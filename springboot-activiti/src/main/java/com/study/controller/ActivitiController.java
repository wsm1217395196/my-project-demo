package com.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.*;

@Slf4j
@Api(tags = "activiti工作流")
@RequestMapping("/activiti")
@RestController
public class ActivitiController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @ApiOperation("查看图片")
    @GetMapping(value = "/image")
    public void image(HttpServletResponse response, @RequestParam String processInstanceId) {
        try {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();

            // null check
            if (processInstance != null) {
                // get process model
                BpmnModel model = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

                if (model != null && model.getLocationMap().size() > 0) {
                    ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
                    List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
                    // 生成流程图 已启动的task 高亮
//                    InputStream is = generator.generateDiagram(model, activeActivityIds);
                    // 生成流程图 都不高亮
                    InputStream is = generator.generateDiagram(model, Collections.<String>emptyList());

                    String imageName = "image" + Instant.now().getEpochSecond() + ".png";
                    FileUtils.copyInputStreamToFile(is, new File("src/main/resources/processes/" + imageName));

                    if (is == null) {
                        return;
                    }
                    response.setContentType("image/png");
                    BufferedImage image = ImageIO.read(is);
                    OutputStream out = response.getOutputStream();
                    ImageIO.write(image, "png", out);
                    is.close();
                    out.close();
                }
            }
        } catch (Exception ex) {
            log.error("查看流程图失败", ex);
        }
    }

    /**
     * 部署流程
     */
    @ApiOperation("部署流程")
    @GetMapping(value = "/deploy")
    public void deploy() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程.bpmn")
                .addClasspathResource("processes/请假流程.png")
                .name("请假流程")
                .deploy();
        System.err.println("部署ID：" + deployment.getId());
        System.err.println("部署名称：" + deployment.getName());
        System.err.println(deployment);
    }

    /**
     * 查询部署流程列表
     */
    @ApiOperation("查询部署流程列表")
    @GetMapping(value = "/getDeployments")
    public void getDeployments() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        list.forEach(deployment -> {
            System.err.println(deployment);
        });
    }

    /**
     * 查询流程定义
     */
    @ApiOperation("查询流程定义")
    @GetMapping(value = "/getDefinition")
    public void getDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            System.err.println("name ：" + pd.getName());
            System.err.println("key ：" + pd.getKey());
            System.err.println("name ：" + pd.getResourceName());
            System.err.println("deploymentId ：" + pd.getDeploymentId());
            System.err.println("version ：" + pd.getVersion());
            System.err.println("-----------------------------------------------------");
        }
    }

    /**
     * 删除流程定义
     */
    @ApiOperation("删除流程定义")
    @GetMapping(value = "/delDefinition")
    public void delDefinition() {
        String pid = "35569559-6539-11eb-945c-3cf011767cd4";
        // 参数一： 流程定义id
        // 参数二： true 表示将删除该路程下所有的流程任务及历史
        //          false 不会删除任务及历史
        try {
            repositoryService.deleteDeployment(pid, false);
        } catch (Exception e) {
            System.err.println("没有流程定义：" + pid);
        }

        this.getDefinition();
    }

    /**
     * 启动流程实例带参数
     */
    @ApiOperation("启动流程实例带参数")
    @GetMapping(value = "/initProcessInstanceWithArgs")
    public void initProcessInstanceWithArgs() {
        // 流程变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("assignee", "张三");
        org.activiti.engine.runtime.ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1", variables);
        System.err.println("流程实例id：" + processInstance.getProcessDefinitionId());
    }

    /**
     * 查询所有任务
     */
    @ApiOperation("查询所有任务")
    @GetMapping(value = "/getTasks")
    public void getTasks() {
        List<Task> list = taskService.createTaskQuery().list();
        for (Task task : list) {
            System.err.println("任务名称：" + task.getName());
            System.err.println("任务执行人：" + task.getAssignee());
            System.err.println("流程实例id：" + task.getProcessInstanceId());
            System.err.println("-----------------------------------------------------");
        }
    }

    /**
     * 执行任务
     * 影响的表   act_run_task   act_hi_taskinst
     */
    @ApiOperation("执行任务")
    @GetMapping(value = "/completeTask")
    public void completeTask(@RequestParam Integer type, @RequestParam Integer complete) {
        Task task = taskService.createTaskQuery().list().get(0);
        String id = task.getId();

        Map<String, Object> variables = new HashMap<String, Object>();
        if (type == 1) {
            taskService.setAssignee(id, "张三");
            variables.put("assignee", "张三");
            variables.put("day", new Random().nextInt(6));
        } else if (type == 2) {
            taskService.setAssignee(id, "总经理");
            variables.put("assignee", "总经理");
        } else if (type == 3) {
            taskService.setAssignee(id, "人力资源");
            variables.put("assignee", "人力资源");
        }

        if (complete != null && complete == 1) {
            taskService.complete(id, variables);
            System.err.println("执行任务");
        }

        getTasks();
    }

    /**
     * 根据用户名查询历史记录
     */
    @ApiOperation("根据用户名查询历史记录")
    @GetMapping(value = "/historyTaskInstanceByUser")
    public void historyTaskInstanceByUser(){
        List<HistoricTaskInstance> history = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .taskAssignee("张三")
                .list();
        for (HistoricTaskInstance hi : history){
            System.err.println("执行人：" + hi.getAssignee());
            System.err.println("name：" + hi.getName());
            System.err.println("流程实例id：" + hi.getProcessInstanceId());
            System.err.println("-----------------------------------------------------");
        }
    }

    /**
     *  根据流程实例查询任务
     */
    @ApiOperation("根据流程实例查询任务")
    @GetMapping(value = "/historyTaskInstanceByProcessInstanceId")
    public void historyTaskInstanceByProcessInstanceId(){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc().list();
        for (HistoricTaskInstance hi : list){
            System.err.println("执行人：" + hi.getAssignee());
            System.err.println("name：" + hi.getName());
            System.err.println("流程实例id：" + hi.getProcessInstanceId());
            System.err.println("-----------------------------------------------------");
        }
    }
}

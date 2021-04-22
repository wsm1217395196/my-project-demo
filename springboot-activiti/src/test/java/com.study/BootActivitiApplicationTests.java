package com.study;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootActivitiApplicationTests {

//    @Autowired
//    private SecurityUtil securityUtil;

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


    /**
     * 部署流程
     */
	@Test
	public void deploy() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程.bpmn")
                .addClasspathResource("processes/请假流程.png")
                .name("请假流程")
                .deploy();
        System.err.println("部署ID："+deployment.getId());
        System.err.println("部署名称："+deployment.getName());
        System.err.println(deployment);
	}

    /**
     * 查询部署流程列表
     */
    @Test
    public void getDeployments(){
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        list.forEach(deployment -> {
            System.err.println(deployment);
        });
    }

    /**
     * 查询流程定义
     */
    @Test
    public void getDefinition(){
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
    @Test
    public void delDefinition(){
        String pid = "35569559-6539-11eb-945c-3cf011767cd4";
        // 参数一： 流程定义id
        // 参数二： true 表示将删除该路程下所有的流程任务及历史
        //          false 不会删除任务及历史
        try {
            repositoryService.deleteDeployment(pid, false);
        } catch (Exception e) {
            System.out.println("没有流程定义：" + pid);
        }

        this.getDefinition();
    }

    /**
     * 启动流程实例带参数
     */
    @Test
    public void initProcessInstanceWithArgs(){
        // 流程变量
        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("assignee","经理");
        org.activiti.engine.runtime.ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1", variables);
        System.err.println("流程实例id：" + processInstance.getProcessDefinitionId());
    }

    /**
     * 查询所有任务
     */
    @Test
    public void getTasks(){
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
    @Test
    public void completeTask(){
        Task task = taskService.createTaskQuery().list().get(0);
        String id = task.getId();

//        taskService.setAssignee(id, "张三");
//        taskService.setAssignee(id, "总经理");
        taskService.setAssignee(id, "人力资源");


        Map<String,Object> variables = new HashMap<String,Object>();
//        variables.put("assignee","张三");
//        variables.put("assignee","总经理");
        variables.put("assignee","人力资源");
//        variables.put("day", 10);
        taskService.complete(id, variables);
        System.out.println("执行任务");

        getTasks();
    }
}
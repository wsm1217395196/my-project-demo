package com.study;

import com.study.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClass {

    @Autowired
    private KieContainer kieContainer;

    @Test
    public void test1() {
        KieSession kieSession = kieContainer.newKieSession("check-model");

        TestModel testModel = new TestModel("wsm", 1, null);
        kieSession.insert(testModel);

        int firedCount;
        List<String> ruleNames = Arrays.asList("rule-age");//要执行的规则
        if (ruleNames.size() > 0) {//1、先执行规则文件的when，2、匹配上的规则放到Match中，3、然后Match和ruleNames匹配取交集，4、最后执行取交集后规则文件中的then
            AgendaFilter agendaFilter = (Match match) -> {
                String ruleName = match.getRule().getName();
                System.out.println("ruleName = " + ruleName);
                if (ruleNames.contains(ruleName)) {
                    return true;
                }
                return false;
            };
            firedCount = kieSession.fireAllRules(agendaFilter);
        } else {//执行规则文件的全部规则
            firedCount = kieSession.fireAllRules();
        }

        System.err.println("触发了" + firedCount + "条规则");
    }

    @Test
    public void test2() {
        KieServices ks = KieServices.Factory.get();
        KieContainer container = ks.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession("check-model");

        TestModel testModel = new TestModel("wsm", 1, null);
        kieSession.insert(testModel);

        int firedCount;
        List<String> ruleNames = Arrays.asList("rule-age");//要执行的规则
        if (ruleNames.size() > 0) {//1、先执行规则文件的when，2、匹配上的规则放到Match中，3、然后Match和ruleNames匹配取交集，4、最后执行取交集后规则文件中的then
            AgendaFilter agendaFilter = (Match match) -> {
                String ruleName = match.getRule().getName();
                System.out.println("ruleName = " + ruleName);
                if (ruleNames.contains(ruleName)) {
                    return true;
                }
                return false;
            };
            firedCount = kieSession.fireAllRules(agendaFilter);
        } else {//执行规则文件的全部规则
            firedCount = kieSession.fireAllRules();
        }

        System.err.println("触发了" + firedCount + "条规则");
    }
}

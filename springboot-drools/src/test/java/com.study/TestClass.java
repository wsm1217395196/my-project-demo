package com.study;

import com.study.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClass {

    @Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer container = ks.getKieClasspathContainer();
        KieSession kieSession = container.newKieSession("check-model");

        TestModel testModel = new TestModel("wsm", null, null);
        kieSession.insert(testModel);
        int i = kieSession.fireAllRules();

    }
}

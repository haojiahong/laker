package com.hao.laker.biz;

import com.google.common.collect.Lists;
import com.hao.laker.biz.test.TestService;
import com.hao.laker.entity.test.Test1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-biz-context.xml")
//@Transactional
@Slf4j
public class TestServiceTest {
    @Autowired
    private TestService testService;

    @Test
//    @Rollback(false)
    public void demoTest() {
        Test1 testOne = new Test1();
        testOne.setTestName("testOne");
        Test1 testTwo = new Test1();
        testTwo.setTestName("test");
        Test1 testThree = new Test1();
        testThree.setTestName("testThree");
        List<Test1> test1List = Lists.newArrayList();
        test1List.add(testOne);
        test1List.add(testTwo);
        test1List.add(testThree);
        int result = testService.batchInsertTest(test1List);
        System.out.println(result);

    }

    @Test
    public void demoTest2() {
        Test1 test1 = new Test1();
        test1.setTestName("test11111");
        int result = testService.insertTest(test1);
        System.out.println(result);
    }

}




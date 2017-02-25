package com.hao.laker.biz;

import com.hao.laker.entity.test.Test1;
import com.hao.laker.manager.other.TestToleranceManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-biz-context.xml")
//@Transactional
@Slf4j
public class TestServiceTest {

    @Autowired
    private TestToleranceManager testToleranceManager;

    //    @Autowired
//    private TestService testService;
//    @Autowired
//    private MainProcessor mainProcessor;
//
//    @Test
////    @Rollback(false)
//    public void demoTest() {
//        Test1 testOne = new Test1();
//        testOne.setTestName("testOne");
//        Test1 testTwo = new Test1();
//        testTwo.setTestName("test");
//        Test1 testThree = new Test1();
//        testThree.setTestName("testThree");
//        List<Test1> test1List = Lists.newArrayList();
//        test1List.add(testOne);
//        test1List.add(testTwo);
//        test1List.add(testThree);
//        int result = testService.batchInsertTest(test1List);
//        System.out.println(result);
//
//    }
//
    @Test
    public void demoTest2() {
        Test1 test1 = new Test1();
        test1.setTestName("test11111");
//        int result = testService.insertTest(test1);
        System.out.println(test1);
    }
//
//    @Test
//    public void threadTest() {
//        mainProcessor.testSpringTransactionThread();
//    }

    @Test
    public void testTolerance() throws Exception {
        Integer userId = 2565;
        String json = "i am haojiahong";
        testToleranceManager.canToleranceException(null, userId, json);

        Thread.sleep(1000 * 60 * 2);

        System.out.println("测试完成");


    }

}




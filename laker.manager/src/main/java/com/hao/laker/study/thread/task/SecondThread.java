package com.hao.laker.study.thread.task;

import com.hao.laker.biz.test.TestService;
import com.hao.laker.common.util.SpringIocUtil;
import com.hao.laker.dao.test.TestMapper;
import com.hao.laker.entity.test.Test1;

/**
 * Created by haojiahong on 17/2/10.
 */
public class SecondThread implements Runnable {

    private TestMapper testMapper;
    private TestService testService;

    public SecondThread() {
        this.testMapper = (TestMapper) SpringIocUtil.getBean("testMapper");
        this.testService = (TestService) SpringIocUtil.getBean("testServiceImpl");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.insertTest();
        System.out.println("我是第二个线程");
    }

    private  void insertTest() {
        Test1 test1 = new Test1();
        test1.setTestName("test");
        testService.insertTest(test1);
//        testMapper.insert(test1);
//        int i = 10 / 0;
        throw new RuntimeException("抛出运行时异常");
//        System.out.println(i);
    }
}

package com.hao.laker.biz.test.impl;

import com.google.common.collect.Lists;
import com.hao.laker.biz.test.TestService;
import com.hao.laker.dao.test.TestMapper;
import com.hao.laker.entity.test.Test1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by haojiahong on 16/7/26.
 */
@Slf4j
@Component
public class TestServiceImpl implements TestService {
    private static final Integer FIXED_THREAD = Runtime.getRuntime().availableProcessors();

    @Autowired
    private TestMapper testMapper;

    ExecutorService executor = Executors.newFixedThreadPool(FIXED_THREAD, new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int batchInsertTest(List<Test1> test1List) {
        class AddTestTask implements Callable<Void> {
            private Test1 test;

            AddTestTask(Test1 test) {
                this.test = test;
            }

            @Override
            public Void call() throws Exception {
                addTest(test);
                return null;
            }
        }
        List<AddTestTask> taskList = Lists.newArrayList();
        for (Test1 test1 : test1List) {
            AddTestTask addTestTask = new AddTestTask(test1);
            taskList.add(addTestTask);
        }
        try {
            List<Future<Void>> results = executor.invokeAll(taskList);
            for (Future<Void> f : results) {
                f.get();
            }
        } catch (Exception e) {
            log.error("执行插入任务失败：{}", e);
        } finally {
            executor.shutdown();
        }

        return 0;
    }

    @Override
    @Transactional
    public int insertTest(Test1 test1) {
        addTest(test1);
        return 0;
    }

    private void addTest(Test1 test1) {
        if ("test".equals(test1.getTestName())) {
            throw new RuntimeException("抛出异常test");
        }
        testMapper.insert(test1);
    }


}

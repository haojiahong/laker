package com.hao.laker.biz.test;

import com.hao.laker.entity.test.Test1;

import java.util.List;

/**
 * Created by haojiahong on 16/7/26.
 */
public interface TestService {
    /**
     * 测试多线程插入事务回滚
     *
     * @param test1List
     * @return
     */
    public int batchInsertTest(List<Test1> test1List);

    public int insertTest(Test1 test1);

}

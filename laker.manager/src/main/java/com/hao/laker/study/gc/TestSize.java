package com.hao.laker.study.gc;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by haojiahong on 17/5/3.
 */
public class TestSize {

    public static void main(String[] args) throws InterruptedException {

        List<ActivityBatImportParam> list = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            ActivityBatImportParam param = new ActivityBatImportParam();
            param.setActId(11);
            param.setActivityDescription("aaa");
            param.setActType(3);
            list.add(param);
        }

        System.out.println(list.size());

        Thread.sleep(1000 * 60);

        System.out.println(list.size());
    }
}

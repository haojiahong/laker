package com.hao.laker.dao.test;

import com.hao.laker.dao.base.BaseDao;
import com.hao.laker.dao.common.MyBatisRepository;
import com.hao.laker.entity.test.Test1;

/**
 * Created by haojiahong on 16/7/25.
 */
@MyBatisRepository
public interface TestMapper extends BaseDao<Test1> {
}

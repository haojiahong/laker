package com.hao.laker.dao;

import com.hao.laker.dao.common.MyBatisRepository;
import com.hao.laker.entity.ToleranceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by haojiahong on 17/2/24.
 */
@MyBatisRepository
public interface ToleranceMapper {

    Integer insertSelective(ToleranceDO record);

    Integer updateByPrimaryKey(ToleranceDO record);

    List<ToleranceDO> selectByStatus(@Param("status") Integer status);

}

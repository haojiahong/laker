package com.hao.laker.manager.tolerance;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.hao.laker.bizenum.ToleranceStatusEnum;
import com.hao.laker.dao.ToleranceMapper;
import com.hao.laker.entity.ToleranceDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by haojiahong on 17/2/24.
 */
@Slf4j
@Component
public class ToleranceManagerImpl implements ToleranceManager {

    @Autowired
    private ToleranceMapper toleranceMapper;

    @Override
    public void handleToleranceSuccess(ToleranceDO tolerance) {
        if (tolerance == null) {
            return;
        }

        ToleranceDO record = new ToleranceDO();
        record.setId(tolerance.getId());
        record.setGmtModified(new Date());
        record.setModifier(1);
        record.setToleranceStatus(ToleranceStatusEnum.DONE.getCode());
        toleranceMapper.updateByPrimaryKey(record);
    }

    @Override
    public void handleToleranceFail(ToleranceDO toleranceDO, String className, String methodName, String request) {
        if (toleranceDO == null) {
            this.insertTolerance(className, methodName, request);
            return;
        }
        this.increaseRetryTimes(toleranceDO);

    }

    private void insertTolerance(String className, String methodName, String request) {
        ToleranceDO record = new ToleranceDO();
        record.setIsDeleted("N");
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setCreator(1);
        record.setModifier(1);

        record.setToleranceClass(className);
        record.setToleranceMethod(methodName);
        record.setToleranceRequest(request);
        record.setToleranceStatus(ToleranceStatusEnum.READY.getCode());
        record.setRetryTimes(0);
        toleranceMapper.insertSelective(record);
    }

    private void increaseRetryTimes(ToleranceDO tolerance) {
        if (tolerance == null) {
            return;
        }

        Integer times = Optional.fromNullable(tolerance.getRetryTimes()).or(0);
        if (times >= 5) {
            log.warn("容灾重试超过5次，不再尝试，人工定位问题。tolerance:{}", JSON.toJSONString(tolerance));
            // TODO 通知相关人员

            ToleranceDO record = new ToleranceDO();
            record.setId(tolerance.getId());
            record.setGmtModified(new Date());
            record.setModifier(1);
            record.setToleranceStatus(ToleranceStatusEnum.EXPIRED.getCode());
            toleranceMapper.updateByPrimaryKey(record);
            return;
        }

        ToleranceDO record = new ToleranceDO();
        record.setId(tolerance.getId());
        record.setGmtModified(new Date());
        record.setModifier(1);
        record.setRetryTimes(++times);
        toleranceMapper.updateByPrimaryKey(record);
    }
}

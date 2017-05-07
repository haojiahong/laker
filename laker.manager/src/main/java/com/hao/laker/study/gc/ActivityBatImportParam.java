package com.hao.laker.study.gc;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by haojiahong on 16/6/20.
 */
@Data
public class ActivityBatImportParam {
    /**
     * 活动ID
     * 如果传递，就会清理该活动下所有的组合，然后重新创建新的组合
     */
    private Integer actId;
    /**
     * 活动类型
     */
    private Integer actType;
    /**
     * 客户标签
     */
    private String customerTags;
    /**
     * 活动标准名称
     */
    private String pcDisplayName;
    /**
     * 活动内部名称
     */
    private String name;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 最小购买件数
     */
    private Integer goodsNumMin;
    /**
     * 最小购买金额
     */
    private BigDecimal goodsAmountMin;
    /**
     * 商品销售总量
     */
    private Integer limitType;
    /**
     * 支付方式
     */
    private Byte actPayType;
    /**
     * 能否使用优惠券
     */
    private Byte couponEnable;
    /**
     * 是否有订单优惠1%
     * 0、 不享受    1、享受
     */
    private int forbid;

    /**
     * 活动规则
     */
    private String rules;

    /**
     * 活动标签
     * 可以为NULL
     */
    private String tags;

    /**
     * 组合列表
     */
//    private List<HdGroupBO> groupList;

    /**
     * 是否允许拆单
     */
    private Integer isEnabledDivide;

    /**
     * 单品导入使用，单纯新增还是覆盖全部
     * 是否覆盖，1：不覆盖（新增） 2：覆盖
     */
    private Integer isOverride;

    /**
     * 拒收处理规则
     */
    private Integer activityRefundRuleType;

    /**
     * 活动描述字段
     */
    private String activityDescription;

    /**
     * 商品价格类型
     */
    private Integer priceSource;

    /**
     * 父活动IDs
     */
    private String parentIds;
}

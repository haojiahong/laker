package com.hao.laker.bo;

import com.hao.laker.common.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by haojiahong on 17/2/18.
 */
@Getter
@Setter
public class ArticleBO {
    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String description;
    private Integer status;
    private String author;
    private Date createTime;
    private Date updateTime;
    private Integer showCount;

    private String createTimeStr;
    private String updateTimeStr;

    public String getCreateTimeStr() {
        return DateUtil.date2Hms(this.createTime);
    }

    public String getUpdateTimeStr() {
        return DateUtil.date2Hms(this.updateTime);
    }
}

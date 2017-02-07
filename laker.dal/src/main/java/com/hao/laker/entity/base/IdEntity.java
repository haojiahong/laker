package com.hao.laker.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by haojiahong on 16/7/21.
 */
@Getter
@Setter
public abstract class IdEntity implements Serializable {
    protected Long id;
}

package com.webgis.domain.base;

import com.webgis.enums.TypeEnum;

/**
 * Created by Justin on 2017/3/20.
 *
 * 基础设施统一抽象domain
 *
 */

public abstract class BaseDomain {

    private TypeEnum type;

    public BaseDomain() {
    }

    public BaseDomain(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }
}

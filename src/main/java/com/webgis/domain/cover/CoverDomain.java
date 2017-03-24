package com.webgis.domain.cover;

import com.webgis.domain.base.BaseDomain;
import com.webgis.domain.base.PointDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * 井盖模型抽象domain
 *
 */

public abstract class CoverDomain extends BaseDomain {

    private List<PointDomain> pointList;

    public CoverDomain() {
    }

    public CoverDomain(TypeEnum type, List<PointDomain> pointList) {
        super(type);
        this.pointList = pointList;
    }

    public List<PointDomain> getPointList() {
        return pointList;
    }

    public void setPointList(List<PointDomain> pointList) {
        this.pointList = pointList;
    }
}

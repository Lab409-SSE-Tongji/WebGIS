package com.webgis.domain.lamp;

import com.webgis.domain.base.BaseDomain;
import com.webgis.domain.base.PointDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by CCMEOW on 2017/6/16.
 */
public class LampDomain extends BaseDomain {
    private List<PointDomain> pointList;

    public LampDomain() {
    }

    public LampDomain(TypeEnum type, List<PointDomain> pointList) {
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

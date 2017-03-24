package com.webgis.domain.cover;

import com.webgis.domain.base.PointDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * 一般窨井盖模型，初始化时指定类型
 *
 */

public class CommonCoverDomain extends CoverDomain {

    public CommonCoverDomain() {
    }

    public CommonCoverDomain(List<PointDomain> pointList) {
        super(TypeEnum.YJG, pointList);
    }

}

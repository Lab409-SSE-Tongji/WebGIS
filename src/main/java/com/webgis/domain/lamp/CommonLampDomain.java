package com.webgis.domain.lamp;

import com.webgis.domain.base.PointDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by CCMEOW on 2017/6/16.
 */
public class CommonLampDomain extends LampDomain {
    public CommonLampDomain() {
    }

    public CommonLampDomain(List<PointDomain> pointList) {
        super(TypeEnum.LD, pointList);
    }
}

package com.webgis.domain.pipe;

import com.webgis.domain.base.LineDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * 一般管道模型，初始化时指定类型
 *
 */

public class CommonPipeDomain extends PipeDomain {

    public CommonPipeDomain() {
    }

    public CommonPipeDomain(List<LineDomain> lineList) {
        super(TypeEnum.XSG, lineList);
    }

}

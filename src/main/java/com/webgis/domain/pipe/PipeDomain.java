package com.webgis.domain.pipe;

import com.webgis.domain.base.BaseDomain;
import com.webgis.domain.base.LineDomain;
import com.webgis.enums.TypeEnum;

import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * 管道类模型抽象类
 *
 */

public abstract class PipeDomain extends BaseDomain {

    private List<LineDomain> lineList;

    public PipeDomain() {
    }

    public PipeDomain(TypeEnum type, List<LineDomain> lineList) {
        super(type);
        this.lineList = lineList;
    }

    public List<LineDomain> getLineList() {
        return lineList;
    }

    public void setLineList(List<LineDomain> lineList) {
        this.lineList = lineList;
    }
}

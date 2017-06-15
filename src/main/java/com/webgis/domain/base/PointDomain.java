package com.webgis.domain.base;

import com.webgis.enums.StatusEnum;
import com.webgis.mongo.entity.MongoRepair;
import com.webgis.utils.DateUtil;
import com.webgis.web.dto.WebRepair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * 点模型domain
 *
 */

public class PointDomain {

    private String url;
    private String pointId;
    private Long specialId;

    private double x;
    private double y;
    private double z;
    private StatusEnum status;

    private List<String> repairIds;
    private List<MongoRepair> repairs;

    public PointDomain() {
        this.repairIds = new ArrayList<>();
        this.repairs = new ArrayList<>();
    }

    public PointDomain(double x, double y, double z, StatusEnum status, String url) {
        this.url = url;
        this.x = x;
        this.y = y;
        this.z = z;
        this.status = status;
        this.specialId = new Date().getTime();
        this.repairIds = new ArrayList<>();
    }

    public List<String> getRepairIds() {
        return repairIds;
    }

    public List<MongoRepair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<MongoRepair> repairs) {
        this.repairs = repairs;
    }

    public void setRepairIds(List<String> repairIds) {
        this.repairIds = repairIds;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {
        this.url = url;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PointDomain{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", status=" + status +
                '}';
    }
}

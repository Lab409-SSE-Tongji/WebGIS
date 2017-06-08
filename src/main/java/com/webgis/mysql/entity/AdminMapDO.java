package com.webgis.mysql.entity;

/**
 * Created by CCMEOW on 2017/6/8.
 */
public class AdminMapDO extends BaseDO {

    private Integer map_id;
    private Integer admin_id;

    public Integer getMap_id() {
        return map_id;
    }

    public void setMap_id(Integer map_id) {
        this.map_id = map_id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public AdminMapDO(){

    }

    public AdminMapDO(Integer mapId, Integer adminId){
        this.map_id =mapId;
        this.admin_id =adminId;
    }


}

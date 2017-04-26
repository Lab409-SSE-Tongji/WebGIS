package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebFolder;

/**
 * Created by cz on 2017/4/25.
 */
public interface FolderService {

    /**
     * 新建文件夹接口
     * @param webFolder
     * @return
     */
    BaseResult<Object> addFolder(WebFolder webFolder);

    /**
     * 更新文件夹信息接口
     * @param webFolder
     * @return
     */
    BaseResult<Object> updateFolder(WebFolder webFolder);

    /**
     * 删除文件夹接口
     * @param folderId
     * @return
     */
    BaseResult<Object> deleteFolder(int folderId);

    /**
     * 获取文件夹接口
     * @param folderId
     * @return
     */
    BaseResult<Object> getFolder(int folderId);


    /**
     * 根据用户账户及上层文件夹信息获取对应的本级文件夹
     * @param accountId
     * @param upperFolder
     * @return
     */
    BaseResult<Object> getFolderByAccountIDandUpperFolder(int accountId, int upperFolder);
}

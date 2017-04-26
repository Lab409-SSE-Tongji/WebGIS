package com.webgis.service.imp;

import com.webgis.mysql.entity.FolderDO;
import com.webgis.mysql.mapper.FolderMapper;
import com.webgis.service.FolderService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cz on 2017/4/25.
 */
@Service
public class FolderServiceImp implements FolderService {

    @Autowired
    private FolderMapper folderMapper;


    /**
     * 新建文件夹接口
     * @param webFolder
     * @return
     */
    @Override
    public BaseResult<Object> addFolder(WebFolder webFolder){
        FolderDO folderDO = new FolderDO(webFolder);
        folderMapper.insert(folderDO);
        return new BaseResult<>(folderDO);
    }

    /**
     * 更新文件夹信息接口
     * @param webFolder
     * @return
     */
    @Override
    public BaseResult<Object> updateFolder(WebFolder webFolder){
        if (folderMapper.getFolderById(webFolder.getId()) == null) {
            return new BaseResult<>(500, "该文件夹不存在");
        }
        FolderDO folderDO = new FolderDO(webFolder);
        folderMapper.update(folderDO);

        return new BaseResult<>(folderDO);
    }

    /**
     * 删除文件夹接口
     * @param folderId
     * @return
     */
    @Override
    public BaseResult<Object> deleteFolder(int folderId) {
        if (folderMapper.getFolderById(folderId) == null) {
            return new BaseResult<>(500, "该文件夹不存在");
        }
        folderMapper.deleteFolder(folderId);
        return new BaseResult<>();
    }

    /**
     * 获取文件夹接口
     * @param folderId
     * @return
     */
    @Override
    public BaseResult<Object> getFolder(int folderId) {
        if (folderMapper.getFolderById(folderId) == null) {
            return new BaseResult<>(500, "该文件夹不存在");
        }
        FolderDO folderDO = new FolderDO();
        folderDO = folderMapper.getFolderById(folderId);
        return new BaseResult<>(folderDO);
    }


    /**
     * 根据用户账户及上层文件夹信息获取对应的本级文件夹
     * @param accountId
     * @param upperFolder
     * @return
     */
    @Override
    public BaseResult<Object> getFolderByAccountIDandUpperFolder(int accountId, int upperFolder) {
        if (upperFolder != 0 && folderMapper.getFolderById(upperFolder) == null) {
            return new BaseResult<>(500, "该文件夹不存在");
        }
        List<FolderDO> folderDO = folderMapper.getFolderByAccountIDandUpperFolder(accountId, upperFolder);
        return new BaseResult<>(folderDO);
    }
}

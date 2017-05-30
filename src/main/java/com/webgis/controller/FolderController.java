package com.webgis.controller;

import com.webgis.service.FolderService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cz on 2017/4/25.
 */

@CrossOrigin
@RestController
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    private FolderService folderService;

    /**
     * 新建文件夹
     * @param webFolder
     * @return
     */
    @RequestMapping(value = "/folders", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addFolder(@RequestBody WebFolder webFolder) {
        return folderService.addFolder(webFolder);
    }

    /**
     * 删除文件夹
     * @param folderId
     * @return
     */
    @RequestMapping(value = "/folders/id", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteMap(@RequestParam("folderId") int folderId) {
        return folderService.deleteFolder(folderId);
    }

    /**
     * 更新文件夹
     * @param webFolder
     * @return
     */
    @RequestMapping(value = "/folders/id", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> updateMap(@RequestBody WebFolder webFolder) {
        return folderService.updateFolder(webFolder);
    }

    /**
     * 获取文件夹信息
     * @param folderId
     * @return
     */
    @RequestMapping(value = "/folders/id", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMap(@RequestParam("folderId") int folderId) {
        return folderService.getFolder(folderId);
    }

    /**
     * 根据用户账户及上层文件夹信息获取对应的本级文件夹
     * @param accountId
     * @param upperFolder
     */
    @RequestMapping(value = "/folders/accountidandupperfolder", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getFolderByAccountIDandUpperFolder(@RequestParam("accountId") int accountId, @RequestParam("upperFolder") int upperFolder) {
        return folderService.getFolderByAccountIDandUpperFolder(accountId, upperFolder);
    }


}

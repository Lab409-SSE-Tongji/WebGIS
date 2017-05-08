package com.webgis.service.imp;


import com.webgis.mysql.entity.PageDO;
import com.webgis.mysql.mapper.PageMapper;
import com.webgis.service.PaginationService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebPage;

import java.util.List;

/**
 * Created by Yan Lieu on 2017/5/8.
 */

public class PaginationServiceImp implements PaginationService {

    private PageMapper pageMapper;
    @Override
    public BaseResult<Object> addPage(WebPage webPage) {
        PageDO pageDO=new PageDO(webPage);
        pageMapper.insert(pageDO);
        return new BaseResult<>(pageDO);
    }

    @Override
    public BaseResult<Object> updatePage(WebPage webPage) {
        if (pageMapper.getPageById(webPage.getId()) == null) {
            return new BaseResult<>(500, "该页面不存在");
        }
        PageDO pageDO = new PageDO(webPage);
        pageMapper.update(pageDO);

        return new BaseResult<>(pageDO);
    }

    @Override
    public BaseResult<Object> deletePage(int pageID) {
        if (pageMapper.getPageById(pageID) == null) {
            return new BaseResult<>(500, "该页面不存在");
        }
        pageMapper.deletePage(pageID);
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> getPage(int pageId) {
        if (pageMapper.getPageById(pageId) == null) {
            return new BaseResult<>(500, "该页面不存在");
        }
        PageDO pageDO = new PageDO();
        pageDO = pageMapper.getPageById(pageId);
        return new BaseResult<>(pageDO);
    }

    @Override
    public BaseResult<Object> getPageByAccountIDandPreviousPage(int accountId, int previousPage) {
        if (previousPage != 0 && pageMapper.getPageById(previousPage) == null) {
            return new BaseResult<>(500, "该页面不存在");
        }
        List<PageDO> pageDO = pageMapper.getPageByAccountIDandPreviousPage(accountId, previousPage);
        return new BaseResult<>(pageDO);
    }
}

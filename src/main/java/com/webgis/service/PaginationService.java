package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebPage;
/**
 * Created by Yan Lieu on 2017/5/8.
 */
public interface PaginationService {
    /**
     * Add new page interface
     */
    BaseResult<Object> addPage(WebPage webPage);

    /**
     * Update page interface info
     */
    BaseResult<Object> updatePage(WebPage webPage);

    /**
     * Delete page interface info
     */
    BaseResult<Object> deletePage(int pageID);

    /**
     * get page interface info
     */
    BaseResult<Object> getPage(int pageId);

    /**
     * get page interface info by user account && its previous page info
     */
    BaseResult<Object> getPageByAccountIDandPreviousPage(int accountId, int previousPage);

}

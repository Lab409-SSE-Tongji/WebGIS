package com.webgis.controller;

import com.webgis.service.PaginationService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebPage;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yan Lieu on 2017/5/8.
 */
@CrossOrigin
@RestController
@RequestMapping("/page")
public class PageController {
    private PaginationService pageService;

    /**
     * create page
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/pages", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addPage(@RequestBody WebPage webPage){return pageService.addPage(webPage);}

    /**
     * delete page
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/pages/id", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteMap(@RequestParam("pageId") int pageId) {
        return pageService.deletePage(pageId);
    }

    /**
     * update
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/pages/id", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> updateMap(@RequestBody WebPage webPage) {
        return pageService.updatePage(webPage);
    }

    /**
     * get Page Info
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/pages/id", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMap(@RequestParam("pageId") int pageId) {
        return pageService.getPage(pageId);
    }

    /**
     * get the current page by user account  && the previous page
     * @param accountId
     * @param previousPage
     */
    @RequestMapping(value = "/pages/accountidandpreviouspage", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getPageByAccountIDandPreviousPage(@RequestParam("accountId") int accountId, @RequestParam("previousPage") int previousPage) {
        return pageService.getPageByAccountIDandPreviousPage(accountId, previousPage);
    }

}

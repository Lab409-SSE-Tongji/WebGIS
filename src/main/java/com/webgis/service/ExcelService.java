package com.webgis.service;

import com.webgis.domain.cover.CommonCoverDomain;
import com.webgis.domain.pipe.CommonPipeDomain;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Justin on 2017/3/10.
 *
 * Excel 解析接口
 *
 */

public interface ExcelService {

    /**
     * 点类信息Excel 解析接口
     * @param file
     * @return
     */
    CommonCoverDomain pointExcelAnalysis(MultipartFile file);


    /**
     * 线类信息Excel 解析接口
     * @param file
     * @return
     */
    CommonPipeDomain lineExcelAnalysis(MultipartFile file);
}

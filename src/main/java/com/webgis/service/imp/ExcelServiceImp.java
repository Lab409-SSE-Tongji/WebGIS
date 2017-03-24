package com.webgis.service.imp;

import com.webgis.domain.base.LineDomain;
import com.webgis.domain.base.PointDomain;
import com.webgis.domain.cover.CommonCoverDomain;
import com.webgis.domain.pipe.CommonPipeDomain;
import com.webgis.enums.StatusEnum;
import com.webgis.service.ExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 2017/3/10.
 */

@Service
public class ExcelServiceImp implements ExcelService {

    @Override
    public CommonCoverDomain pointExcelAnalysis(MultipartFile file) {
        List<PointDomain> list = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // TODO: 2017/3/16 检测Excel首行内容
            for (int rowNum=1; rowNum<sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                PointDomain pointDomain = new PointDomain(row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getNumericCellValue(),
                        row.getCell(2).getNumericCellValue(),
                        StatusEnum.getEnum(row.getCell(3).getStringCellValue()));
                list.add(pointDomain);
            }
            return new CommonCoverDomain(list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommonPipeDomain lineExcelAnalysis(MultipartFile file) {
        List<LineDomain> list = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            // TODO: 2017/3/16 检测Excel首行内容
            for (int rowNum=1; rowNum<sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                LineDomain lineDomain = new LineDomain(row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getNumericCellValue(),
                        row.getCell(2).getNumericCellValue(),
                        row.getCell(3).getNumericCellValue(),
                        row.getCell(4).getNumericCellValue(),
                        row.getCell(5).getNumericCellValue(),
                        StatusEnum.getEnum(row.getCell(6).getStringCellValue()));
                list.add(lineDomain);
            }
            return new CommonPipeDomain(list);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package org.jeecgframework.poi.excel.view;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.poi.excel.entity.vo.POIConstants;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author JueYue on 14-3-8.
 * Excel 生成解析器,减少用户操作
 */
public class JeecgSingleExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {
        String codedFileName = "临时文件.xls";
        if (model.containsKey(POIConstants.FILE_NAME)) {
            codedFileName = (String) model.get(POIConstants.FILE_NAME)+".xls";
        }
        httpServletResponse.setHeader(
                "content-disposition",
                "attachment;filename=" + new String(codedFileName.getBytes(), "iso8859-1"));
        if (model.containsKey(POIConstants.MAP_LIST)) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) model.get(POIConstants.MAP_LIST);
            for (Map<String, Object> map : list) {
                ExcelExportUtil.createSheetInUserModel2File(hssfWorkbook,
                        (ExcelTitle) map.get(POIConstants.EXCEL_TITLE),
                        (Class<?>) map.get(POIConstants.CLASS),
                        (Collection<?>) map.get(POIConstants.DATA_LIST));
            }
        } else {
            ExcelExportUtil.createSheetInUserModel2File(hssfWorkbook,
                    (ExcelTitle) model.get(POIConstants.EXCEL_TITLE), (Class<?>) model.get(POIConstants.CLASS),
                    (Collection<?>) model.get(POIConstants.DATA_LIST));
        }
    }
}

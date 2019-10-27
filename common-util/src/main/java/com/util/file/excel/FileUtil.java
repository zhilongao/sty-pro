package com.util.file.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author long
 * @Date 2019/10/20 16:18
 */
public class FileUtil {

    /**
     * 直接读取
     * @param excelFile
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List<Object> readExcel(MultipartFile excelFile, Class clazz) throws Exception {
        return EasyExcelFactory.read(excelFile.getInputStream(), new Sheet(1, 1, clazz));
    }

    /**
     * 读取时监听
     * @param excelFile
     * @param eventListener
     * @throws Exception
     */
    public static void readExcel(MultipartFile excelFile, AnalysisEventListener eventListener) throws Exception{
        ExcelReader reader = EasyExcelFactory.getReader(excelFile.getInputStream(), eventListener);
        reader.read(new Sheet(1, 1, StudentModel.class));
    }
}

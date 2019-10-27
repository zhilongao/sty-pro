package com.util.controller;

import com.util.file.excel.ExcelListener;
import com.util.file.excel.FileUtil;
import com.util.file.excel.StudentModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author long
 * @Date 2019/10/20 15:43
 */
@Api
@RestController
@RequestMapping(value = "file")
public class FileController {

    @ApiOperation(value = "fileImport")
    @PostMapping
    public List<StudentModel> fileImport(MultipartFile file) {
        return listenerImport(file);
    }

    /**
     * 直接导入
     * @param file
     * @return
     */
    private List<StudentModel> forceImport(MultipartFile file) {
        List<StudentModel> list = new ArrayList<>();
        List<Object> objects = null;
        try {
            objects = FileUtil.readExcel(file, StudentModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!CollectionUtils.isEmpty(objects)) {
            for (Object obj : objects) {
                StudentModel model = (StudentModel) obj;
                list.add(model);
            }
        }
        return list;
    }

    /**
     * 导入时监听
     * @param file
     * @return
     */
    private List<StudentModel> listenerImport(MultipartFile file) {
        ExcelListener listener = new ExcelListener();
        try {
            FileUtil.readExcel(file, listener);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        List<StudentModel> rows = listener.getRows();
        return rows;
    }
}

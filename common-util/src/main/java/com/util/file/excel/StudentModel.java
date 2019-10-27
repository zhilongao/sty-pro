package com.util.file.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/10/20 16:19
 */
public class StudentModel extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "学号",index = 0)
    private String sNo;

    @ExcelProperty(value = "姓名",index = 1)
    private String sName;

    @ExcelProperty(value = "年龄",index = 2)
    private Integer sAge;

    @ExcelProperty(value = "平均分",index = 3)
    private Double sScore;

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public Double getsScore() {
        return sScore;
    }

    public void setsScore(Double sScore) {
        this.sScore = sScore;
    }
}

package com.common.util.file.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author long
 * @Date 2019/10/20 16:28
 */
public class ExcelListener extends AnalysisEventListener<StudentModel> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    private final List<StudentModel> rows = new ArrayList<>();
    private final List<StudentModel> errors = new ArrayList<>();
    private final List<Integer> errorRows = new ArrayList<>();
    private int start = 1;

    @Override
    public void invoke(StudentModel object, AnalysisContext context) {
        start++;
        logger.info("read object:{}", object);
        if (StringUtils.isEmpty(object.getsNo()) || StringUtils.isEmpty(object.getsName())) {
            errors.add(object);
            errorRows.add(start);
        } else {
            rows.add(object);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("read {} rows %n", rows.size());
    }

    public List<StudentModel> getRows() {
        return rows;
    }
}

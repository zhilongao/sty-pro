package com.study.zl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author long
 * @Date 2019/8/16 13:59
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class JvmAnalysis {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

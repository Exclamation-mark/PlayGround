package com.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ExportTest {
    public static class TestList{
        private String d;
        private String e;

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }
    }

    /**
     * 多列表组合填充填充
     *
     * @since 2.2.0-beta1
     */
    @Test
    public void compositeFill() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
        String templateFileName = "C:\\code\\PlayGround\\JavaProject\\WebSocketDemo\\src\\test\\resources\\a入库预约单模板 - 副本.xlsx";
        String fileName = "C:\\code\\PlayGround\\JavaProject\\WebSocketDemo\\src\\test\\resources\\rrr.xlsx";

        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).autoStyle(Boolean.FALSE).build();
        excelWriter.fill(data(), fillConfig, writeSheet);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", "zzq_orderNo");
        map.put("orderNo1", "zzq_orderNo1");
        map.put("a", "zzq_a");
        map.put("b", "zzq_b");
        map.put("c", "zzq_c");
        map.put("tv", "zzq_tv");
        excelWriter.fill(map, writeSheet);

        // 别忘记关闭流
        excelWriter.finish();
    }

    private List<TestList> data() {
        List<TestList> r = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            TestList testList = new TestList();
            testList.setD(RandomStringUtils.random(10, true, false));
            testList.setE(RandomStringUtils.random(10, true, false));
            r.add(testList);
        }
        return r;
    }
}

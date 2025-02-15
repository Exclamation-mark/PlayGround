package com.example;

import com.sarojaba.prettytable4j.PrettyTable;
import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

public class TableDemoTest {

    @Test
    public void testString() {
        String[][] data = new String[][]{
                {"a", "b", "c"},
                {"a", "b", "c"},
                {"a", "b", "c"},
                {"a", "b", "c"}
        };
        Table table = Table.of(data, Alignment.CENTER, 10); // 10-character wide for each column
        System.out.println(table); // NOTICE: table.toString() is called implicitly
    }

    @Test
    public void testString2() {
        PrettyTable pt = PrettyTable
                .fieldNames("name", "age", "city")
                .addRow("asxas", 22, "new york")
                .addRow("elizabeth", 22, "xas")
                .addRow("bill", 31, "axs")
                .addRow("bill", 31, "xas")
                .addRow("bill", 31, "xasd")
                .addRow("mary", 18, "los angeles ");
        for (int i = 0; i < 100; i++) {
            pt.addRow("index_" + i, i, RandomStringUtils.random(15, true, false) + "city_" + i + "_");
        }
        pt.sortTable("city");
        System.out.println(pt.toString());
    }

    @Test
    public void testArea() {
        System.out.println(judge(4,20, 20,23));
        System.out.println(judge(4,21, 20,23));
        System.out.println(judge(4,25, 20,23));
        System.out.println(judge(24,25, 20,23));
        System.out.println(judge(30,35, 33,53));
        System.out.println(judge(30,35, 30,53));
    }

    public static boolean judge(int a, int b, int c, int d) {
        return Math.max(a, c) < Math.min(b, d);
    }
}

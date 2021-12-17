package com.example;

import com.sarojaba.prettytable4j.PrettyTable;
import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;
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
                .addRow("johnassssssssssssssssssssssssssssssssssss", 22, "new york")
                .addRow("elizabeth", 426165223, "chsaddddddddddddddddddddddddddasdddddddddddddddddddddddddddddddddddddddddicago")
                .addRow("bill", 31, "atxsasdasdasdddddddddddddddddddddddddddddddddddddddddlanta")
                .addRow("mary", 18, "los angeles");

        System.out.println(pt);
    }
}

package com.example;

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
}

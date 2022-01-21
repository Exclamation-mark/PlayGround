package com.example;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;

import java.util.Map;
import java.util.function.Predicate;

public class MvelTest {

    @Test
    public void testPre(){
        String expression = "a == null && b == nil ";
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", null);
        map.put("b", null);

        Object object = MVEL.eval(expression, map);
        Assertions.assertTrue(object != null);
        Assertions.assertTrue(object instanceof Boolean);
        Assertions.assertTrue(object.equals(true));
    }
}

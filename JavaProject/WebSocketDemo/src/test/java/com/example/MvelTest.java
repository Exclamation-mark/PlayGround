package com.example;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
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
        map.put("c", 10);

        Object object = MVEL.eval(expression, map);
        Assertions.assertNotNull(object);
        Assertions.assertTrue(object instanceof Boolean);
        Assertions.assertEquals(true, object);

        Object greR = MVEL.eval("c >= 10", map);
        Assertions.assertNotNull(greR);
        Assertions.assertTrue(greR instanceof Boolean);
        Assertions.assertEquals(true, greR);

        Object greR2 = MVEL.eval("c * 2 > 20", map);
        Assertions.assertNotNull(greR2);
        Assertions.assertTrue(greR2 instanceof Boolean);
        Assertions.assertEquals(false, greR2);
    }

    @Test
    public void testOgnl() {
        RuleBook ruleBook = RuleBookBuilder.create()
                .addRule(rule -> rule.withNoSpecifiedFactType()
                        .then(f -> System.out.print("Hello "))
                        .then(f -> System.out.println("World")))
                .build();
        ruleBook.run(new FactMap());
    }
}

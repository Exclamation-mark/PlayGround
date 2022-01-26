package com.example;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class MvelTest {

    @Test
    public void testStream() {
        Stream<Integer> stream = Stream.of(1, 9, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 18, null);
        stream.filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .filter(v -> v > 5 && v % 2 == 0)
                .distinct()
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void testPre(){
        String expression = "a == null && b == nil ";
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", null);
        map.put("b", null);
        map.put("c", 10);

        Object object = MVEL.eval(expression, map);
        validate(object);

        Object greR = MVEL.eval("c >= 10", map);
        validate(greR);

        Object greR2 = MVEL.eval("c * 2 >= 20", map);
        validate(greR2);
        System.out.println("end");
    }

    public void validate(Object v) {
        Assertions.assertNotNull(v);
        Assertions.assertTrue(v instanceof Boolean);
        Assertions.assertEquals(true, v);
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

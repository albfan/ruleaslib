package org.acme.lib.test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.acme.lib.Hello;
import javax.inject.Inject;

import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;



import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@QuarkusTest
public class RuleTest {

    @Inject
    RuleUnit<Hello> ruleUnit;

    @Test
    public void testHelloEndpoint() {


        Hello hello = new Hello();
        hello.getStrings().add("hello");

        RuleUnitInstance<Hello> instance = ruleUnit.createInstance(hello);

        instance.fire();

        List<Map<String, Object>> a = 
        instance.executeQuery("hello");
   
        String allValues = a.stream()
        .flatMap(map -> map.values().stream()) 
        .map(Object::toString)
        .collect(Collectors.joining(" "));

         assertEquals("hello world", allValues);


    }
}
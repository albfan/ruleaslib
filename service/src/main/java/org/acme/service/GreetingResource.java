package org.acme.service;


import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;

import org.acme.lib.Hello;

@Path("/hello")
public class GreetingResource {

    @Inject
    RuleUnit<Hello> ruleUnit;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
       
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
        
       return allValues;
    }
}
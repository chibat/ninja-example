package controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

import ninja.Result;
import ninja.Results;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;

import com.google.common.collect.Maps;
import com.google.inject.Inject;

public class Add2Controller {

    @Inject
    private HttpServletRequest request;

    public Result input() {
        return Results.html().render(newMap());
    }

    public Result calculate(@JSR303Validation Form form, Validation validation) {

        Map<String, Object> map = newMap();

        if (validation.hasViolations()) {
            List<FieldViolation> beanViolations = validation.getBeanViolations();
            map.put("errors", beanViolations);
            map.put("arg1", form.arg1);
            map.put("arg2", form.arg2);
            return Results.html().render(map)
                    .template("views/Add2Controller/input.ftl.html");
        }

        map.put("result",
                Integer.parseInt(form.arg1) + Integer.parseInt(form.arg2));
        return Results.html().render(map);
    }

    private Map<String, Object> newMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("contextPath", request.getContextPath());
        return map;
    }

    public static class Form {
        
        @NotBlank
        @Digits(integer=9,fraction=0)
        public String arg1;
        
        @NotBlank
        @Digits(integer=9,fraction=0)
        public String arg2;
    }
}

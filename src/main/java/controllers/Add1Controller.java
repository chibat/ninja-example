package controllers;

import java.util.Map;

import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.validation.IsInteger;
import ninja.validation.Required;
import ninja.validation.Validation;

import com.google.common.collect.Maps;

public class Add1Controller {

    public Result input() {
        return Results.html();
    }

    public Result calculate(@Param("arg1") @Required @IsInteger String arg1,
            @Param("arg2") @Required @IsInteger String arg2,
            Validation validation) {

        Map<String, Object> map = Maps.newHashMap();

        if (validation.hasViolations()) {
            map.put("errors", validation.getFieldViolations());
            map.put("arg1", arg1);
            map.put("arg2", arg2);
            return Results.html().render(map)
                    .template("views/Add1Controller/input.ftl.html");
        }

        map.put("result", Integer.parseInt(arg1) + Integer.parseInt(arg2));
        return Results.html().render(map);
    }
}

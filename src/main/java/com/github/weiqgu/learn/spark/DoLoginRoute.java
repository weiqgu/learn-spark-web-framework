package com.github.weiqgu.learn.spark;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DoLoginRoute extends AbstractTemplateRoute {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String name = request.queryParams("name");
        String password = request.queryParams("password");
        if ("demo".equals(name) && "password".equals(password)) {
            request.session().attribute("user", "demo");
            response.redirect("/protected/");
            return null;
        } else {
            return super.handle(request,response);
        }
    }

    @Override
    String getTemplateName() {
        return "/templates/login.vm";
    }

    @Override
    Map<String, Object> getModel(Request request) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", "Invalid credential, login failed! Try again ...");
        return model;
    }
}

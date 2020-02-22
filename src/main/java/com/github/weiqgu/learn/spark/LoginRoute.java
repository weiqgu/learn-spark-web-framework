package com.github.weiqgu.learn.spark;

import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class LoginRoute extends AbstractTemplateRoute {
    @Override
    String getTemplateName() {
        return "templates/login.vm";
    }

    @Override
    Map<String, Object> getModel(Request request) {
        return new HashMap<>();
    }
}

package com.github.weiqgu.learn.spark;

import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class StaticTemplateRoute extends AbstractTemplateRoute {
    private String template;

    public StaticTemplateRoute(String template) {
        this.template = template;
    }

    @Override
    String getTemplateName() {
        return template;
    }

    @Override
    Map<String, Object> getModel(Request request) {
        return new HashMap<>();
    }
}

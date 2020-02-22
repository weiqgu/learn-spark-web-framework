package com.github.weiqgu.learn.spark;

import spark.Request;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class GreetingHtmlRoute extends AbstractTemplateRoute  {

    @Override
    String getTemplateName() {
        return "templates/greeting.vm";
    }

    @Override
    Map<String, Object> getModel(Request request) {
        Map<String, Object> model = new HashMap<>();
        String name = request.queryMap().get("name").value();

        model.put("name", name);
        model.put("time", ZonedDateTime.now().toString());
        return model;
    }
}

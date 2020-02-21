package com.github.weiqgu.learn.spark;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class GreetingHtmlRoute implements Route  {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String, Object> model = new HashMap<>();
        String name = request.queryMap().get("name").value();

        model.put("name", name);
        model.put("time", ZonedDateTime.now().toString());
        return new VelocityTemplateEngine()
                .render(new ModelAndView(model, "templates/greeting.vm"));
    }
}

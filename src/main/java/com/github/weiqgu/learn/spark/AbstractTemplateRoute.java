package com.github.weiqgu.learn.spark;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public abstract class AbstractTemplateRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return new VelocityTemplateEngine()
                .render(new ModelAndView(getModel(request), getTemplateName()));
    }

    abstract String getTemplateName();

    abstract Map<String, Object> getModel(Request request);
}

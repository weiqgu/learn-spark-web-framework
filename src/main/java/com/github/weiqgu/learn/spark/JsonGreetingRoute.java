package com.github.weiqgu.learn.spark;

import spark.Request;
import spark.Response;
import spark.Route;

public class JsonGreetingRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String name = request.queryMap().get("name").value();
        response.type("application/json");
        return new Greeting().setTo(name);
    }
}

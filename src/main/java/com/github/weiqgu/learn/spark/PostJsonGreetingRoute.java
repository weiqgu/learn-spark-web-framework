package com.github.weiqgu.learn.spark;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostJsonGreetingRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Gson gson = new Gson();
        Greeting g = gson.fromJson(body, Greeting.class);
        response.type("application/json");
        return new GreetingReply(g);
    }
}

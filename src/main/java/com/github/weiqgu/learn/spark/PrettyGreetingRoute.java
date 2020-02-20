package com.github.weiqgu.learn.spark;

import spark.Request;
import spark.Response;
import spark.Route;

public class PrettyGreetingRoute implements Route {
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        // set the response content type as text/html
        response.type("text/html");
        return "<h1 style='color: red'>Greetings from Spark!</h1>";
    }
}

package com.github.weiqgu.learn.spark;


import spark.Request;
import spark.Response;
import spark.Route;

/**
 * The route implementation returns a plain text response
 */
public class GreetingRoute implements Route {
    /**
     *
     * @param request The http request object
     * @param response The http response object
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return "Greetings from Spark!";
    }
}

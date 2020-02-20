package com.github.weiqgu.learn.spark;

import spark.Spark;

public class WebApplication {
    private static final int DefaultPort = 8080;

    public static void main(String[] args) {
        int port = DefaultPort;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.err.println(String.format("Invalid port:  " + args[0]));
            }
        }

        System.out.println(String.format("Web application will start at port : %s", port));

        // Listen to the port
        Spark.port(port);


        // For GET /greeting, use GreetingRoute to handle the request
        Spark.get("/greeting", new GreetingRoute());

        // For GET /pretty-greeting, use GreetingRoute to handle the request
        Spark.get("/pretty-greeting", new PrettyGreetingRoute());
    }
}

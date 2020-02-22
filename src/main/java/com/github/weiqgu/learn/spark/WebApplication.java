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

        // Set public folder
        Spark.staticFileLocation("/public");

        // Add a filter, that for any /protected/ url, this filter will be run before
        // the route, here it checks if the user has logged in by checking if the
        // session contains a user attribute
        // If the no such session attribute exists, the browser will be redirected
        // to login page
        Spark.before("/protected/*", (request, response) -> {
            String userName = request.session().attribute("user");
            System.out.println("Username: " + userName);
            if (userName == null || userName.length() == 0) {
                response.redirect("/login");
            }
        });

        // For GET /greeting, use GreetingRoute to handle the request
        Spark.get("/greeting", new GreetingRoute());

        // For GET /pretty-greeting, use GreetingRoute to handle the request
        Spark.get("/pretty-greeting", new PrettyGreetingRoute());

        // For GET /json-greeting, use JsonGreetingRoute to handle the request to return a json response
        Spark.get("/json-greeting", new JsonGreetingRoute(), new JsonTransformer());

        // For POST /json-greeting, use JsonGreetingRoute to handle the request to return a json response
        Spark.post("/json-greeting", "application/json", new PostJsonGreetingRoute(), new JsonTransformer());

        Spark.get("/greeting.html", new GreetingHtmlRoute());

        Spark.get("/login", new LoginRoute());
        Spark.post("/doLogin", new DoLoginRoute());
        Spark.get("/logout", (request, response) -> {
            request.session().invalidate();
            response.redirect("/");
            return null;
        });

        Spark.get("/protected/", new StaticTemplateRoute("/templates/protected.vm"));

    }
}

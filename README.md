# Step1
##Git commands
 
 * Create a branch
 ```bash
  git branch <branch name> 
 ```
 
 * Check out branch
 
 ```bash
   git checkout <branch name> 
 ```

 * Check local repo status
 ```bash
    git status
  ```


### Gradle
Gradle a java build and dependency management tool, similar to npm to javascript.

To do the build:

```bash
  ./gradlew build 
```

To run the application, which start the WebApplication with default port 8080
```bash
  ./gradlew run
```

To run the application on a different port:
```bash
  ./gradlew run --args="8000"
```

Once the application starts, use the browser enter the address:

```
   http://localhost:8080/greeting
```

The browser should show: **Greetings from Spark!**

  
Enter the URL:
```
   http://localhost:8080/pretty-greeting
```

The browser should show red big **Greetings from Spark!**. This is html snippet. Check the chrome inspection for both urls.

Gradle build file is **build.gradle** (similar to package.json in npm). The file describes the dependent library and other
build information.  There are two things to pay attention in the build.gradle in this example:
1. **mainClassName** indicates the class name for the application entry point (which has the main method)
 ```
 mainClassName = 'com.github.weiqgu.learn.spark.WebApplication'
```

2. The dependent library of spark:
```
    dependencies {
        implementation 'com.sparkjava:spark-core:2.9.1'
    }
```

  If more library is needed, it should be added inside the dependencies bracket
  
  
  #Step2
  
  ## Add gson library for json support
  
  Add the gson library in the build.gradle under dependencies
  ```
      implementation 'com.google.code.gson:gson:2.8.6'
  ```
  
  ## Add a route /json-greeting
  This route also has *ResponseTransformer* implementation **JsonTransformer** which transforms java object to a json.
  
  ## Query parameter
  JsonGreetingRoute uses *request.queryMap* to get the query parameter in the url. For example, 
  ```
     http://localhost:8080/json-greeting?name=john
  ```
  The name is a query parameter that will be retrieved by:
  ```
    String name = request.queryMap().get("name").value();
  ```
  The *name* will have value "john" for the above URL.
  
  ## Accept incoming json
  *PostjsonGreetingRoute* is configured to accept POST http request with accept type of application/json
  ```
   Spark.post("/json-greeting", "application/json", new PostJsonGreetingRoute(), new JsonTransformer());    
  ```
  This means the application will handle POST request on /json-greeting, and the request accept the application/json as
  response.
  
  Try use postman to send request with different Accept Header
   
  #Step 3
  Using template to generate response html.  The template contains place holder which will be replaced 
  by provided data during the run of application.
  
  Check the *GreetingHtmlRoute* class and *src/main/resources/templates/greeting.vm* template.  The Velocity
  template engine merges the data with template. *$name* is place holder tag, and *#if* *#else* are logic
  operation tag 
  
  #Step 4
  
  ### Redirect
    
   *response.redirect(location)* this will redirect browser to the new *location* URL  
    
  ### Filter
  Spark.before can define a filter that runs before any request is routed to Routes.  We can use this for example to check
  if the user is logged in for any protected URLs
  
  ### Session
  Session is created on Server side for requests comes from the same browser. We can store user information in session 
  after login and use it check if the user has logged in
  
  ### Login
  /login route is displaying the login page
  /doLogin is the route that handle the user login, i.e. check user name/password, and redirect to protected url if login is successful,
  or login page for invalid login
  
  ### Abstrat class
  As returning a page from template is commonly used, and the logic of doing that is common, an abstract class
  *AbstractTemplateRoute* is created to do the common render logic. Subclasses now only implement two abstract methods:
  * *getTemplateName*: which returns the template name
  * *getModel*: which returns the Model that will merge with template name
  This way, the render code is shared among on the subclasses
    
  *GreetinghtmlRoute* is refactored to use this newly created Abstract class as well as *LoginRoute*.
  
  ### Override superclass method
  *DoLoginRoute* extends the *AbstractTemplateRoute*, but it has some conditional logic, if the login succeeds, then it should redirects
  to /protedted url, and if it fails, it should render a templated login page.  So it has to override the *handle* method implemented in
  the *AbstractTemplateRoute*
  
  ### Static contents
  *Spark.staticFileLocation* defines all the static file locations that Spark can directly serves.
  
  ### StaticTemplateRoute
  Another concrete subclass from *AbstractTemplateRoute*, this is used for static template which doesn't need to any data to merged in.
  This is normally not very useful (why not just use the static content).  This is to workaround a problem that the Spark filter is 
  applied after static file is served, so we can't put protected content in the static file location, as user can access them without
  login
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
   
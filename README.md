# Overview

This package allows you to query the API tracker T411. 

Project is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Implemented methods: 
* Authentication 
* Search 
* Top100 
* TopToday 
* TopWeek 
* TopMonth 
* Profile 

# Usage 
You can integrate it in your project using maven: 
```xml
<dependency>
  <groupId>com.giboxlab</groupId>
  <artifactId>t411api</artifactId>
  <version>0.1</version>
</dependency>
```

Then just initialize the main class as below: 
```java
T411Api api = new T411Api(); 
api.auth ("user", "pass"); 
api.search ("avatar");
```



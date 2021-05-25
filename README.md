This is a minimal reproduction demo for the reported spring-hateoas issue
[#1541](https://github.com/spring-projects/spring-hateoas/issues/1541).

### The working state

The code in the `main` branch is in "broken" state.
To convert the project to "working" state, please update spring boot version in `build.gradle` from `2.4.5` to `2.5.0`.
Note: there are three places in the file to be updated.
Important: The updated version updates the transitive `spring-hateoas` dependency from `1.3.1` to `1.2.5`.

Tests should run successfully via

```shell
$ ./gradlew build
BUILD SUCCESSFUL
```

### Broken state
Running tests fails with `HttpMessageConversionException`. Stack trace:

```
$ ./gradlew build --info

Task :test FAILED

BookControllerTest > getBook() FAILED
    org.springframework.web.util.NestedServletException: 
    Request processing failed; nested exception is org.springframework.http.converter.HttpMessageConversionException:
    Type definition error: [simple type, class sun.security.util.ObjectIdentifier]; 
    nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
        No serializer found for class sun.security.util.ObjectIdentifier and no properties discovered to create
        BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        (through reference chain: org.example.demo.BookDto["publicKey"]->
        sun.security.ec.ECPublicKeyImpl["algorithmId"]->
        sun.security.x509.AlgorithmId["oid"])
    
 Caused by:
        org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, 
        class sun.security.util.ObjectIdentifier]; nested exception is 
        com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class 
        sun.security.util.ObjectIdentifier and no properties discovered to create BeanSerializer
        (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        ...
        
 Caused by:
        com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class 
        sun.security.util.ObjectIdentifier and no properties discovered to create BeanSerializer 
        (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) 
        ...
```

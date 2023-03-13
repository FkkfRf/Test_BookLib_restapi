package specs;

import helpers.CustomApiListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification okResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .log(STATUS)
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification createdResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .log(STATUS)
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification noContentResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification badRequestResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(404)
            .build();
}


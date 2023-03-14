package specs;

import helpers.CustomApiListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecs {
    public static ResponseSpecification okResponseSpec200 = new ResponseSpecBuilder()
            .log(ALL)
            .log(STATUS)
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification createdResponseSpec201 = new ResponseSpecBuilder()
            .log(ALL)
            .log(STATUS)
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification noContentResponseSpec204 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification badRequestResponseSpec400 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification notFoundResponseSpec404 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(404)
            .build();
}


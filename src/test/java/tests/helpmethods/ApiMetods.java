package tests.helpmethods;

import io.qameta.allure.Step;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.RequiestSpecs.bookRequestSpec;
import static specs.ResponseSpecs.okResponseSpec200;

public class ApiMetods {

    @Step("Получить Id последней добавленной книги")
    public String getMaxId() {
        List<Integer> idList =
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(okResponseSpec200)
                        .extract()
                        .jsonPath().getList("books.id");

        return Collections.max(idList).toString();
    }
}

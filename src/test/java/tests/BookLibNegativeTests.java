package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.RequiestSpecs.bookRequestSpec;
import static specs.ResponseSpecs.*;

@Tag("Negative")
@Severity(SeverityLevel.NORMAL)
@Feature("Negative")
public class BookLibNegativeTests {
    @Test
    @Story("GET")
    @DisplayName("Получить полный список книг")
    void getBooksListWithBadUriTest() {
        step("Отправить GET запрос по неправильному Uri", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get("http://localhost:5000/api/book")
                        .then()
                        .spec(notFoundResponseSpec));

    }

}

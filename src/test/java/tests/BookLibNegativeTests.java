package tests;

import helpers.DataForTests;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.lombok.BookBody;
import models.lombok.NegativeBookBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.helpmethods.ApiMetods;

import static helpers.DataForTests.bookName;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.RequiestSpecs.bookRequestSpec;
import static specs.ResponseSpecs.*;

@Tag("Negative")
@Severity(SeverityLevel.NORMAL)
@Feature("Negative")
public class BookLibNegativeTests {
    ApiMetods apiMetods = new ApiMetods();

    @Test
    @Story("GET")
    @DisplayName("Получить полный список книг по неправильному Uri")
    public void getBooksListWithBadUriTest() {
        step("Отправить GET запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get("http://localhost:5000/api/book")
                        .then()
                        .spec(notFoundResponseSpecHTML404));
    }

    @Test
    @Story("GET")
    @DisplayName("Получить описание книги с несуществующим id")
    public void getBookWithBadIdTest() {

        String bookId = apiMetods.getMaxId() + 1;

        step("Отправить GET запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get("/" + bookId)
                        .then()
                        .spec(notFoundResponseSpec404));
    }

    @Test
    @Story("POST")
    @DisplayName("Добавить новую книгу по неправильному Uri")
    public void addBookWithBadUriTest() {
        BookBody bookBody = step("Задать имя создаваемой книги", () -> new BookBody());
        bookBody.setName(" ");

        step("Отправить POST запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post("http://localhost:5000/api/book")
                        .then()
                        .spec(notFoundResponseSpecHTML404));
    }

    @Test
    @Story("POST")
    @DisplayName("Добавить новую книгу c неправильным телом JSON")
    public void addBookWithBadBodyTest() {
        NegativeBookBody bookBody = step("Задать имя создаваемой книги", () -> new NegativeBookBody());
        bookBody.setNames(" ");

        step("Отправить POST запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(badRequestResponseSpec400));
    }

    @Test
    @Story("POST")
    @DisplayName("Добавить новую книгу c name = null")
    public void addBookWithNullNameTest() {
        BookBody bookBody = step("Задать имя создаваемой книги", () -> new BookBody());
        bookBody.setName(null);

        step("Отправить POST запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(badRequestResponseSpec400));
    }

    @Test
    @Story("PUT")
    @DisplayName("Обновить описание книги с несуществующим id")
    public void updateBookWithBadIdTest() {

        String bookId = apiMetods.getMaxId() + 1;
        BookBody bookBodyNewBody = step("Задать автора, имя, год, электр  для обновления книги", () ->
                new BookBody());
        bookBodyNewBody.setAuthor(DataForTests.bookAuthor);
        bookBodyNewBody.setName(bookName);
        bookBodyNewBody.setYear(DataForTests.bookYear);
        bookBodyNewBody.setIsElectronicBook(true);

        step("Отправить PUT запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBodyNewBody)
                        .when()
                        .put("/" + bookId)
                        .then()
                        .spec(notFoundResponseSpec404));
    }

    @Test
    @Story("DELRTE")
    @DisplayName("Удалить книгу с несуществующим id")
    public void deleteBookWithBadIdTest() {

        String bookId = apiMetods.getMaxId() + 1;

        step("Отправить DELETE запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .delete("/" + bookId)
                        .then()
                        .spec(notFoundResponseSpec404));
    }
}

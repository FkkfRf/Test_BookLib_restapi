package tests;

import helpers.DataForTests;
import io.qameta.allure.*;
import models.lombok.Book;
import models.lombok.BookBody;
import models.lombok.BooksList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.helpmethods.GetIdList;

import static helpers.DataForTests.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequiestSpecs.bookRequestSpec;
import static specs.ResponseSpecs.createdResponseSpec201;
import static specs.ResponseSpecs.okResponseSpec200;

@Severity(SeverityLevel.CRITICAL)
@Feature("Positive")
public class BookLibPositiveTests {
    GetIdList getIdList = new GetIdList();

    @Test
    @Story("GET")
    @DisplayName("Получить полный список книг")
    void getBooksListTest() {
        BooksList booksData = step("Отправить GET запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(okResponseSpec200)
                        .extract().as(BooksList.class));

        step("Проверить значения первого элемента списка", () -> {
            assertThat(booksData.getBooks().get(0).getId()).isEqualTo("1");
            assertThat(booksData.getBooks().get(0).getAuthor()).isEqualTo("Роберт Мартин");
        });
    }

    @Test
    @Story("GET")
    @DisplayName("Получить id книги по basePath")
    void getBooksIdTest() {
        String bookId = "2";
        Book bookData = step("Отправить GET запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .get("/" + bookId)
                        .then()
                        .spec(okResponseSpec200)
                        .extract().as(Book.class));

        step("Проверить соответствие basePath и Id", () -> {
            assertThat(bookData.getBook().getId()).isEqualTo(bookId);
        });
    }

    @Test
    @Story("POST")
    @DisplayName("Добавить новую книгу")
    void addBookTest() {
        BookBody bookBody = step("Задать имя создаваемой книги", () -> new BookBody());
        bookBody.setName(bookName);

        Book bookData = step("Отправить POST запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(createdResponseSpec201)
                        .extract().as(Book.class));

        step("Проверить название(name) созданной книги", () -> {
            assertEquals(bookName, bookData.getBook().getName());
        });

        step("возврат к исходному - удаление добавленной книги", () -> {
            String bookId = getIdList.getMaxId();
            given().spec(bookRequestSpec)
                    .when()
                    .delete("/" + bookId)
                    .then().spec(okResponseSpec200);
        });
    }

    @Test
    @Story("PUT")
    @DisplayName("Oбновить описание для последней добавленной книги")
    void updateLastBookTest() {
        BookBody bookBody = step("Задать name='Some name' для создаваемой книги", () -> new BookBody());
        bookBody.setName("Some name");

        step("Добавить новую книгу", () ->
                given().spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(createdResponseSpec201));

        String bookId = getIdList.getMaxId();
        System.out.println(bookId);

        BookBody bookBodyNewBody = step("Задать автора, имя, год, электр  для обновления книги", () ->
                new BookBody());
        bookBodyNewBody.setAuthor(DataForTests.bookAuthor);
        bookBodyNewBody.setName(bookName);
        bookBodyNewBody.setYear(DataForTests.bookYear);
        bookBodyNewBody.setIsElectronicBook(true);

        Book bookData = step("Отправить PUT запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBodyNewBody)
                        .when()
                        .put("/" + bookId)
                        .then()
                        .spec(okResponseSpec200)
                        .extract().as(Book.class));

        step("Проверить соответствие значений name, author, year. isElectronicBook заданным", () -> {
            assertEquals(bookAuthor, bookData.getBook().getAuthor());
            assertEquals(bookName, bookData.getBook().getName());
            assertEquals(bookYear, bookData.getBook().getYear());
            assertEquals(true, bookData.getBook().isIsElectronicBook());
        });

        step("возврат к исходному - удаление добавленной книги", () ->
                given().spec(bookRequestSpec)
                        .when()
                        .delete("/" + bookId)
                        .then().spec(okResponseSpec200));
    }

    @Test
    @Story("DELETE")
    @DisplayName("Удалить последнюю добавленную книгу")
    void deleteLastBookTest() {
        BookBody bookBody = new BookBody();
        bookBody.setName(bookName);

        step("Добавить новую книгу", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then().spec(createdResponseSpec201));

        String bookId = getIdList.getMaxId();

        step("Отправить DELETE запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .delete("/" + bookId)
                        .then()
                        .spec(okResponseSpec200));
    }
}



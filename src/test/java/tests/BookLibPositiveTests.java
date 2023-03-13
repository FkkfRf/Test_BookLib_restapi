package tests;

import helpers.DataForTests;
import io.qameta.allure.*;
import models.lombok.Book;
import models.lombok.BookBody;
import models.lombok.BooksList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.helpmethods.GetIdList;

import static helpers.DataForTests.bookName;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequiestSpecs.bookRequestSpec;
import static specs.ResponseSpecs.createdResponseSpec;
import static specs.ResponseSpecs.okResponseSpec;


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
                        .spec(okResponseSpec)
                        .extract().as(BooksList.class));

        step("Проверка значений", () -> {
            assertThat(booksData.getBooks().get(0).getId()).isEqualTo("1");
            assertThat(booksData.getBooks().get(0).getAuthor()).isEqualTo("Роберт Мартин");
        });

    }

    @Test
    @Story("POST")
    @DisplayName("Добавить новую книгу")
    void addBookDataTest() {
        BookBody bookBody = step("Задать имя создаваемой книги", () -> new BookBody());
        bookBody.setName(bookName);

        Book bookData = step("Отправить POST запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(createdResponseSpec)
                        .extract().as(Book.class));

        step("Проверка значений", () -> {
            assertEquals(bookName, bookData.getBook().getName());
        });
    }

    @Test
    @Story("PUT")
    @DisplayName("Oбновить информацию о книге по ее id")
    void updateBookTest() {
        BookBody bookBody = step("Задать имя создаваемой книги", () -> new BookBody());
        bookBody.setName(bookName);

        step("Добавить новую книгу", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(createdResponseSpec));


        String bookId = getIdList.getMaxId();
        System.out.println(bookId);

        BookBody bookBodyNewBody = step("Задать автора, имя, год, электр  для обновления книги", () -> new BookBody());
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
                        .spec(okResponseSpec)
                        .extract().as(Book.class));

        assertEquals(bookName, bookData.getBook().getName());
    }

    @Test
    @Story("DELETE")
    @DisplayName("Удалить последнюю добавленную книгу по Id")
    void getLastBookTest() {
        BookBody bookBody = new BookBody();
        bookBody.setName(bookName);

        step("Добавить новую книгу", () ->
                given()
                        .spec(bookRequestSpec)
                        .body(bookBody)
                        .when()
                        .post()
                        .then()
                        .spec(createdResponseSpec));

        String bookId = getIdList.getMaxId();

        step("Отправить DELETE запрос", () ->
                given()
                        .spec(bookRequestSpec)
                        .when()
                        .delete("/" + bookId)
                        .then()
                        .spec(okResponseSpec)
        );
    }
}



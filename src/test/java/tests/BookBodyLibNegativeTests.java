package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("Negative")
@Severity(SeverityLevel.NORMAL)
@Feature("Negative")
public class BookBodyLibNegativeTests {

    @Test
    @DisplayName("Список книг. Позитивный тест")
    void getBooksListTest() {

    }
}

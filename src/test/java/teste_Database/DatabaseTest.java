package teste_Database;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DatabaseTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
        Configuration.headless = false;
    }

    DatabasePage page = new DatabasePage();

    @BeforeEach
    public void openPage() {
        page.open();
    }

    @Test
    public void testFirstMovieData() {

        page.movieGrid.get(0).shouldBe(visible);
        page.movieGrid.get(0).shouldHave(text("Law Abiding Citizen"));
        page.movieGrid.get(1).shouldHave(text("2009"));
        page.movieGrid.get(2).shouldHave(text("F. Gardy Gray"));
    }
}
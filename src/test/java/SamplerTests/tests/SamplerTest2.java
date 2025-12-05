package SamplerTests.tests;

import SamplerTests.pages.SamplerPage2;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SamplerTest2 {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demo.vaadin.com/sampler/";
        Configuration.holdBrowserOpen = false;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openBrowser() {
        open("/");
    }

    @Test
    public void testSuite_InteractionAccess() {
        SamplerPage2 page = new SamplerPage2();

        page.enterInteraction();

        $(byText("Notification")).shouldBe(visible);
    }

    @Test
    public void testSuite_DataInputAccess() {
        SamplerPage2 page = new SamplerPage2();

        page.enterDataInput();

        $(byText("Text field")).shouldBe(visible);
    }
}

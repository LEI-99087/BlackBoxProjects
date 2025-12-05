package teste_Form;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest {

    private static final String USER_FIRST_NAME = "Aluno";
    private static final String USER_LAST_NAME = "Iscte";
    private static final String USER_LOGIN = "iscteuser";
    private static final String USER_PWD = "SecurePassword123!";
    private static final String USER_EMAIL = "aluno@iscte-iul.pt";

    private final FormPage formPage = new FormPage();

    @BeforeAll
    public static void globalSetup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setup() {
        formPage.openPage();
    }

    @Test
    @DisplayName("Deve registar utilizador sem subscrição de marketing")
    public void testRegistrationStandard() {
        formPage.submitForm(USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PWD, false, null);

        String message = formPage.getSuccessMessage();
        assertTrue(message.contains("Data saved"), "A mensagem de sucesso deve aparecer");
        assertTrue(message.contains(USER_LOGIN), "O nome de utilizador deve estar na mensagem");
    }

    @Test
    @DisplayName("Deve registar utilizador com email e marketing")
    public void testRegistrationWithMarketing() {
        formPage.submitForm(USER_FIRST_NAME, USER_LAST_NAME, USER_LOGIN, USER_PWD, true, USER_EMAIL);

        String message = formPage.getSuccessMessage();
        assertTrue(message.contains("Data saved"));
    }

}
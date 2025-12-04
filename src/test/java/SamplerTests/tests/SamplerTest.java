package SamplerTests.tests;

import SamplerTests.pages.SamplerPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SamplerTest {

    @BeforeAll
    public static void setUp() {
        // Configuração do Browser
        Configuration.browser = "chrome";
        // URL CORRETO:
        Configuration.baseUrl = "https://demo.vaadin.com/sampler/";

        // Mantém o browser aberto se o teste falhar (útil para debug)
        Configuration.holdBrowserOpen = false;

        // Integração com Allure Report (para gerar relatórios bonitos depois)
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openBrowser() {
        // Garante que começamos sempre do início
        open("/");
    }

    // Test Suite 7: Aceder a um componente do tipo Data presentation
    @Test
    public void testSuite7_DataPresentationAccess() {
        SamplerPage page = new SamplerPage();

        // 1. Ação: Tentar aceder à categoria Data Presentation
        page.enterDataPresentation();

        // 2. Validação: Verificar se, após o clique, algo aconteceu.
        // Como o sampler é dinâmico, verificamos se o elemento clicado continua visível
        // ou se apareceu algum sub-menu (ex: "Grid" que é filho de Data Presentation)
        $(byText("Grid")).shouldBe(visible);
    }

    // Test Suite 8: Aceder a um componente do tipo Basic features
    @Test
    public void testSuite8_BasicFeaturesAccess() {
        SamplerPage page = new SamplerPage();

        // 1. Ação: Tentar aceder à categoria Basic Features
        page.enterBasicFeatures();

        // 2. Validação: Verificar se um sub-item (ex: "Button" ou "Label") aparece
        // Isto confirma que o menu abriu corretamente
        $(byText("Button")).shouldBe(visible);
    }
}
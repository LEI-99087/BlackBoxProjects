package SamplerTests.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

// page_url = https://demo.vaadin.com/sampler/
public class SamplerPage {

    // --- Elementos (Locators) ---
    // Procuramos os links pelo texto visível na página
    private SelenideElement dataPresentationLink = $(byText("Data presentation"));
    private SelenideElement basicFeaturesLink = $(byText("Basic features"));

    // Um elemento genérico para validar se a página carregou (ex: o logo ou título principal)
    // No Sampler, geralmente há um título ou descrição que muda
    private SelenideElement pageTitle = $(".v-label-h1"); // Exemplo comum em Vaadin, ou $("h1")

    // --- Métodos de Ação ---

    // Método para abrir a página (usa o BaseURL configurado no teste)
    public void openPage() {
        open("/");
    }

    public void enterDataPresentation() {
        // Clica no link e espera que ele esteja visível antes de clicar
        dataPresentationLink.shouldBe(visible).click();
    }

    public void enterBasicFeatures() {
        basicFeaturesLink.shouldBe(visible).click();
    }

    // --- Métodos de Leitura/Validação ---

    // Verifica se a opção "Data presentation" está visível na página (útil para asserções)
    public boolean isDataPresentationVisible() {
        return dataPresentationLink.isDisplayed();
    }

    // Verifica se a opção "Basic features" está visível
    public boolean isBasicFeaturesVisible() {
        return basicFeaturesLink.isDisplayed();
    }
}
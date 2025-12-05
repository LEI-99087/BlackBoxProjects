package SamplerTests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class SamplerPage2 {

    // --- Elementos (Locators) ---

    // Categoria "Interaction"
    private SelenideElement interactionLink = $(byText("Interaction"));

    // Categoria "Data input"
    private SelenideElement dataInputLink = $(byText("Data input"));

    // Título genérico da página (confirma carregamento)
    private SelenideElement pageTitle = $(".v-label-h1");


    // --- Métodos de Ação ---

    public void openPage() {
        open("/");
    }

    public void enterInteraction() {
        interactionLink.shouldBe(visible).click();
    }

    public void enterDataInput() {
        dataInputLink.shouldBe(visible).click();
    }


    // --- Métodos de Validação ---

    public boolean isInteractionVisible() {
        return interactionLink.isDisplayed();
    }

    public boolean isDataInputVisible() {
        return dataInputLink.isDisplayed();
    }
}
package adicionarCategoria;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class EditCategoriesPage {


    private final SelenideElement addCategory = $("vaadin-button[disableonclick='true']");


    private SelenideElement getNameField() {
        SelenideElement field = $$("vaadin-text-field").last();
        field.shouldBe();
        return field;
    }


    private SelenideElement getNameInput(SelenideElement field) {
        WebElement input = executeJavaScript(
                "return arguments[0].shadowRoot.querySelector('input[part=\"value\"]')",
                field
        );
        return $(input);
    }

    // Adiciona uma nova categoria
    public void addCategory(String name) {
        addCategory.click();
        sleep(100);
        SelenideElement field = getNameField();
        SelenideElement input = getNameInput(field);
        input.setValue(name);


        executeJavaScript(
                "const input = arguments[0];" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "input.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', bubbles: true }));" +
                        "input.dispatchEvent(new KeyboardEvent('keyup', { key: 'Enter', bubbles: true }));",
                input
        );

        sleep(100);
    }

    public String getValue(){
        return getNameInput(getNameField()).getValue();
    }



}

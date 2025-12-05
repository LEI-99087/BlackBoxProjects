package teste_Form;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {

    // URL base
    private static final String URL = "https://vaadin-form-example.demo.vaadin.com";

    private final SelenideElement firstNameInput = $$("vaadin-text-field").get(0);
    private final SelenideElement lastNameInput = $$("vaadin-text-field").get(1);
    private final SelenideElement usernameInput = $$("vaadin-text-field").get(2);

    private final SelenideElement passwordInput = $$("vaadin-password-field").get(0);
    private final SelenideElement confirmPasswordInput = $$("vaadin-password-field").get(1);

    private final SelenideElement marketingCheckbox = $("vaadin-checkbox");
    private final SelenideElement emailInput = $("vaadin-email-field");

    private final SelenideElement submitButton = $("vaadin-button");

    public void openPage() {
        Selenide.open(URL);
    }

    public void submitForm(String fName, String lName, String uName, String pwd, boolean subscribeMarketing, String email) {

        setText(firstNameInput, fName);
        setText(lastNameInput, lName);
        setText(usernameInput, uName);
        setText(passwordInput, pwd);
        setText(confirmPasswordInput, pwd);

        if (subscribeMarketing) {
            marketingCheckbox.scrollIntoView(true).shouldBe(visible).click();

            emailInput.shouldBe(visible);
            setText(emailInput, email);
        }

        submitButton.click();
    }

    private void setText(SelenideElement element, String value) {
        Selenide.executeJavaScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                element,
                value
        );
    }

    public String getSuccessMessage() {
        SelenideElement notification = $("vaadin-notification-card[theme='success']");
        notification.shouldBe(visible);

        return $(notification.getWrappedElement()
                .getShadowRoot()
                .findElement(By.cssSelector("div[part='content']"))).getText();
    }
}
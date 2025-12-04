package adicionarProduto;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement usernameField = $("input[aria-labelledby^='vaadin-text-field-label-0']");
    private final SelenideElement passwordField = $("input[aria-labelledby^='vaadin-password-field-label-1']");
    private final SelenideElement loginButton   = $("vaadin-button[tabindex='0']");

    public void openLoginPage() {
        open("https://vaadin-bookstore-example.demo.vaadin.com/");
    }

    public void loginAsAdmin() {
        usernameField.setValue("admin");
        passwordField.setValue("admin");
        loginButton.click();
    }
}

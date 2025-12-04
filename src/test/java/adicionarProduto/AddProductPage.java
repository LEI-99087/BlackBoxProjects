package adicionarProduto;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class AddProductPage {

    public SelenideElement vaadinTextFieldName =
            $x("//vaadin-text-field[@style='width: 100%;']");

    public SelenideElement vaadinTextFieldPrice =
            $x("//vaadin-text-field[./span[@slot='suffix']]");

    public  SelenideElement checkBox_Mystery = $x("/html/body/vaadin-app-layout/vaadin-horizontal-layout[2]/div/vaadin-vertical-layout/vaadin-checkbox-group/vaadin-checkbox[4]");

    public SelenideElement getNameInput() {



        WebElement inputName = executeJavaScript(
                "return arguments[0].shadowRoot.querySelector('input')",
                vaadinTextFieldName
        );


        return $(inputName);
    }

    public SelenideElement getCheckBoxMysteryInput() {
        WebElement input = executeJavaScript(
                "return arguments[0].shadowRoot.querySelector('input[type=\"checkbox\"]')", // <- Garante que é um checkbox
                checkBox_Mystery
        );
        return $(input);
    }

    public SelenideElement getPriceInput() {



        WebElement inputPrice = executeJavaScript(
                "return arguments[0].shadowRoot.querySelector('input')",
                vaadinTextFieldPrice
        );

        return $(inputPrice);
    }

    public void fillName(String name) {
        getNameInput().setValue(name);
    }

    public void fillPrice(String price) {
        getPriceInput().setValue(price);
    }







    public void selectMystery() {
        checkBox_Mystery.click();
    }



    public void save() {
        $x("//vaadin-button[normalize-space()='Save']").click();
    }

    public boolean successIsVisible() {
        return $x("//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success')]")
                .isDisplayed();
    }
}

package adicionarProduto;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final SelenideElement newProductButton =
            $x("//vaadin-button[normalize-space()='New product']");

    public void openNewProductForm() {
        newProductButton.click();
    }
}

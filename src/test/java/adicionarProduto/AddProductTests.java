package adicionarProduto;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Selenide.*;

public class AddProductTests {

    @Test
    @Feature("Bookstore")
    @Story("Adicionar Produto")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida que é possível adicionar um produto após login")
    public void testAddProduct() throws InterruptedException {

        LoginPage login = new LoginPage();
        login.openLoginPage();  // cria WebDriver
        login.loginAsAdmin();

        HomePage home = new HomePage();
        home.openNewProductForm();

        AddProductPage product = new AddProductPage();
        product.fillName("O Capital");
        assertEquals("O Capital",product.getNameInput().getValue());
        product.fillPrice("25.99");
        assertEquals("25.99",product.getPriceInput().getValue());

        product.selectMystery();
        assertTrue(product.getCheckBoxMysteryInput().isSelected());
        product.save();

        assertTrue(product.successIsVisible());
    }
}

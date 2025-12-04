package adicionarCategoria;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddCategoryTest {

    @Test
    @Feature("Bookstore")
    @Story("Adicionar Categoria")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida que é possível adicionar uma categoria após login")
    public void testAddCategory() throws InterruptedException {

        LoginPage login = new LoginPage();
        login.openLoginPage();
        login.loginAsAdmin();

        HomePage home = new HomePage();
        home.editCategoriesPage();

        EditCategoriesPage categoryPage = new EditCategoriesPage();
        String newCategory = "Biografia";

        categoryPage.addCategory(newCategory);


        assertEquals("Biografia",categoryPage.getValue());
    }
}

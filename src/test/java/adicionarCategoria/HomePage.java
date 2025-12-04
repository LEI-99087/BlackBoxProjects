package adicionarCategoria;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    public SelenideElement adminButton = $("html > body > vaadin-app-layout > a:nth-of-type(3)");

    public void editCategoriesPage() {
        adminButton.click();
    }


}

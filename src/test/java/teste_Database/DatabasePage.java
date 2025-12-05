package teste_Database;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

// page_url = https://vaadin-database-example.demo.vaadin.com/
public class DatabasePage {

    private static final String URL = "https://vaadin-database-example.demo.vaadin.com/";

    public ElementsCollection movieGrid = $$("vaadin-grid-cell-content");

    public void open() {
        Selenide.open(URL);
    }


}
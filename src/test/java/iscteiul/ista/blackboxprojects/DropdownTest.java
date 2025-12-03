package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DropdownTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void setup() {
        driver.get("https://sales.jetbrains.com/hc/en-gb/requests/new?ticket_form_id=66372");
        try {
            WebElement acceptCookies = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button.ch2-allow-all-btn")));
            acceptCookies.click();
            System.out.println("Banner fechado com sucesso!");
        } catch (Exception e) {
            System.out.println("O banner não apareceu");
        }
    }

    @Test
    public void testDropdown() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        By dropdownButton = By.cssSelector("a[aria-labelledby='request_custom_fields_28102551_label']");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();


        By options = By.cssSelector("div.nesty-panel ul li");
        WebElement secondOption = wait.until(ExpectedConditions.elementToBeClickable(options));
        secondOption = driver.findElements(options).get(1); // segunda opção
        secondOption.click();


        String valueAfter = driver.findElement(dropdownButton).getText();
        System.out.println("Texto selecionado: " + valueAfter);
        assertNotEquals("-", valueAfter);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

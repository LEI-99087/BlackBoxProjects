package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboxTest {

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
    public void testCheckbox() {


        By checkbox = By.id("request_custom_fields_360000004124");

        WebElement cb = driver.findElement(checkbox);

        cb.click(); // clica

        assertTrue(cb.isSelected());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

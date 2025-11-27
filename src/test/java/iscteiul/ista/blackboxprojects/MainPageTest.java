package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.jetbrains.com/");

        try {
            List<WebElement> cookieContainers =
                    driver.findElements(By.cssSelector("div.ch2-container"));
            if (!cookieContainers.isEmpty()) {
                WebElement container = cookieContainers.get(0);
                List<WebElement> buttons = container.findElements(By.tagName("button"));
                for (WebElement button : buttons) {
                    if (button.isDisplayed() && button.isEnabled()) {
                        button.click();
                        System.out.println("Cookie banner accepted.");
                        break;
                    }
                }
            }
        } catch (Exception ignored) {}

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() throws InterruptedException {

        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(
                                        By.cssSelector("input[data-test-id='search-input']"));

        //searchField.clear();
        searchField.sendKeys("Selenium");

        Thread.sleep(1000);

        assertEquals("Selenium", searchField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement
                                        (By.cssSelector("div[data-test='main-submenu-suggestion']"));

        Thread.sleep(666);
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));

        Thread.sleep(777);
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}

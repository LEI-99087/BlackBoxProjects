package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicContentTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_content");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDynamicContentChanges() {

        List<WebElement> initialTexts = driver.findElements(By.cssSelector("#content .row div:nth-child(2)"));
        assertFalse(initialTexts.isEmpty(), "Não encontrou o conteúdo inicial.");

        String firstTextBefore = initialTexts.get(0).getText();

        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));

        List<WebElement> updatedTexts = driver.findElements(By.cssSelector("#content .row div:nth-child(2)"));
        String firstTextAfter = updatedTexts.get(0).getText();

        assertNotEquals(firstTextBefore, firstTextAfter, "O conteúdo dinâmico não foi atualizado.");
    }
}

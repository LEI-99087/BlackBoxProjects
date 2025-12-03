package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NotificationMessagesTest {
    private WebDriver driver;
    private JetBrainsSupportPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        page = new JetBrainsSupportPage(driver);
        page.open();
        page.acceptCookiesIfPresent();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testNotificationMessages() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        page.emailInput.click();
        page.pressEnterOnEmail();

        wait.until(ExpectedConditions.visibilityOf(page.emailErrorNotification));

        sleep(5000);
        assertTrue(page.emailErrorNotification.isDisplayed(), "O erro nao é visível.");
    }

}

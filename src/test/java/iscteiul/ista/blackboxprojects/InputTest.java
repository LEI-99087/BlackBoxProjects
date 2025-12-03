package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class InputTest {
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
    public void testInputEmail() throws InterruptedException {
        String textToEnter = "Bom dia caros colegas e caros professores.";

        page.emailInput.sendKeys(textToEnter);

        sleep(5000);

        assertEquals(textToEnter, page.getEmailValue());
    }
}

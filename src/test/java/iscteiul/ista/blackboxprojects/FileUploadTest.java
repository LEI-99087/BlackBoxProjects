package iscteiul.ista.blackboxprojects;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor; // <-- Import for scrolling

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class FileUploadTest {
    private WebDriver driver;
    private static final String FILE_UPLOAD_URL = "https://intellij-support.jetbrains.com/hc/en-us/requests/new?ticket_form_id=66731";
    private static final String FILE_INPUT_ID = "request-attachments";
    private static final String TEST_FILE_NAME = "test_upload_file.txt";

    @BeforeAll
    public static void createTestFile() throws IOException {
        File testFile = new File(TEST_FILE_NAME);
        if (!testFile.exists()) {
            Files.write(testFile.toPath(), "This is a test file for upload.".getBytes());
        }
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(FILE_UPLOAD_URL);

        try {
            WebElement acceptCookies = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button.ch2-allow-all-btn")));
            acceptCookies.click();
            System.out.println("Cookies banner closed.");
        } catch (Exception e) {
            System.out.println("No cookies banner found or unable to close.");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFileUpload() {
        String filePath = new File(TEST_FILE_NAME).getAbsolutePath();
        WebElement fileInputElement = driver.findElement(By.id(FILE_INPUT_ID));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fileInputElement);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        fileInputElement.sendKeys(filePath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement uploadedFileLabel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[@id='request-attachments-pool']//*[contains(text(), '" + TEST_FILE_NAME + "')]"))
        );
        assertTrue(uploadedFileLabel.isDisplayed(), "The uploaded file name should be visible.");
        try {
            System.out.println("Test Passed! Pausing for a few seconds for visual confirmation...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void deleteTestFile() {
        File testFile = new File(TEST_FILE_NAME);
        if (testFile.exists()) {
            if (testFile.delete()) {
                System.out.println("Test file deleted successfully.");
            } else {
                System.out.println("Failed to delete test file.");
            }
        }
    }
}
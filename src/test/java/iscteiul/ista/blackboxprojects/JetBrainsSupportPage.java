package iscteiul.ista.blackboxprojects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JetBrainsSupportPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "button.ch2-allow-all-btn")
    private WebElement cookieButton;

    @FindBy(id = "request_anonymous_requester_email")
    public WebElement emailInput;

    @FindBy(id = "request_anonymous_requester_email_error")
    public WebElement emailErrorNotification;

    public JetBrainsSupportPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://intellij-support.jetbrains.com/hc/en-us/requests/new?ticket_form_id=66731");
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            btn.click();
        } catch (Exception e) {
            System.out.println("Nao apareceu cookie, pass...");
        }
    }

    public String getEmailValue() {
        return emailInput.getAttribute("value");
    }

    public void pressEnterOnEmail() {
        emailInput.sendKeys(Keys.ENTER);
    }

}

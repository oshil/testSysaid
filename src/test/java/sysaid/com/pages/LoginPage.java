package sysaid.com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class LoginPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(xpath = "//*[@id='tablogin']")
    WebElement loginBlock;

    @FindBy(name = "userName")
    WebElement userName;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath ="//*[@id='tablogin']//td[@class='ButtonLabel']/span")
    WebElement loginButton;

    @FindBy(name = "rememberMeForDisplay")
    WebElement rememberMeCheckbox;

    @FindBy(xpath ="//*[@id='waiting_message'][contains (text(),'Failed')]")
    WebElement messageError;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "https://automationtest1.qa.sysaidit.com/";
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage(WebDriver driver) {
        driver.get(PAGE_URL);
        return this;
    }
    public LoginPage openLoginPage() {
        Log.info("Opening login page");
        driver.get(PAGE_URL);
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(loginBlock);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public LoginPage waitUntilErrorMessageIsLogIsLoaded() {
        try {
            waitUntilElementIsLoaded(messageError);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(loginBlock);
    }

    public LoginPage fillEmailField(String user) {
        setElementText(userName, user);
        Log.info("entering userName: " + user + " ");
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        setElementText(passwordField, password);
        Log.info("entering password: " + password + " ");
        return this;
    }

    public LoginPage clickOnLogin() {
        clickElement(loginButton);
        return this;
    }

    public LoginPage clickOnCheckBox() {
        clickElement(rememberMeCheckbox);
        return this;
    }

    public LoginPage login(String user, String password) {
        openLoginPage();
        waitUntilLoginPageIsLoaded();
        fillEmailField(user);
        fillPasswordField(password);
        clickOnLogin();
        return this;
    }

    public boolean alertMessageWrongCredentials() {
        return exists(messageError);
    }
}

package sysaid.com.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class MainPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    //1
    @FindBy(xpath = "//*[@id='user-links']/li[2]/span[contains( text(),'qatest')]")
    WebElement userName;
    @FindBy(xpath = "//*[@class='drop service-desk']")
    WebElement serviceDesk;
    @FindBy(xpath = "//*[@href='#/HelpDesk.jsp?fromId=IncidentsList']/span")
    WebElement incidents;

    //2
    @FindBy(xpath = "//*[@class='Maxed Container']//a/span")
    WebElement addNewIncident;

    //Mandatory Fields
    @FindBy(xpath = "//*[@class='newListSelected problem_type_CustomSelect']//span")
    WebElement category1;
    @FindBy(xpath = "//*[@class='newListSelected subcategory_CustomSelect']//span")
    WebElement category2;
    @FindBy(xpath = "//*[@class='newListSelected thirdLevelCategory_CustomSelect']//span")
    WebElement category3;

    @FindBy(id="title")
    WebElement title;
    @FindBy(id="desc")
    WebElement desc;
    @FindBy(xpath = "//*[@id='68_status']//*[@class='selectedTxt']/span")
    WebElement status;
    @FindBy(xpath = "//*[@id='68_selectUrgency']//*[@class='selectedTxt']/span")
    WebElement urgency;
    @FindBy(xpath = "//*[@id='68_selectImpact']//*[@class='selectedTxt']/span")
    WebElement impact;
    @FindBy(xpath = "//*[@id='68_selectPriority']//*[@class='selectedTxt']/span")
    WebElement priority;
    @FindBy(xpath = "//*[@id='68_requestUser']//*[@class='selectedTxt']/span")
    WebElement requestUser;

    //General
    @FindBy(id="notes")
    WebElement notes;
    @FindBy(xpath = "//*[@class=\"Button3Parts\"]//span[contains(text(),'Add a note')]")
    WebElement addANoteButton;
    @FindBy(xpath = "//*[@class='Button3Parts']//span[text()='Add']")
    WebElement attachmentsButton;
    @FindBy(id="ApplyBtn")
    WebElement applyButton;
    @FindBy(xpath = "//*[@id='user-links']/li[2]")
    WebElement userDropDown;
    @FindBy(xpath = "//*[@id='personal-menu']/li[8]/a")
    WebElement logout;

    //Tabs
    @FindBy(xpath = "//*[@id='tabs']//li[1]")
    WebElement generalTab;
    @FindBy(xpath = "//*[@id='tabs']//li[3]")
    WebElement activitiesTab;

    //Activities
    @FindBy(id="sract_fromTime")
    WebElement startDay;
    @FindBy(name="sract_fromTime_time")
    WebElement startTime;
    @FindBy(id="sract_toTime")
    WebElement endDay;
    @FindBy(name="sract_toTime_time")
    WebElement endTime;
    @FindBy(xpath = "//*[@class='FieldBox']//span[text()='Add']")
    WebElement addActButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "https://automationtest1.qa.sysaidit.com/Home.jsp";
        PropertyConfigurator.configure("log4j.properties");
    }

    public MainPage openMainPage(WebDriver driver, String baseUrl) {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage waitUntilMainPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(serviceDesk);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnMainPage() {
        Log.info("Wait for load Main page");
        waitUntilMainPageIsLoaded();
        return exists(serviceDesk);
    }

    public boolean isLoggedIn() {

        return exists(userName);
    }

    public MainPage clickOnServiceDesk() {
        Log.info("Click on Service Desk button");
        clickElement(serviceDesk);
        return this;
    }

    public MainPage clickOnIncidenstButton() {
        clickElement(incidents);
        return this;
    }

    public MainPage clickOnAddNewIncidenstButton() {
        clickElement(addNewIncident);
        return this;
    }

    public MainPage clickOnUserDropDowntButton() {
        clickElement(userDropDown);
        return this;
    }

    public MainPage clickOnLogoutButton() {
        clickElement(logout);
        return this;
    }

}
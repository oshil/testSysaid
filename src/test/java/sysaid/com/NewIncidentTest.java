package sysaid.com;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sysaid.com.pages.LogLog4j;
import sysaid.com.pages.LoginPage;
import sysaid.com.pages.MainPage;

import static org.testng.AssertJUnit.assertTrue;

public class NewIncidentTest extends TestNgTestBase{

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;
    public MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        PropertyConfigurator.configure("log4j.properties");
        try {
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CreateIncidentSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            mainPage
                    .clickOnServiceDesk()
                    .clickOnIncidenstButton()
                    .clickOnAddNewIncidenstButton()
                    .fillCategory1Field("1")
                    .fillCategory2Field("2")
                    .fillCategory3Field("3")
                    .fillTitleField("abc")
                    .fillDescriptionField("asdfgh")
                    .fillRequestUserField("qatest")
                    .clickOnActivitiesTab()
                    .fillStartDayField("09-09-2015")
                    .fillStartTimeField("19:50")
                    .fillEndDayField("10-09-2015")
                    .fillEndTimeField("15:15")
                    .clickOnAddActButton()
                    .clickOnGeneralTab()
                    .clickOnApplyButton();
            assertTrue("The incident was not created", mainPage.isIncidentCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("the incident was created");
    }

    @Test
    public void CreateIncidentNegative() {
        Log.info("Checking that error message appears, if data not entered in mandatory fields");
        try {
            mainPage
                    .clickOnServiceDesk()
                    .clickOnIncidenstButton()
                    .clickOnAddNewIncidenstButton()
                    .fillCategory1Field("")
                    .fillCategory2Field("2")
                    .fillCategory3Field("3")
                    .fillTitleField("abc")
                    .fillDescriptionField("asdfgh")
                    .fillRequestUserField("qatest")
                    .clickOnActivitiesTab()
                    .fillStartDayField("09-09-2015")
                    .fillStartTimeField("19:50")
                    .fillEndDayField("10-09-2015")
                    .fillEndTimeField("15:15")
                    .clickOnAddActButton()
                    .clickOnGeneralTab()
                    .clickOnApplyButton();
            assertTrue("The incident was created, without mandatory fields filled", mainPage.isIncidentCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("the incident was not created, as expected");
    }
}

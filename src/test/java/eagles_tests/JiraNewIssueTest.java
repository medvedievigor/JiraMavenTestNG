package eagles_tests;

import Steps.BeforeTestSteps;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.NewIssuePage;

public class JiraNewIssueTest {
    public  static NewIssuePage newIssuePage;
    public  static DashboardPage dashboardPage;
    public    static BeforeTestSteps beforeTestSteps;
    public  static  String summary ="[Test Automation] QAAUTO6-T1_test02";
    public  static  String issueType ="Task";


    @BeforeMethod

    public void setupBeforeTest(){
        beforeTestSteps =new BeforeTestSteps();
        newIssuePage = new NewIssuePage();
        dashboardPage = new DashboardPage();
        beforeTestSteps.setupAndLogin();

    }



    @Test

    public void CreateIssueTest () {

        dashboardPage.clickCreateIssueButton();
        newIssuePage.atRequiredPage(); //проверяет, что окно Create issue открыто.
        newIssuePage.enterProjectName("QAAUTO-6 (QAAUT6)");
        newIssuePage.enterIssueType(issueType);
        newIssuePage.enterSummary(summary);
        newIssuePage.enterDescription("Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-1 task\"");
        newIssuePage.clickAssignToMe();
        newIssuePage.clickCreateButton();
        newIssuePage.isIssueCreated(); //проверяет, что окно Create issue правильно заполнено и закрыто.
    }
//    @AfterMethod
//    public void close1(){
//        WebDriverRunner.getWebDriver().quit();
//    }
}

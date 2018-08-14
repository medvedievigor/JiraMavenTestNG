package eagles_tests;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import helpers.LoadProperties;


import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;



public class JiraTests {
    public  static NewIssuePage newIssuePage;
    public  static DashboardPage dashboardPage;
    public  static SearchPage searchPage;
    public  static  String summary ="[Test Automation] QAAUTO6-T1_test02";
    public  static  String issueType ="Task";

    @BeforeTest
    public void setup () {
    LoginPage loginPage = new LoginPage();
    LoadProperties loadProperties =new LoadProperties();
    Configuration.remote ="http://localhost:4444/wd/hub";
    Configuration.browser ="chrome";
    Configuration.timeout = 9000;

    open (loadProperties.getProjectProperty());

            loginPage.enterUserName(loadProperties.getUserName());
            loginPage.enterPassword(loadProperties.getPassword());
            loginPage.clickSubmitButton();

     newIssuePage = new NewIssuePage();
     dashboardPage = new DashboardPage();
     searchPage=new SearchPage();
     }

        @Test(priority = 1)
        public void CreateIssueTest () {

            dashboardPage.clickCreateIssueButton();

            newIssuePage.enterProjectName("QAAUTO-6 (QAAUT6)");
            newIssuePage.enterIssueType(issueType);
            newIssuePage.atRequiredPage(); //проверяет, что окно Create issue открыто.
            newIssuePage.enterSummary(summary);
            newIssuePage.enterDescription("Testing issue created according to http://jira.hillel.it:8080/browse/QAAUT6-1 task\"");
            newIssuePage.clickAssignToMe();
            newIssuePage.clickCreateButton();
            newIssuePage.isIssueCreated(); //проверяет, что окно Create issue правильно заполнено и закрыто.
        }
            // new eagles_tests
            @Test(priority = 2)
            public  void findCreatedIssueTest (){
                String summaryOfSearchedTask ="Test Automation QAAUTO6-T1_test02";
                String jqlRequestForSearching = "project = QAAUT6 AND assignee = currentUser() AND  summary ~ \"\\\\["+summaryOfSearchedTask+"\\\\]\"AND type ="+issueType+" ORDER BY createdDate DESC";


            dashboardPage.clickIssueButton();
            dashboardPage.clickSearchForIssuesButton();
            searchPage.clickAdvancedButton();
            searchPage.fillJQLField(jqlRequestForSearching);
            searchPage.clickSearchButton();

            $(byXpath("//li[@title =\""+summary+"\"]")).should(Condition.visible);//проверяет, что иcкомый issue есть в списке
            // или можно через
            //Assert.assertTrue($(byXpath("//li[@title =\""+summary+"\"]")).isDisplayed(),"This task not exist!");

        }
    }
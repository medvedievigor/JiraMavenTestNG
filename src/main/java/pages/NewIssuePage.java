package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class NewIssuePage {
    private String inputProjectXpath = "//*[@id=\"project-field\"]";
    private String inputIssueTypeXpath = "//*[@id=\"issuetype-field\"]";
    private String inputSummaryXpath = "//*[@id=\"summary\"]";
    private String inputDescriptionID = "description";
    private String clickTextButtonXpath = "//*[@class=\"tabs-menu\"]/li[2]";
    private String assignToMeButtonXpath = "//*[@id=\"assign-to-me-trigger\"]";
    private String createButtonXpath = "//*[@id=\"create-issue-submit\"]";
    //этот селектор нужен был для iframe
   // private String frameXpath = "//[@class=\"mce-edit-area mce-container mce-panel mce-stack-layout-item mce-first\"]/iframe ";

    public boolean atRequiredPage () {
        $(byXpath("//*[@id=\"create-issue-dialog\"]/div[1]/h2")).should(Condition.visible);
        return true;
    }
    public  void enterProjectName(String projectName){
        $(byXpath(inputProjectXpath)).sendKeys(projectName);
        $(byXpath(inputProjectXpath)).pressEnter();
    }
    public  void enterIssueType(String issueType){
        $(byXpath(inputIssueTypeXpath)).sendKeys(issueType);
        $(byXpath(inputIssueTypeXpath)).pressEnter();
    }

    public  void enterSummary(String summary){
        $(byXpath(inputSummaryXpath)).clear();
        $(byXpath(inputSummaryXpath)).sendKeys(summary);


        }

    // Это снячало был iframe, потом они его изменили...
    public  void enterDescription(String description){
           //Selenide.switchTo().frame($(byXpath(frameXpath)));
            $(byXpath(clickTextButtonXpath)).click();
           $(byId(inputDescriptionID)).clear();
           $(byId(inputDescriptionID)).sendKeys(description);
           //Selenide.switchTo().defaultContent();
    }
    public void clickAssignToMe(){
        $(byXpath(assignToMeButtonXpath)).click();
    }
    public void clickCreateButton(){
        $(byXpath(createButtonXpath)).click();
    }
    public boolean isIssueCreated () {
        $(byXpath("//*[@id=\"create-issue-dialog\"]/div[1]/h2")).shouldNot(Condition.visible); // проверяет, что после нажатия кнопки, окно закрылось.
        // или можно через
        //Assert.assertFalse($(byXpath("//*[@id=\"create-issue-dialog\"]/div[1]/h2")).isDisplayed(),"some of mandatory fields is empty");
        return true;
    }

}

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class MainTest {

    private SelenideElement wikiPageLink = $("#wiki-tab");
    private SelenideElement h1 = $(".anchor").parent();
    private SelenideElement softAssertLink = $x("//a[text() = 'SoftAssertions']");
    private SelenideElement wikiFilter = $("#wiki-pages-filter");
    private SelenideElement show2moreButton = $("button.f6");
    private ElementsCollection filterResults = $$("ul.m-0 a");

    public void someActions() {
        Selenide.open("https://github.com/selenide/selenide");
        wikiPageLink.click();
        h1.shouldHave(Condition.exactText("Welcome to the selenide wiki!"));
    }

    @Test
    public void test() {
        someActions();
        show2moreButton.click();
        filterResults.shouldHave(CollectionCondition.containExactTextsCaseSensitive("SoftAssertions"));

    }

    @Test
    public void test2() {
        Selenide.inNewBrowser(() -> {
            someActions();
            wikiFilter.setValue("SoftAssertions");
            $$("a.py-1").findBy(Condition.text("SoftAssertions")).shouldHave(Condition.exactText("SoftAssertions"));
        });
    }

}





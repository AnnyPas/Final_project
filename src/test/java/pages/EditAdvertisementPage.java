package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class EditAdvertisementPage {

    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement saveButton =
            $$("button").findBy(exactText("Сохранить изменения"));

    public void changeName(String newName) {
        nameInput.shouldBe(visible).setValue(newName);
        saveButton.click();
        // Не прерываем запрос сохранения новым переходом или refresh().
        webdriver().shouldHave(url(Configuration.baseUrl + "/"));
        new MainPage().waitForAdvertisementListLoaded();
    }
}

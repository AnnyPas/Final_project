package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Configuration;
import model.Advertisement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CreateAdvertisementPage {

    private final SelenideElement nameInput =
            $("input[name='name']");

    private final SelenideElement categoryInput =
            $("input[name='category']");

    private final SelenideElement cityInput =
            $("input[name='city']");

    private final SelenideElement descriptionInput =
            $("textarea[name='description']");

    private final SelenideElement priceInput =
            $("input[name='price']");

    private final SelenideElement publishButton =
            $$("button").findBy(text("Опубликовать"));

    public void createAdvertisement(Advertisement advertisement) {
        nameInput.setValue(advertisement.getName());

        selectCategory(advertisement.getCategory());
        selectCity(advertisement.getCity());

        descriptionInput.setValue(advertisement.getDescription());
        priceInput.setValue(advertisement.getPrice());

        publishButton.click();
        webdriver().shouldHave(url(Configuration.baseUrl + "/"));
        new MainPage().waitForAdvertisementListLoaded();
    }

    private void selectCategory(String category) {
        categoryInput.parent().$("button").click();

        categoryInput
                .parent()
                .parent()
                .$$("button")
                .findBy(text(category))
                .click();

        categoryInput.shouldHave(value(category));
    }

    private void selectCity(String city) {
        cityInput.parent().$("button").click();

        cityInput
                .parent()
                .parent()
                .$$("button")
                .findBy(text(city))
                .click();

        cityInput.shouldHave(value(city));
    }
}
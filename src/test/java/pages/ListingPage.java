package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Configuration;
import model.Advertisement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ListingPage {

    private final SelenideElement editAdvertisementButton =
            $$("button")
                    .findBy(exactText("Редактировать объявление"));

    private final SelenideElement deleteAdvertisementButton =
            $$("button")
                    .findBy(exactText("Удалить"));

    public void openEditPage() {
        editAdvertisementButton.click();
    }

    public void deleteAdvertisement() {
        deleteAdvertisementButton.click();
        webdriver().shouldHave(url(Configuration.baseUrl + "/"));
        new MainPage().waitForAdvertisementListLoaded();
    }

    public void checkAdvertisementName(String name) {
        $$("h1")
                .findBy(exactText(name))
                .shouldBe(visible);
    }

    public void checkAdvertisementDetails(Advertisement advertisement) {
        $$("h1")
                .findBy(exactText(advertisement.getName()))
                .shouldBe(visible);

        $$("span")
                .findBy(text(advertisement.getCategory()))
                .shouldHave(
                        text(advertisement.getCategory()),
                        text(advertisement.getCondition())
                );

        $$("h3")
                .findBy(exactText(advertisement.getCity()))
                .shouldBe(visible);

        $$("p")
                .findBy(exactText(advertisement.getDescription()))
                .shouldBe(visible);

        $$("h1")
                .findBy(exactText(advertisement.getPrice() + " ₽"))
                .shouldBe(visible);
    }
}

package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.Advertisement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final SelenideElement loginAndRegistrationButton =
            $$("button").findBy(exactText("Вход и регистрация"));
    private final SelenideElement logoutButton =
            $$("button").findBy(exactText("Выйти"));
    private final SelenideElement createAdvertisementButton =
            $$("button").findBy(exactText("Разместить объявление"));
    private final SelenideElement searchInput = $("input[name='name']");
    private final SelenideElement categoryInput = $("input[name='category']");
    private final SelenideElement cityInput = $("input[name='city']");
    private final SelenideElement applyButton =
            $$("button").findBy(exactText("Применить"));
    private final SelenideElement paginationCounter =
            $$("p").findBy(matchText("^\\d+ из \\d+$"));

    public void openLoginPage() {
        loginAndRegistrationButton.click();
    }

    public void checkUserIsLoggedIn() {
        logoutButton.shouldBe(visible);
    }

    public void checkUserIsNotLoggedIn() {
        loginAndRegistrationButton.shouldBe(visible);
        logoutButton.shouldNotBe(visible);
    }

    public void openCreateAdvertisementPage() {
        createAdvertisementButton.click();
    }

    public void openAdvertisementList() {
        open("/");
        waitForAdvertisementListLoaded();
    }

    public void waitForAdvertisementListLoaded() {
        searchInput.shouldBe(visible);
        applyButton.shouldBe(visible);
        paginationCounter.shouldBe(visible);
    }

    public void searchAdvertisement(Advertisement advertisement, String name) {
        waitForAdvertisementListLoaded();
        searchInput.setValue(name);
        selectOption(categoryInput, advertisement.getCategory());
        selectOption(cityInput, advertisement.getCity());
        applyButton.click();
    }

    public void checkAdvertisementIsVisible(String name) {
        advertisementTitles(name).first().shouldBe(visible);
    }

    public void openAdvertisement(String name) {
        advertisementTitles(name).first().shouldBe(visible).click();
    }

    public void checkAdvertisementIsAbsentInSearchResults(Advertisement advertisement) {
        searchAdvertisement(advertisement, advertisement.getName());
        // Счётчик результата проверяется до отрицательной проверки карточек.
        paginationCounter.shouldHave(exactText("0 из 0"));
        advertisementTitles(advertisement.getName()).shouldHave(size(0));
    }

    private ElementsCollection advertisementTitles(String name) {
        return $$(".card h2").filterBy(exactText(name));
    }

    private void selectOption(SelenideElement input, String value) {
        input.parent().$("button").click();
        input.parent().parent().$$("button").findBy(exactText(value)).click();
        input.shouldHave(com.codeborne.selenide.Condition.value(value));
    }
}

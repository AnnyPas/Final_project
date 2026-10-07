package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Advertisement;
import pages.CreateAdvertisementPage;
import pages.EditAdvertisementPage;
import pages.ListingPage;
import pages.MainPage;
import utils.TestDataGenerator;

public class AdvertisementSteps {

    private final TestContext context;
    private final MainPage mainPage = new MainPage();
    private final CreateAdvertisementPage createAdvertisementPage = new CreateAdvertisementPage();
    private final EditAdvertisementPage editAdvertisementPage = new EditAdvertisementPage();
    private final ListingPage listingPage = new ListingPage();

    public AdvertisementSteps(TestContext context) {
        this.context = context;
    }

    @Given("подготовлены данные нового объявления")
    public void advertisementDataPrepared() {
        context.setAdvertisement(TestDataGenerator.generateAdvertisement());
    }

    @Given("пользователь открыл форму создания объявления")
    public void userOpenedCreateAdvertisementPage() {
        mainPage.openCreateAdvertisementPage();
    }

    @Given("у пользователя есть опубликованное объявление")
    public void userHasPublishedAdvertisement() {
        advertisementDataPrepared();
        userOpenedCreateAdvertisementPage();
        userCreatesAdvertisement();
        advertisementCreatedSuccessfully();
    }

    @When("пользователь создает объявление с валидными данными")
    public void userCreatesAdvertisement() {
        createAdvertisementPage.createAdvertisement(context.getAdvertisement());
    }

    @Then("объявление успешно создано")
    public void advertisementCreatedSuccessfully() {
        Advertisement advertisement = context.getAdvertisement();
        mainPage.searchAdvertisement(advertisement, advertisement.getName());
        mainPage.checkAdvertisementIsVisible(advertisement.getName());
        mainPage.openAdvertisement(advertisement.getName());
        listingPage.checkAdvertisementDetails(advertisement);
        // Прямое открытие /listing/{id} теряет данные карточки в этом сервисе.
        // Получаем свежий список и повторно открываем объявление через UI.
        openAdvertisementFromList(advertisement.getName());
        listingPage.checkAdvertisementDetails(advertisement);
    }

    @When("пользователь открывает свое объявление для редактирования")
    public void userOpensAdvertisementForEditing() {
        userOpensOwnAdvertisement();
        listingPage.openEditPage();
    }

    @When("пользователь изменяет название объявления")
    public void userChangesAdvertisementName() {
        String updatedName = context.getAdvertisement().getName() + " изменено";
        context.setUpdatedAdvertisementName(updatedName);
        editAdvertisementPage.changeName(updatedName);
    }

    @Then("изменения объявления успешно сохранены")
    public void advertisementChangesSaved() {
        String updatedName = context.getUpdatedAdvertisementName();
        openAdvertisementFromList(updatedName);
        listingPage.checkAdvertisementName(updatedName);
    }

    @When("пользователь открывает свое объявление")
    public void userOpensOwnAdvertisement() {
        String name = context.getAdvertisement().getName();
        openAdvertisementFromList(name);
        listingPage.checkAdvertisementName(name);
    }

    @When("пользователь удаляет свое объявление")
    public void userDeletesAdvertisement() {
        listingPage.deleteAdvertisement();
    }

    @Then("объявление больше не отображается")
    public void advertisementIsNotDisplayed() {
        mainPage.openAdvertisementList();
        mainPage.checkAdvertisementIsAbsentInSearchResults(context.getAdvertisement());
    }
    private void openAdvertisementFromList(String name) {
        mainPage.openAdvertisementList();
        mainPage.searchAdvertisement(context.getAdvertisement(), name);
        mainPage.checkAdvertisementIsVisible(name);
        mainPage.openAdvertisement(name);
    }
}

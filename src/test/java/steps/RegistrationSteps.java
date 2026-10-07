package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.TestDataGenerator;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationSteps {

    private final TestContext context;

    private final RegistrationPage registrationPage =
            new RegistrationPage();

    private final MainPage mainPage =
            new MainPage();

    private final LoginPage loginPage =
            new LoginPage();

    public RegistrationSteps(TestContext context) {
        this.context = context;
    }

    @Given("подготовлены уникальные данные нового пользователя")
    public void uniqueUserDataPrepared() {
        User user =
                TestDataGenerator.generateUser();

        context.setUser(user);
    }

    @Given("пользователь открыл страницу регистрации")
    public void userOpenedRegistrationPage() {
        open("/");

        mainPage.openLoginPage();
        loginPage.openRegistrationPage();
    }

    @When("пользователь регистрируется с уникальными данными")
    public void userRegistersWithUniqueData() {
        User user =
                context.getUser();

        registrationPage.register(
                user.getEmail(),
                user.getPassword(),
                user.getSubmitPassword()
        );
    }

    @Then("регистрация пользователя выполнена успешно")
    public void registrationIsSuccessful() {
        mainPage.checkUserIsLoggedIn();
    }

    @When("пользователь повторно регистрируется с данными существующего пользователя")
    public void userRegistersAgain() {
        User user =
                context.getUser();

        registrationPage.register(
                user.getEmail(),
                user.getPassword(),
                user.getSubmitPassword()
        );
    }

    @Then("отображается ошибка повторной регистрации")
    public void duplicateRegistrationErrorIsDisplayed() {
        registrationPage
                .checkDuplicateRegistrationRejected();

        mainPage.checkUserIsNotLoggedIn();
    }
}

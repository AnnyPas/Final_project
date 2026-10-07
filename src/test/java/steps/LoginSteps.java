package steps;

import api.UserApiClient;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import pages.LoginPage;
import pages.MainPage;
import utils.TestDataGenerator;

import static com.codeborne.selenide.Selenide.open;

public class LoginSteps {

    private final TestContext context;
    private final UserApiClient userApiClient = new UserApiClient();
    private final MainPage mainPage = new MainPage();
    private final LoginPage loginPage = new LoginPage();

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("зарегистрированный пользователь существует")
    public void registeredUserExists() {
        User user = TestDataGenerator.generateUser();
        userApiClient.createUser(user);
        context.setUser(user);
    }

    @Given("пользователь открыл страницу авторизации")
    public void userOpenedLoginPage() {
        open("/");
        mainPage.openLoginPage();
    }

    @Given("пользователь авторизован")
    public void userIsAuthorized() {
        open("/");
        mainPage.openLoginPage();
        User user = context.getUser();
        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        mainPage.checkUserIsLoggedIn();
    }

    @When("пользователь вводит корректные данные и выполняет вход")
    public void userLogsIn() {
        User user = context.getUser();
        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );
    }

    @Then("пользователь успешно авторизован")
    public void userIsLoggedIn() {
        mainPage.checkUserIsLoggedIn();
    }
}

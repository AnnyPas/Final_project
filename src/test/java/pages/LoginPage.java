package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {

    private final SelenideElement emailInput =
            $("input[placeholder='Введите Email']");

    private final SelenideElement passwordInput =
            $("input[placeholder='Пароль']");

    private final SelenideElement loginButton =
            $$("button").findBy(text("Войти"));

    private final SelenideElement registrationButton =
            $$("button").findBy(text("Нет аккаунта"));

    public void login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public void openRegistrationPage() {
        registrationButton.click();
    }
}
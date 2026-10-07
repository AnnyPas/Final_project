package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationPage {

    private final SelenideElement emailInput =
            $("input[placeholder='Введите Email']");

    private final SelenideElement passwordInput =
            $("input[placeholder='Пароль']");

    private final SelenideElement submitPasswordInput =
            $("input[placeholder='Повторите пароль']");

    private final SelenideElement createAccountButton =
            $$("button").findBy(text("Создать аккаунт"));

    private final SelenideElement errorMessage =
            $(byText("Ошибка"));

    public void register(
            String email,
            String password,
            String submitPassword
    ) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        submitPasswordInput.setValue(submitPassword);

        createAccountButton.click();
    }

    public void checkDuplicateRegistrationRejected() {
        errorMessage.shouldBe(visible);

        emailInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        submitPasswordInput.shouldBe(visible);
        createAccountButton.shouldBe(visible);
    }
}
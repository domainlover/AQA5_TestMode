package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BankTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void withStatusActive() {
        UserData userData = DataGenerator.statusActive();
        $("[class='input__box'] [name='login']").setValue(userData.getLogin());
        $("[class='input__box'] [name='password']").setValue(userData.getPassword());
        $("[class='button__content']").click();
        $$(".heading").find(exactText("Личный кабинет")).shouldBe(exist);
    }

    @Test
    void withStatusBlocked() {
        UserData userData = DataGenerator.statusBlocked();
        $("[class='input__box'] [name='login']").setValue(userData.getLogin());
        $("[class='input__box'] [name='password']").setValue(userData.getPassword());
        $("[class='button__content']").click();
        $(byText("Пользователь заблокирован")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void withInvalidLogin() {
        UserData userData = DataGenerator.invalidLogin();
        $("[class='input__box'] [name='login']").setValue(userData.getLogin());
        $("[class='input__box'] [name='password']").setValue(userData.getPassword());
        $("[class='button__content']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void withInvalidPassword() {
        UserData userData = DataGenerator.invalidPassword();
        $("[class='input__box'] [name='login']").setValue(userData.getLogin());
        $("[class='input__box'] [name='password']").setValue(userData.getPassword());
        $("[class='button__content']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }
}
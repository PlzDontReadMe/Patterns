package ru.netology;



import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);


        $("[placeholder=\"Город\"]").val(DataGenerator.Registration.generateUser("ru").getCity());//
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").val(firstMeetingDate);
        $(byName("name")).val(DataGenerator.Registration.generateUser("ru").getName());
        $(byName("phone")).val(DataGenerator.Registration.generateUser("ru").getPhone());
        $("[data-test-id=\"agreement\"].checkbox").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"success-notification\"]")
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15))
                .shouldHave(appear);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").val(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"]")
                .shouldHave(textCaseSensitive("Необходимо подтверждение У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15))
                .should(visible);
        $(byText("Перепланировать")).click();
        $("[data-test-id=\"success-notification\"]")
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(15))
                .shouldHave(visible);
    }
}


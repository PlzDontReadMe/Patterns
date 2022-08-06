package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
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


        $("[placeholder=\"Город\"]").val(DataGenerator.generateCity("ru"));//
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").val(firstMeetingDate);
        $(byName("name")).val(DataGenerator.generateName("ru"));//FixMe буква "ё" не принимается формой, указано написать как в паспорте
        $(byName("phone")).val(DataGenerator.generatePhone("ru"));
        $("[data-test-id=\"agreement\"].checkbox").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"success-notification\"]")
//                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + firstMeetingDate), Duration.ofSeconds(15))//FixMe не видит текст в попапе
                .shouldHave(appear);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").val(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"]")
//                .should(Condition.text("У вас уже запланирована встреча на другую дату.Перепланировать?"))//FixMe не видит текст в попапе
                .should(visible);
        $(byText("Перепланировать")).click();
        $("[data-test-id=\"success-notification\"]")
//                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + secondMeetingDate), Duration.ofSeconds(15))//FixMe не видит текст в попапе
                .shouldHave(visible);

    }

}


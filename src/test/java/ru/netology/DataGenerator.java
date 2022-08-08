package ru.netology;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.UtilityClass;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }
@UtilityClass
    public static class Registration {
    public static UserInfo generateUser(String locale) {
            return new UserInfo();
        }
    }
    @Data
    @NoArgsConstructor
    public static class UserInfo {
        String city = faker.address().cityName();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
    }

}




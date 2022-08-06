package ru.netology;


import com.github.javafaker.Faker;
import lombok.Data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        return faker.address().city();
    }

    public static String generateName(String locale) {
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().phoneNumber();
    }


//@UtilityClass
//    public static class Registration {
//    public static UserInfo generateUser(String locale) {
//            Faker faker = new Faker(new Locale(locale));
//            return new UserInfo(generateCity(locale),generateName(locale),generatePhone(locale));
//        }
//    }
//
//    @Data
//    public static class UserInfo {
//        String city;
//        String name;
//        String phone;
//    }
}




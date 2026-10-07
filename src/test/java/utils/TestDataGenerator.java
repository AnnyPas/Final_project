package utils;

import model.Advertisement;
import model.User;

import java.util.UUID;

public class TestDataGenerator {

    public static User generateUser() {
        String email =
                "test_" + UUID.randomUUID() + "@example.com";

        String password = "Password123";

        return new User(
                email,
                password,
                password
        );
    }
    public static Advertisement generateAdvertisement() {
        String uniquePart = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        return new Advertisement(
                "Тестовая книга " + uniquePart,
                "Книги",
                "Новый",
                "Москва",
                "Описание тестового объявления " + uniquePart,
                "100"
        );
    }
}
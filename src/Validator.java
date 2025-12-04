import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    /**
     * Метод який відповідає за валідацію імені користувача
     * @param name
     * @return помилки
     */
    public static List<String> validateLogin(String name) {
        List<String> errors = new ArrayList<>();
        String regex = "[a-zA-Z_\\d]{5,}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(name).matches()) {
            errors.add("Логін може містити тільки латинські літери, цифри та '_' і бути довжиною не менше 5 символів");
        }

        return errors;
    }

    /**
     * Метод який відповідає за валідацію паролю користувача
     * @param password
     * @return помилки
     */
    public static List<String> validatePassword(String password) {
        List<String> errors = new ArrayList<>();
        String regex = "[a-zA-Z@_$\\d]{6,}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(password).matches()) {
            errors.add("Пароль повинен містити не менше 6 символів, тільки латинські літери, цифри та символи '_ $ @'");
        }

        return errors;
    }

    /**
     * Метод який відповідає за валідацію пошти користувача
     * @param email
     * @return помилки
     */
    public static List<String> validateEmail(String email) {
        List<String> errors = new ArrayList<>();
        String regex = "[a-zA-Z\\d]+@[a-zA-Z]{4,}\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(email).matches()) {
            errors.add("Пошта має містити тільки латинські літери та цифри. Потрібний формат mail@domain.re");
        }

        return errors;
    }

    /**
     * Метод який відповідає за валідацію номеру телефону
     * @param phoneNumber
     * @return помилки
     */
    public static List<String> validateMobile(String phoneNumber) {
        List<String> errors = new ArrayList<>();
        String regex = "(\\d{10}|\\(\\d{3}\\)\\d{7}|\\+380\\d{9}|380\\d{9})";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(phoneNumber).matches()) {
            errors.add("Невірний формат номеру телефону! Приклади: 0506295430, (050)6295430, +380506295430, 380506295430");
        }

        return errors;
    }

    /**
     * Метод який відповідає за валідацію посилання
     * @param url
     * @return помилки
     */
    public static List<String> validateUrl(String url) {
        List<String> errors = new ArrayList<>();
        String regex = "https:\\/\\/[a-zA-Z0-9.-]+(/[a-zA-Z0-9./&=-]*)*";
        Pattern pattern = Pattern.compile(regex);

        if (!pattern.matcher(url).matches()) {
            errors.add("Невірний формат URL! Приклад: https://mysite.com");
        }

        return errors;
    }
}

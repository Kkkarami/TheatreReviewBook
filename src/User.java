/**
 * Клас-сутність Користувач, зберігає в собі id,ім'я користувача та хешований пароль
 * Містить гетери та сетери полів
 */
public class User {
    /** Унікальний ID користувача */
    private int id;
    /** Логін користувача */
    private String userName;
    /** Хешований пароль користувача */
    private String hashPassword;
    /** Пошта користувача*/
    private String email;
    /** Посилання на особисту сторінку*/
    private String url;
    /** ПНомер телефону користувача*/
    private String phoneNumber;


    /**
     * Конструктор користувача
     * @param id
     * @param userName
     * @param hashPassword
     * @param email
     * @param url
     * @param phoneNumber
     */
    public User(int id, String userName, String hashPassword, String email, String url, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.email = email;
        this.url = url;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Повертає айді
     * @return id користувача
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Повертає логін
     * @return логін користувача
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Повертає хешований пароль користувача
     * @return хешований пароль користувача
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * @param hashPassword
     */
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


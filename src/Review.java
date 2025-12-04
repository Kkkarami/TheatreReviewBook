/**
 * Клас відгуку, зберігає айді, айді користувача, айді вистави текст відгуку та дату
 */
public class Review {
    /**
     * id відгуку
     */
    private int id;
    /**
     * id користувача, який написав відгук
     */
    private int userId;
    /**
     * id вистави на яку написаний відгук
     */
    private int performanceId;
    /**
     * текст відгуку
     */
    private String text;
    /**
     * Дата коли написайний відгук
     */
    private String date;

    /**
     * Конструктор відгуку
     * @param id
     * @param userId
     * @param performanceId
     * @param text
     * @param date
     */
    public Review(int id, int userId, int performanceId, String text, String date) {
        this.id = id;
        this.userId = userId;
        this.performanceId = performanceId;
        this.text = text;
        this.date = date;
    }

    /**
     * Повертає айді відгуку
     * @return айді відгуку
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Повертає айді користувача який написав відгук
     * @return айді користувача який написав відгук
     */
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Повертає айді вистави на яку написаний відгук
     * @return айді вистави на яку написаний відгук
     */
    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    /**
     * Повертає текст відгуку
     * @return текст відгуку
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Повертає дату відгуку
     * @return дата відгуку
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

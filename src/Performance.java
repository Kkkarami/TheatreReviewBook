/**
 * Клас сутність вистави містить в собі айді вистави, її назву, та опис
 */
public class Performance {
    /**
     * Айді вистави
     */
    private int id;
    /**
     * Назва вистави
     */
    private String name;
    /**
     * Опис вистави
     */
    private String description;

    /**
     * Конструктор вистави
     * @param id
     * @param name
     * @param description
     */
    public Performance(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Повертає айді вистави
     * @return айді вистави
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Повертає назву вистави
     * @return назва вистанви
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Повертає опис вистави
     * @return опис вистави
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

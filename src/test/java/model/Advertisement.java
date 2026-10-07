package model;

public class Advertisement {

    private String name;
    private String category;
    private String condition;
    private String city;
    private String description;
    private String price;

    public Advertisement(
            String name,
            String category,
            String condition,
            String city,
            String description,
            String price
    ) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.city = city;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCondition() {
        return condition;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
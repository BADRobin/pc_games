package dci.j24e01.pc_games;

public class Game {
    private Long id;
    private String name;
    private Long categoryId;
    private Double price;
    private String categoryName;

    public Game(Long id, String name, Long categoryId, double price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public Game() {

    }

    public Game(Long id, String name, Long categoryId, Double price, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

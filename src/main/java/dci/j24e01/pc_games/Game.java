package dci.j24e01.pc_games;

public class Game {
    private Long id;
    private String name;
    private Long categoryId;
    private double price;

    public Game(Long id, String name, Long categoryId, double price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    public Game() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category_id=" + categoryId +
                ", price=" + price +
                '}';
    }
}

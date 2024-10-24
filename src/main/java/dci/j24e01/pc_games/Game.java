package dci.j24e01.pc_games;

public class Game {
    private Long id;
    private String name;
    private Long category_id;
    private double price;

    public Game(Long id, String name, Long category_id, double price) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.price = price;
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

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
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
                ", category_id=" + category_id +
                ", price=" + price +
                '}';
    }
}

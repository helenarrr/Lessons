package InternetShop;

import java.util.Random;

public class Item {
    private String name;
    private double price;
    private double cost;
    private Category category;

    enum Category {DRINKS, BAGS, SNEAKERS}

    enum Discount {
        BRONZE(0), SILVER(5), GOLD(10), SUPER(15), SUPER_GOLD(20);
        private final int discountItem;

        Discount(int discountItem) {
            this.discountItem = discountItem;

        }

        public int getDiscountItem() {
            return discountItem;
        }
    }

    public Item(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public double randomDiscount(double price) {
        return price - (price * (discount() / 100));
    }

    public double discount() {
        Discount[] discounts = Discount.values();
        return discounts[new Random().nextInt(discounts.length)].getDiscountItem();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}

package InternetShop;

public class Order {
    private Buyer buyer;
    private Item item;
    private int amountItem;
    private static int counter;

    public Order(Buyer buyer, Item item, int amountItem) {
        this.buyer = buyer;
        this.item = item;
        this.amountItem = amountItem;
        counter++;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmountItem() {
        return amountItem;
    }

    public void setAmountItem(int amountItem) {
        this.amountItem = amountItem;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Order.counter = counter;
    }


    @Override
    public String toString() {
        return "Order{" +
                "buyer=" + buyer +
                ", item=" + item +
                ", amountItem=" + amountItem +
                '}';
    }
}

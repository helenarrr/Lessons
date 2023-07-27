package InternetShop;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("guess", 10, Item.Category.BAGS));
        items.add(new Item("nike", 20, Item.Category.SNEAKERS));


        ArrayList<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("Alla", 20000));
        buyers.add(new Buyer("Ilia", 10000));

        Shop shop1 = new Shop(items, buyers);
        try {
            shop1.deal("Alla", "guess", 1);
            shop1.deal("Alla", "nike", 1);
            shop1.deal("Ilia", "nike", 1);
        } catch (NoBuyerException | NoItemException | IllegalAmountException | TooMuchSaleException e) {
            e.printStackTrace();
        }
        System.out.println(Order.getCounter());
    }
}



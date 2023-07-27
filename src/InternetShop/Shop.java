package InternetShop;

import java.util.ArrayList;

public class Shop {

    final ArrayList<Item> stock;
    final ArrayList<Buyer> customers;


    public Shop(ArrayList<Item> stock, ArrayList<Buyer> customers) {
        this.stock = stock;
        this.customers = customers;

    }

    public Order deal(String buyerName, String item, int amount) throws NoBuyerException, NoItemException, IllegalAmountException, TooMuchSaleException {
        Buyer tempBuyer = null;
        Item tempItem = null;

        if (amount <= 0) {
            throw new IllegalAmountException("Неверное количество товара!");
        }

        for (int i = 0; i < customers.size(); i++) {
            if (buyerName.equals(customers.get(i).getName())) {
                tempBuyer = customers.get(i);
                break;
            }
        }

        if (tempBuyer == null) {
            throw new NoBuyerException("Нет такого покупателя!");
        }

        for (int i = 0; i < stock.size(); i++) {
            if (item.equals(stock.get(i).getName())) {
                tempItem = stock.get(i);
                break;
            }
        }
        if (tempItem == null) {
            throw new NoItemException("В магазине нет такого товара!");
        }

        Order order = new Order(tempBuyer, tempItem, amount);
        double discount = tempItem.discount();

        if (discount * amount > 50) {
            throw new TooMuchSaleException("Суммарная скидка на товары не может быть больше 50%!");
        }

        System.out.println(order);
        System.out.println("Скидка в процентах составила: " + discount + ". Цена товара после скидки : " + tempItem.randomDiscount(tempItem.getPrice()));

        return order;
    }
}

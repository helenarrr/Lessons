package InternetShop;

public class Buyer {

    private String name;
    private int cash;

    public Buyer(String name, int cash) {
        this.name = name;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                ", cash=" + cash +
                '}';
    }
}


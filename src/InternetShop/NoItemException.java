package InternetShop;

public class NoItemException extends Exception {

    public NoItemException() {
    }

    public NoItemException(String message) {
        super(message);
    }
}

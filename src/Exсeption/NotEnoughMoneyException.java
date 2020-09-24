package Exсeption;

public class NotEnoughMoneyException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Недостаточно средств");
    }
}

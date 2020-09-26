package Exсeption;

public class UnknownAccountException extends Exception {

    @Override
    public void printStackTrace() {
        System.out.println("Аккаунт не найден. Повторите ввод");
    }
}

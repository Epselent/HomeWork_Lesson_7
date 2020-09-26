import Account.AccountServiceFromDB;
import DataBase.DataBase;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }
}

package Account;

import DataBase.DataBase;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;


public class AccountServiceFromDB implements AccountService {
    private Account account;

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        account = Account.getDataOfString(DataBase.selectAccountFromDB(accountId));
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            DataBase.updateTable(account.getAmount(), accountId);
            System.out.println(account.getHolder() + " c вашего счета cписано - " + amount + " руб.");
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {
        account = Account.getDataOfString(DataBase.selectAccountFromDB(accountId));
        System.out.println(account.getHolder() + " на вашем счете " + account.getAmount() + " руб.");
    }

    @Override
    public void deposit(int accountId, int amount) throws UnknownAccountException {
        account = Account.getDataOfString(DataBase.selectAccountFromDB(accountId));
        account.setAmount(account.getAmount() + amount);
        DataBase.updateTable(account.getAmount(), accountId);
        System.out.println(account.getHolder() + " на ваш счет зачислено - " + amount + " руб.");
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        account = Account.getDataOfString(DataBase.selectAccountFromDB(from));
        Account accountTo = Account.getDataOfString(DataBase.selectAccountFromDB(to));
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            accountTo.setAmount(accountTo.getAmount() + amount);
            DataBase.updateTable(account.getAmount(), from);
            DataBase.updateTable(accountTo.getAmount(), to);
            System.out.println(account.getHolder() + " вы переввели " + amount + " руб. на счет - " + accountTo.getHolder());
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}

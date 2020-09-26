package Account;

import DataBase.DataBase;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;


public class AccountServiceFromDB implements AccountService {
    private Account account;

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        String sql = String.format("SELECT * FROM CLIENT\n" +
                "where id = %s", accountId);
        account = Account.getDataOfString(DataBase.selectAccountFromDB(sql));
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            String sqlUpd = String.format("UPDATE client SET amount = %s WHERE id =%s", account.getAmount(), accountId);
            DataBase.updateTable(sqlUpd);
            System.out.println(account.getHolder() + " c вашего счета cписано - " + amount + " руб.");
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {
        String sql = String.format("SELECT * FROM CLIENT\n" +
                "where id = %s", accountId);
        account = Account.getDataOfString(DataBase.selectAccountFromDB(sql));
        System.out.println(account.getHolder() + " на вашем счете " + account.getAmount() + " руб.");
    }

    @Override
    public void deposit(int accountId, int amount) throws UnknownAccountException {
        String sql = String.format("SELECT * FROM CLIENT\n" +
                "where id = %s", accountId);
        account = Account.getDataOfString(DataBase.selectAccountFromDB(sql));
        account.setAmount(account.getAmount() + amount);
        String sqlUpd = String.format("UPDATE client SET amount = %s WHERE id =%s", account.getAmount(), accountId);
        DataBase.updateTable(sqlUpd);
        System.out.println(account.getHolder() + " на ваш счет зачислено - " + amount + " руб.");
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        String sqlFrom = String.format("SELECT * FROM CLIENT\n" +
                "where id = %s", from);
        String sqlTo = String.format("SELECT * FROM CLIENT\n" +
                "where id = %s", to);
        account = Account.getDataOfString(DataBase.selectAccountFromDB(sqlFrom));
        Account accountTo = Account.getDataOfString(DataBase.selectAccountFromDB(sqlTo));
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            accountTo.setAmount(accountTo.getAmount() + amount);
            String sqlUpdFrom = String.format("UPDATE client SET amount = %s WHERE id =%s", account.getAmount(), from);
            String sqlUpdTo = String.format("UPDATE client SET amount = %s WHERE id =%s", accountTo.getAmount(), to);
            DataBase.updateTable(sqlUpdFrom);
            DataBase.updateTable(sqlUpdTo);
            System.out.println(account.getHolder() + " вы переввели " + amount + " руб. на счет - " + accountTo.getHolder());
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}

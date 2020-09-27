package Account;

import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;

import javax.jws.Oneway;
import java.util.HashMap;

public class AccountServiceFromTxt implements AccountService {
    private AccountList accountList = new AccountList();
    private Account account;

    @Override
    public void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        account = accountList.searchAccount(accountId);
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            accountList.setAccountHashMap(accountId, account);
            System.out.println(account.getHolder() + " c вашего счета cписано - " + amount + " руб.");
        } else {
            throw new NotEnoughMoneyException();
        }

    }

    @Override
    public void balance(int accountId) throws UnknownAccountException {
        account = accountList.searchAccount(accountId);
        System.out.println(account.getHolder() + " на вашем счете " + account.getAmount() + " руб.");
    }

    @Override
    public void deposit(int accountId, int amount) throws UnknownAccountException {
        account = accountList.searchAccount(accountId);
        account.setAmount(account.getAmount() + amount);
        accountList.setAccountHashMap(accountId, account);
        System.out.println(account.getHolder() + " на ваш счет зачислено - " + amount + " руб.");
    }

    @Override
    public void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException {
        account = accountList.searchAccount(from);
        Account accountTo = accountList.searchAccount(to);
        if (account.validAmount(amount)) {
            account.setAmount(account.getAmount() - amount);
            accountTo.setAmount(accountTo.getAmount() + amount);
            accountList.setAccountHashMap(from, account);
            accountList.setAccountHashMap(to, accountTo);
            System.out.println(account.getHolder() + " вы переввели " + amount + " руб. на счет - " + accountTo.getHolder());
        } else {
            throw new NotEnoughMoneyException();
        }

    }

    public void saveInFile() {
        accountList.writeAccountToFile();
    }

    public void begin() {
        accountList.readFileToListAccount();
    }

}

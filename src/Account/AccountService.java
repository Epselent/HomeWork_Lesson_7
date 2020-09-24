package Account;

import Account.Account;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;

import java.util.HashMap;

public interface AccountService {
    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void balance(int accountId) throws UnknownAccountException;

    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException;

    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException;
}

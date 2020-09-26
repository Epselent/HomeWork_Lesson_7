package Client;

import Account.AccountServiceFromDB;
import Account.AccountServiceFromTxt;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientHandlerDB implements ClientHadler {
    private AccountServiceFromDB accountServiceFromDB;

    public ClientHandlerDB() {
        accountServiceFromDB = new AccountServiceFromDB();
    }


    public void withdraw(String[] commandSplit) {
        try {
            accountServiceFromDB.withdraw(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]));

        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void balance(String[] commandSplit) {

        try {
            accountServiceFromDB.balance(Integer.parseInt(commandSplit[1]));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }


    public void deposit(String[] commandSplit) {
        try {
            accountServiceFromDB.deposit(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void transfer(String[] commandSplit) {
        try {
            accountServiceFromDB.transfer(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]), Integer.parseInt(commandSplit[3]));
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void inputClient() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String clientCommand;
        System.out.println("Введите команду");
        boolean exit = true;
        try {
            while (exit) {
                clientCommand = reader.readLine();
                exit = commandHandler(clientCommand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

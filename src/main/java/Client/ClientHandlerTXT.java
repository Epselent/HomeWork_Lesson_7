package Client;

import Account.AccountServiceFromDB;
import Account.AccountServiceFromTxt;
import Exсeption.NotEnoughMoneyException;
import Exсeption.UnknownAccountException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientHandlerTXT implements ClientHadler {
    private AccountServiceFromTxt accountServiceFromTxt;

    public ClientHandlerTXT() {
        accountServiceFromTxt = new AccountServiceFromTxt();
    }

//    public boolean commandHandler(String command) {
//        String[] commandSplit = command.split("\\s*(\\s)");
//        boolean exit = true;
//        switch (commandSplit[0]) {
//            case "balance":
//                balance(commandSplit);
//                break;
//            case "withdraw":
//                withdraw(commandSplit);
//                break;
//            case "deposit":
//                deposit(commandSplit);
//                break;
//            case "transfer":
//                transfer(commandSplit);
//                break;
//            case "exit":
//                exit = false;
//                break;
//            default:
//                System.out.println("Комманда не правильно введена");
//        }
//        return exit;
//    }

    public void withdraw(String[] commandSplit) {
        try {
            accountServiceFromTxt.withdraw(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]));
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void balance(String[] commandSplit) {

        try {
            accountServiceFromTxt.balance(Integer.parseInt(commandSplit[1]));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }


    public void deposit(String[] commandSplit) {
        try {
            accountServiceFromTxt.deposit(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void transfer(String[] commandSplit) {
        try {
            accountServiceFromTxt.transfer(Integer.parseInt(commandSplit[1]), Integer.parseInt(commandSplit[2]), Integer.parseInt(commandSplit[3]));
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }

    public void inputClient() {
        accountServiceFromTxt.begin();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String clientCommand;
        System.out.println("Введите команду");
        boolean exit = true;
        try {
            while (exit) {
                clientCommand = reader.readLine();
                exit = commandHandler(clientCommand);
            }
            accountServiceFromTxt.saveInFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

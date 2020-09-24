package Account;

import FileStorage.FileController;
import Exсeption.UnknownAccountException;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountList {
    private HashMap<Integer, Account> accountHashMap;
    private ArrayList<String> listAccount;

    public AccountList() {
        this.accountHashMap = new HashMap<Integer, Account>();
        this.listAccount = new ArrayList<>();
    }

    public static void startFilling() {
        String holder;
        int amount;
        ArrayList<String> dataList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            holder = "Client" + i;
            amount = (int) (Math.random() * 1000);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i + "$");
            stringBuilder.append(holder + "$");
            stringBuilder.append(amount);
            dataList.add(stringBuilder.toString());
        }
        FileController.writeFile(dataList);
    }

    public Account searchAccount(int id) throws UnknownAccountException {
        if (this.accountHashMap.containsKey(id)) {
            Account account;
            return account = this.accountHashMap.get(id);
        } else {
            throw new UnknownAccountException();
        }
    }

    public void writeAccountToFile() {
        listAccount.clear();
        for (int k : this.accountHashMap.keySet()) {
            listAccount.add(this.accountHashMap.get(k).makeDataInOnLine());
        }
        FileController.writeFile(listAccount);
    }

    public void readFileToListAccount() {
        this.listAccount = new ArrayList<>(FileController.readFile());
        for (String s : listAccount) {
            Account account = Account.getDataOfString(s);
            this.accountHashMap.put(account.getId(), account);
        }
    }

    public void setAccountHashMap(Integer key, Account account) {
        this.accountHashMap.put(key, account);
    }
}

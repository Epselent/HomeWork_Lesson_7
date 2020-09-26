package Account;

public class Account {
    private int id;
    private String holder;
    private int amount;
    private String idHolderAmount;

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    protected int getId() {
        return id;
    }

    protected Account setId(int id) {
        this.id = id;
        return this;
    }

    protected String getHolder() {
        return holder;
    }

    protected Account setHolder(String holder) {
        this.holder = holder;
        return this;
    }

    protected int getAmount() {
        return amount;
    }

    protected Account setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    protected String makeDataInOnLine() {
        StringBuilder str = new StringBuilder();
        str.append(this.id + "$" + this.holder + "$" + this.amount);
        idHolderAmount = str.toString();
        return idHolderAmount;
    }

    protected static Account getDataOfString(String str) {
        String[] arr = str.split("\\$");
        return new Account(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
    }

    protected boolean validAmount(int amount) {
        return this.amount >= amount;
    }
}

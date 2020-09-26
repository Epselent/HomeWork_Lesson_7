package Client;

public interface ClientHadler {
    default boolean commandHandler(String command) {
        String[] commandSplit = command.split("\\s*(\\s)");
        boolean exit = true;
        switch (commandSplit[0]) {
            case "balance":
                balance(commandSplit);
                break;
            case "withdraw":
                withdraw(commandSplit);
                break;
            case "deposit":
                deposit(commandSplit);
                break;
            case "transfer":
                transfer(commandSplit);
                break;
            case "exit":
                exit = false;
                break;
            default:
                System.out.println("Комманда не правильно введена");
        }
        return exit;
    }
    void withdraw(String[] commandSplit);
    void balance(String[] commandSplit);
    void deposit(String[] commandSplit);
    void transfer(String[] commandSplit);
    void inputClient();

}

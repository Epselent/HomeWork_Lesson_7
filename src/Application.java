import Client.ClientHandler;
import DataBase.FileController;

public class Application {
    private ClientHandler clientHandler;

    public Application() {
        clientHandler = new ClientHandler();

    }

    public void start() {
        FileController.createFile();
        clientHandler.inputClient();

    }
}

import Client.ClientHandlerDB;
import Client.ClientHandlerTXT;
import DataBase.DataBase;
import FileStorage.FileController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private ClientHandlerTXT clientHandlerTXT;
    private ClientHandlerDB clientHandlerDB;

    public Application() {
        clientHandlerTXT = new ClientHandlerTXT();
        clientHandlerDB = new ClientHandlerDB();
    }

    public void start() {
        System.out.println("Выберите способ хранения данных. Для хранения в  TXT введите FILE, для Базы данных введите DATABASE");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String clientCommand = reader.readLine();
            switch (clientCommand) {
                case "FILE":
                    FileController.createFile();
                    clientHandlerTXT.inputClient();
                    break;
                case "DATABASE":
                    DataBase.createDB();
                    clientHandlerDB.inputClient();
                    DataBase.disconnectDB();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

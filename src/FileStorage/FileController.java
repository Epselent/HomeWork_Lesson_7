package FileStorage;

import Account.AccountList;

import java.io.*;
import java.util.ArrayList;

public class FileController {
    private static File file = new File("Archive_account.txt");

    public static void createFile() {
        if (!file.exists()) {
            AccountList.startFilling();
        }
    }

    public static void writeFile(ArrayList<String> dataList) {
        try (FileWriter writer = new FileWriter(file)) {
            for (String str : dataList) {
                if (str != null) {
                    writer.write(str + "\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();
        String str;
        try (BufferedReader readerFile = new BufferedReader(new FileReader(file))) {
            while ((str = readerFile.readLine()) != null) {
                data.add(str);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

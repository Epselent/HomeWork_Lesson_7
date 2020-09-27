package DataBase;

import Exсeption.UnknownAccountException;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DataBase {
    public static Connection connection;
    public static Statement stmt;
    private static File fileDB = new File("data.db");
    private static PreparedStatement pstmt;


    public static void createDB() {
        if (!fileDB.exists()) {
            try {
                fileDB.createNewFile();
                connectDB();
                createTableAndFilling();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            connectDB();
        }
    }

    public static void connectDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void disconnectDB() {
        try {
            connection.close();
            stmt.close();
        } catch (SQLException throwables) {
            System.out.println("Подключение к БД закрыто");
            ;
        }

    }

    public static void createTableAndFilling() {
        String sqlCreate = "CREATE TABLE client (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                   UNIQUE\n" +
                "                   NOT NULL,\n" +
                "    holder STRING  NOT NULL,\n" +
                "    amount INT\n" +
                ")";
        String holder = new String();
        int amount = 0;
        try {
            stmt.executeUpdate(sqlCreate);
            pstmt = connection.prepareStatement("INSERT INTO client (holder,amount)\n" +
                    "VALUES (?,?)");
            for (int i = 1; i <= 10; i++) {
                holder = "Client" + i;
                amount = (int) (Math.random() * 1000);
                pstmt.setString(1, holder);
                pstmt.setInt(2, amount);
                pstmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String selectAccountFromDB(int id) throws UnknownAccountException {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            pstmt = connection.prepareStatement("SELECT * FROM CLIENT\n" +
                    "where id = ?");
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                stringBuilder.append(resultSet.getInt("id"));
                stringBuilder.append("$");
                stringBuilder.append(resultSet.getString("holder"));
                stringBuilder.append("$");
                stringBuilder.append(resultSet.getInt("amount"));
                return stringBuilder.toString();
            } else {
                throw new UnknownAccountException();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public static void updateTable(int amount, int id) {
        try {
            pstmt = connection.prepareStatement("UPDATE client SET amount = ? WHERE id =?");
            pstmt.setInt(1, amount);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

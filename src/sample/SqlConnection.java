package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.JDBC;

import javax.swing.*;
import java.sql.*;

public class SqlConnection {
    Connection connection = null;

    public static Connection ConnectDB() {
        try {
            //Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new JDBC());
            Connection connection = (Connection) DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\SaleApp\\myfin.db");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    private static SqlConnection instance = null;
    public static synchronized SqlConnection getInstance() throws SQLException {
        if (instance == null)
            instance = new SqlConnection();
        return instance;
    }

    public static ObservableList<Category> getDataCategory(){
        Connection connection = ConnectDB();
        ObservableList<Category> list = FXCollections.observableArrayList();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name_category FROM category")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Category(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name_category")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Product> getDataProduct(){
        Connection connection = ConnectDB();
        ObservableList<Product> list = FXCollections.observableArrayList();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, id_category, name, price, producing_country FROM products")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Product(
                        Integer.parseInt(resultSet.getString("id")),
                        Integer.parseInt(resultSet.getString("id_category")),
                        resultSet.getString("name"),
                        Double.parseDouble(resultSet.getString("price")),
                        resultSet.getString("producing_country")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<GoodsInOrder> getDataGoodsInOrder(){
        Connection connection = ConnectDB();
        ObservableList<GoodsInOrder> list = FXCollections.observableArrayList();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, id_products, quantity, cost, cashier FROM goods_in_order")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new GoodsInOrder(
                        Integer.parseInt(resultSet.getString("id")),
                        Integer.parseInt(resultSet.getString("id_products")),
                        Integer.parseInt(resultSet.getString("quantity")),
                        Double.parseDouble(resultSet.getString("cost")),
                        resultSet.getString("cashier")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

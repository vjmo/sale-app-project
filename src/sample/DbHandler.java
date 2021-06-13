package sample;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHandler {

   /* String sqlTableCategory = "CREATE TABLE "category" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name_category"	INTEGER NOT NULL,
	PRIMARY KEY("id")
);";
String sqlTableProduct = "CREATE TABLE "products" (
	"id"	INTEGER NOT NULL,
	"id_category"	INTEGER,
	"name"	TEXT NOT NULL,
	"price"	REAL NOT NULL,
	"producing_country"	TEXT NOT NULL,
	FOREIGN KEY("id_category") REFERENCES "category"("id"),
	PRIMARY KEY("id")
);";
String sqlTableGoods ="CREATE TABLE "goods_in_order" (
	"id"	INTEGER NOT NULL UNIQUE,
	"id_products"	INTEGER NOT NULL UNIQUE,
	"quantity"	INTEGER NOT NULL,
	"cost"	REAL,
	"cashier"	TEXT NOT NULL,
	FOREIGN KEY("id_products") REFERENCES "products"("id"),
	PRIMARY KEY("id")
);";
*/

    Connection connection = null;
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\SaleApp\\myfin.db";

    // Используем щаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    //private Connection connection;

    private DbHandler() throws SQLException {
        // Регистриуем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<Product> getAllProducts() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Product> products = new ArrayList<Product>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, id_category, name, price, producing_country FROM products");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getInt("id_category"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("producing_country")));
            }
            // Возращаем наш список
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление продукта в БД
    public void addProduct(Product product) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        /* try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products(`id_category`,`name`, `price`, `producing_country`) " +
                        "VALUES(?, ?, ?, ?)")) {
           statement.setObject(1, product.id_category);
            statement.setObject(2, product.name);
            statement.setObject(3, product.price);
            statement.setObject(4, product.producing_country);
            // Выполняем запрос
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    // Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // category
    public List<Category> getAllCategory() {
        try (Statement statement = this.connection.createStatement()) {
            List<Category> categories = new ArrayList<Category>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name_category FROM category");
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"),
                        resultSet.getString("name_category")));
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addCategory(Category category) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Category(`name_category`) " +
                        "VALUES(?)")) {
           // statement.setObject(1, category.nameCategory);
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Category WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //goods_in_order
    public List<GoodsInOrder> getAllGoodsInOrder() {
        try (Statement statement = this.connection.createStatement()) {
            List<GoodsInOrder> goodsInOrders = new ArrayList<GoodsInOrder>();
            ResultSet resultSet = statement.executeQuery("SELECT id, id_products, quantity, cost, cashier FROM goods_in_order");
            while (resultSet.next()) {
                goodsInOrders.add(new GoodsInOrder(resultSet.getInt("id"),
                        resultSet.getInt("id_products"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("cost"),
                        resultSet.getString("cashier")));
            }
            return goodsInOrders;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

   /* public void addGoodsInOrder(GoodsInOrder goodsInOrder) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO goods_in_order(`id_products`, `quantity`, `cost`, `cashier`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, goodsInOrder.id_products);
            statement.setObject(2, goodsInOrder.quantity);
            statement.setObject(3, goodsInOrder.cost);
            statement.setObject(4, goodsInOrder.cashier);
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void deleteGoodsInOrder(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM goods_in_order WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

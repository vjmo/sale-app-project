package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SampleController {
    @FXML
    private TextArea t_area;
    @FXML
    private Button id_btn_cat;
    @FXML
    private TextField id_tf_category;
    @FXML
    private TextField name_tf_category;
    @FXML
    private TableView<Category> table_category;

    @FXML
    private TableColumn<Category, Integer> col_id_category;

    @FXML
    private TableColumn<Category, String> col_categ;

    ObservableList<Category> listCategory;

    int index = -1;

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @FXML
    public void addCategory(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();
        String sql = "insert into category (name_category) values (?)";
        try{
            id_btn_cat = (Button) actionEvent.getSource();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name_tf_category.getText());
            preparedStatement.execute();
            updateCategory();
            JOptionPane.showMessageDialog(null,"Category Add Succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void updateCategory(){
        connection = SqlConnection.ConnectDB();
        try{
            //Button button = (Button) actionEvent.getSource();
            table_category.refresh();
            col_id_category.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
            col_categ.setCellValueFactory(new PropertyValueFactory<Category, String>("nameCategory"));

            listCategory = SqlConnection.getDataCategory();
            table_category.setItems(listCategory);
            JOptionPane.showMessageDialog(null,"obnova");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void deleteCategory(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();

        try{
            Button button = (Button) actionEvent.getSource();
            Category selectedItem = table_category.getSelectionModel().getSelectedItem();
            table_category.getItems().remove(selectedItem);
            String sql = "DELETE FROM category WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_tf_category.getText());
            table_category.getItems().remove(preparedStatement);
           // preparedStatement.setString(2, String.valueOf(table_category.getItems().remove(selectedItem)));
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null,"delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    private TextField id_tf_id_category;

    @FXML
    private TextField name_product_tf;

    @FXML
    private TextField price_product_tf;

    @FXML
    private TextField country_product_tf;

    @FXML
    private TextField id_delete_tf_prodct;

    @FXML
    private Button id_bnt_product;

    @FXML
    private Button id_delete_bnt_prod;
    @FXML
    private TableColumn<Product, Integer> id_clmn_pr;

    @FXML
    private TableColumn<Product, Integer> id_clmn_categ_pr;

    @FXML
    private TableColumn<Product, String> clmn_name_pr;

    @FXML
    private TableColumn<Product, Double> clmn_price_pr;

    @FXML
    private TableColumn<Product, String> clmn_prodcountry_pr;

    @FXML
    private TableView<Product> table_product;

    ObservableList<Product> listProduct;
    @FXML
    public void addProduct(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();
        String sql = "insert into products (id_category, name, price, producing_country) values (?, ?, ?, ?)";
        try{
            id_bnt_product = (Button) actionEvent.getSource();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_tf_id_category.getText());
            preparedStatement.setString(2, name_product_tf.getText());
            preparedStatement.setString(3, price_product_tf.getText());
            preparedStatement.setString(4, country_product_tf.getText());
            preparedStatement.execute();
            updateProduct();
            JOptionPane.showMessageDialog(null,"prod Add Succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void updateProduct(){
        connection = SqlConnection.ConnectDB();
        try{
            //Button button = (Button) actionEvent.getSource();
            table_product.refresh();
            id_clmn_pr.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            id_clmn_categ_pr.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id_category"));
            clmn_name_pr.setCellValueFactory(new PropertyValueFactory<Product, String>("nameProduct"));
            clmn_price_pr.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            clmn_prodcountry_pr.setCellValueFactory(new PropertyValueFactory<Product, String>("producingCountry"));

            listProduct = SqlConnection.getDataProduct();
            table_product.setItems(listProduct);
            JOptionPane.showMessageDialog(null,"obnova  prouct");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void deleteProduct(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();

        try{
            Button button = (Button) actionEvent.getSource();
            Product selectedItem = table_product.getSelectionModel().getSelectedItem();
            table_product.getItems().remove(selectedItem);
            String sql = "DELETE FROM products WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_delete_tf_prodct.getText());
            table_category.getItems().remove(preparedStatement);
            // preparedStatement.setString(2, String.valueOf(table_category.getItems().remove(selectedItem)));
            preparedStatement.execute();
            updateProduct();
            JOptionPane.showMessageDialog(null,"delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }



    @FXML
    private TextField id_pr_tf_goods;

    @FXML
    private TextField quantity_tf_goods;

    @FXML
    private TextField cost_tf_goods;

    @FXML
    private TextField cashier_tf_goods;

    @FXML
    private Button id_bnt_goods;

    @FXML
    private Button id_delete_bnt_prod1;

    @FXML
    private TextField id_delete_tf_goods;

    @FXML
    private TableView<GoodsInOrder> table_goods;

    @FXML
    private TableColumn<GoodsInOrder, Integer> id_clm_goods;

    @FXML
    private TableColumn<GoodsInOrder, Integer> id_prod_clm_goods;

    @FXML
    private TableColumn<GoodsInOrder, Integer> quantuty_clm_goods;

    @FXML
    private TableColumn<GoodsInOrder, Double> cost_clmn_goods;

    @FXML
    private TableColumn<GoodsInOrder, String> cashier_clmn_goods;
    @FXML
    private Button id_delete_bnt_goods;

    ObservableList<GoodsInOrder> listGoodsInOrder;
    @FXML
    public void addGoodsInOrder(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();
        String sql = "insert into goods_in_order (id_products, quantity, cashier) values (?, ?, ?)";
        //String sqlSum = "select goods_in_order.quantity*products.price AS summa FROM goods_in_order, products WHERE goods_in_order.id_products = products.id";
        try{
            id_bnt_goods = (Button) actionEvent.getSource();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_pr_tf_goods.getText());
            preparedStatement.setString(2, quantity_tf_goods.getText());
            preparedStatement.setString(3, cashier_tf_goods.getText());
            preparedStatement.execute();
            String sqlSum = "select goods_in_order.quantity*products.price AS summa FROM goods_in_order, " +
                    "products WHERE goods_in_order.id_products = products.id";
            preparedStatement= connection.prepareStatement(sqlSum);
            preparedStatement.setString(1, t_area.getText());
            preparedStatement.execute();

            updateGoodsInOrder();
            JOptionPane.showMessageDialog(null,"prod Add Succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void updateGoodsInOrder(){
        connection = SqlConnection.ConnectDB();
        try{
            //Button button = (Button) actionEvent.getSource();
            table_goods.refresh();
            id_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("id"));
            id_prod_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("id_products"));
            quantuty_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("quantity"));
            cost_clmn_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Double>("cost"));
            cashier_clmn_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, String>("cashier"));

            listGoodsInOrder = SqlConnection.getDataGoodsInOrder();
            table_goods.setItems(listGoodsInOrder);
            JOptionPane.showMessageDialog(null,"obnova goods");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void deleteGoodsInOrder(ActionEvent actionEvent){
        connection = SqlConnection.ConnectDB();

        try{
            id_delete_bnt_goods = (Button) actionEvent.getSource();
            Product selectedItem = table_product.getSelectionModel().getSelectedItem();
            table_product.getItems().remove(selectedItem);
            String sql = "DELETE FROM goods_in_order WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id_delete_tf_goods.getText());
            table_goods.getItems().remove(preparedStatement);
            // preparedStatement.setString(2, String.valueOf(table_category.getItems().remove(selectedItem)));
            preparedStatement.execute();
            updateGoodsInOrder();
            JOptionPane.showMessageDialog(null,"delete");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);

        }
    }

    @FXML
    public void initialize() {
        col_id_category.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        col_categ.setCellValueFactory(new PropertyValueFactory<Category, String >("nameCategory"));

        listCategory = SqlConnection.getDataCategory();
        table_category.setItems(listCategory);
        updateCategory();

        id_clmn_pr.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        id_clmn_categ_pr.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id_category"));
        clmn_name_pr.setCellValueFactory(new PropertyValueFactory<Product, String>("nameProduct"));
        clmn_price_pr.setCellValueFactory(new PropertyValueFactory<Product, Double >("price"));
        clmn_prodcountry_pr.setCellValueFactory(new PropertyValueFactory<Product, String >("producingCountry"));

        listProduct = SqlConnection.getDataProduct();
        table_product.setItems(listProduct);
        updateProduct();

        id_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("id"));
        id_prod_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("id_products"));
        quantuty_clm_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Integer>("quantity"));
        cost_clmn_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, Double >("cost"));
        cashier_clmn_goods.setCellValueFactory(new PropertyValueFactory<GoodsInOrder, String >("cashier"));

        listGoodsInOrder = SqlConnection.getDataGoodsInOrder();
        table_goods.setItems(listGoodsInOrder);
        updateGoodsInOrder();
    }
}

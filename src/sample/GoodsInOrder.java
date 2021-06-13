package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class GoodsInOrder {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty id_products;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty cost;
    private SimpleStringProperty cashier;


    public GoodsInOrder(int id, int id_products, int quantity, double cost, String cashier) {
        this.id = new SimpleIntegerProperty(id);
        this.id_products = new SimpleIntegerProperty(id_products);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.cost = new SimpleDoubleProperty(cost);
        this.cashier = new SimpleStringProperty(cashier);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public void setId_products(int id_products) {
        this.id_products.set(id_products);
    }

    public int getId_products() {
        return id_products.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public double getCost() {
        return cost.get();
    }

    public void setCashier(String cashier) {
        this.cashier.set(cashier);
    }

    public String getCashier() {
        return cashier.get();
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Товар: %s | Количество: %s | Сумма: %s | Кассир: %s",
                this.id, this.id_products, this.quantity, this.cost, this.cashier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsInOrder goodsInOrder = (GoodsInOrder) o;
        return goodsInOrder.id == this.id &&
                goodsInOrder.id_products == this.id_products &&
                goodsInOrder.quantity == this.quantity &&
                goodsInOrder.cashier == this.cashier &&
                goodsInOrder.cost == this.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_products, quantity, cost, cashier);
    }
}

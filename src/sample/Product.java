package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Product {
    // Поля класса
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty id_category;
    private SimpleStringProperty nameProduct;
    private SimpleDoubleProperty price;
    private SimpleStringProperty producingCountry;


    // Конструктор
    public Product(int id, int id_category, String nameProduct, double price, String producingCountry) {
        this.id = new SimpleIntegerProperty(id);
        this.id_category = new SimpleIntegerProperty(id_category);
        this.nameProduct = new SimpleStringProperty(nameProduct);
        this.price = new SimpleDoubleProperty(price);
        this.producingCountry = new SimpleStringProperty(producingCountry);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public void setId_category(int id_category) {
        this.id_category.set(id_category);
    }

    public int getId_category() {
        return id_category.get();
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct.set(nameProduct);
    }

    public String getNameProduct() {
        return nameProduct.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getPrice() {
        return price.get();
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry.set(producingCountry);
    }

    public String getProducingCountry() {
        return producingCountry.get();
    }

    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format("ID: %s | Категория: %s | Товар: %s | Цена: %s | Страна производства: %s",
                this.id, this.id_category, this.nameProduct, this.price, this.producingCountry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product.nameProduct == this.nameProduct &&
                product.producingCountry == this.producingCountry &&
                product.id == this.id &&
                product.id_category == this.id_category &&
                product.price == this.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_category, nameProduct, price, producingCountry);
    }
}
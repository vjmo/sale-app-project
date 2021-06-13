package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Category {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nameCategory;
   // public int id;

  //  public String nameCategory;

    public Category(int id, String nameCategory) {
        this.id = new SimpleIntegerProperty(id);
        this.nameCategory = new SimpleStringProperty(nameCategory);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory.set(nameCategory);
    }

    public String getNameCategory() {
        return nameCategory.get();
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Категория: %s ",
                this.id, this.nameCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return category.id == this.id &&
                category.nameCategory == this.nameCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCategory);
    }
}

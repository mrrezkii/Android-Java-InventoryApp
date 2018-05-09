package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

import java.io.Serializable;

public class CategoriesModel implements Serializable {
    String idcategory, category;

    public CategoriesModel() {
        this.idcategory = idcategory;
        this.category = category;
    }

    public CategoriesModel(String idCategory, String category) {
        this.idcategory = idcategory;
        this.category = category;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

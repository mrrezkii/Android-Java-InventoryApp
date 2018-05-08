package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

import java.io.Serializable;

public class CategoryTypesModel implements Serializable {
    int idtypes;
    String idcategori, types;

    public CategoryTypesModel(int idtypes, String idcategori, String types) {
        this.idtypes = idtypes;
        this.idcategori = idcategori;
        this.types = types;
    }

    public int getIdtypes() {
        return idtypes;
    }

    public void setIdtypes(int idtypes) {
        this.idtypes = idtypes;
    }

    public String getIdcategori() {
        return idcategori;
    }

    public void setIdcategori(String idcategori) {
        this.idcategori = idcategori;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}

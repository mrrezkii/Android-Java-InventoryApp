package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

import java.io.Serializable;

public class UserModel implements Serializable {

    int id;
    String username, password, nama, branch;

    public UserModel(int id, String username, String password, String nama, String branch) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

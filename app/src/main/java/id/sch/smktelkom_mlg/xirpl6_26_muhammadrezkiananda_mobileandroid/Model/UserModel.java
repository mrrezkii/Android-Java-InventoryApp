package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

public class UserModel {

    int id;
    String name, username, password, branch;

    public UserModel(int id, String name, String username, String password, String branch) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBranch() {
        return branch;
    }
}

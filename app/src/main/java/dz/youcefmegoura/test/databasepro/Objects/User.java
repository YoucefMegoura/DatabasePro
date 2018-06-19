package dz.youcefmegoura.test.databasepro.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Youcef Mégoura on 18/06/2018.
 */

public class User {
    private int id;
    private String pseudo;
    private String password;
    private int is_connected;



    public User(int id, String pseudo, String password, int is_connected) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.is_connected = is_connected;
    }

    public User(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_connected() {
        return is_connected;
    }

    public void setIs_connected(int is_connected) {
        this.is_connected = is_connected;
    }

    public static User getUserFromJson(String json){
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public static ArrayList<User> getListOfUserFromJson(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>(){}.getType();
        ArrayList<User> users = gson.fromJson(json, type);
        return users;
    }
}
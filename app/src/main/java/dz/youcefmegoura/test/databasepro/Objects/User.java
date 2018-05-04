package dz.youcefmegoura.test.databasepro.Objects;

/**
 * Created by Megoura Youcef and Mekka Moussaoui on 04/05/2018.
 */

public class User {
    String username;
    int age;
    int score;

    public User() {
    }

    public User(String username, int age, int score) {
        username = username;
        age = age;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

package dz.youcefmegoura.test.databasepro.Objects;

import java.util.ArrayList;

/**
 * Created by Youcef Mégoura on 06/06/2018.
 */

public class Langue {
    private int id_langue;
    private String nom_langue;
    private String url_image_langue;
    private String db_name;
    public ArrayList <Categorie> categorie;
    public int image_langue;

    public int getImage_langue() {
        return image_langue;
    }

    public void setImage_langue(int image_langue) {
        this.image_langue = image_langue;
    }

    public ArrayList<Categorie> getItems() {
        return categorie;
    }

    public void setItems(ArrayList<Categorie> categorie) {
        this.categorie = categorie;
    }

    public Langue() {
    }

    public int getId_langue() {
        return id_langue;
    }

    public void setId_langue(int id_langue) {
        this.id_langue = id_langue;
    }

    public String getNom_langue() {
        return nom_langue;
    }

    public void setNom_langue(String nom_langue) {
        this.nom_langue = nom_langue;
    }

    public String getUrl_image_langue() {
        return url_image_langue;
    }

    public void setUrl_image_langue(String url_image_langue) {
        this.url_image_langue = url_image_langue;
    }




    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public Langue(int id_langue, String nom_langue, String url_image_langue, String db_name) {

        this.id_langue = id_langue;
        this.nom_langue = nom_langue;
        this.url_image_langue = url_image_langue;
        this.db_name = db_name;
    }
}

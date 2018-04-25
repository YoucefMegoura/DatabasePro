package dz.youcefmegoura.test.databasepro.Objects;

/**
 * Created by Youcef Mégoura on 23/04/2018.
 */

public class Image {
    //Attributs
    private int id_image;
    private String nom_image;
    private int score_image;
    private String url_image;

    //Constructeur
    public Image(int id_image, String nom_image, int score_image, String url_image) {
        this.id_image = id_image;
        this.nom_image = nom_image;
        this.score_image = score_image;
        this.url_image = url_image;
    }

    //Guetters & Setters
    public int getId_image() {
        return id_image;
    }
    public void setId_image(int id_image) {
        this.id_image = id_image;
    }
    public String getNom_image() {
        return nom_image;
    }
    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
    public int getScore_image() {
        return score_image;
    }
    public void setScore_image(int score_image) {
        this.score_image = score_image;
    }
    public String getUrl_image() {
        return url_image;
    }
    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

}
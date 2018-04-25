package dz.youcefmegoura.test.databasepro.Objects;

/**
 * Created by Youcef Mégoura on 23/04/2018.
 */

public class Categorie{
    private int id_categorie;
    private String nom_categorie;
    private int score_categorie;

    public Categorie(int id_categorie, String nom_categorie, int score_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.score_categorie = score_categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public int getScore_categorie() {
        return score_categorie;
    }

    public void setScore_categorie(int score_categorie) {
        this.score_categorie = score_categorie;
    }
}

package dz.youcefmegoura.test.databasepro.Objects;


/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class Niveau {
    //Attributs
    private int id_niveau;
    private String nom_niveau;
    private int score_niveau;

    //Constructeur
    public Niveau(int id_nieau, String nom_niveau, int score_niveau) {
        this.id_niveau = id_nieau;
        this.nom_niveau = nom_niveau;
        this.score_niveau = score_niveau;
    }

    //Guetters & Setters
    public int getId_niveau() {
        return id_niveau;
    }
    public void setId_niveau(int id_nieau) {
        this.id_niveau = id_nieau;
    }
    public String getNom_niveau() {
        return nom_niveau;
    }
    public void setNom_niveau(String nom_niveau) {
        this.nom_niveau = nom_niveau;
    }
    public int getScore_niveau() {
        return score_niveau;
    }
    public void setScore_niveau(int score_niveau) {
        this.score_niveau = score_niveau;
    }
}

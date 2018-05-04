package dz.youcefmegoura.test.databasepro.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Youcef Mégoura and Mekka Moussaoui on 04/05/2018.
 */

public class PopulateDatabase {


    public static void populate_categorie(SQLiteDatabase db){
        db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (1, 'physique', 0); ");
        db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (2, 'science', 0); ");
        db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (3, 'informatique', 0)" );
    }

    public static void populate_niveau(SQLiteDatabase db){
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (1, 'niv 1 ', 0, 1); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (2, 'niv 2', 0, 1); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (3, 'niv 3', 0, 1); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (4, 'niv 1', 0, 2); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (5, 'niv 2', 0, 2); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (6, 'niv 3', 0, 2); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (7, 'niv 1', 0, 3); ");
        db.execSQL( "INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (8, 'niv 2', 0, 3); ");
        db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (9, 'niv 3', 0, 3);");

    }

    public static void populate_image(SQLiteDatabase db){
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (1, 'avocado', 0, 'avocado', 1, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (2, 'bee', 0, 'bee', 1, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (3, 'mushroom', 0, 'mushroom', 1, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (4, 'flower', 0, 'flower', 2, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (5, 'peas', 0, 'peas', 2, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (6, 'beetle', 0, 'beetle', 2, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (7, 'island', 0, 'island', 3, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (8, 'pineapple', 0, 'pineapple', 3, 1); ");
        db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (9, 'moon', 0, 'moon', 3, 1); ");

    }



}

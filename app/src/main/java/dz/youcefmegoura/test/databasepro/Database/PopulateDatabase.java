package dz.youcefmegoura.test.databasepro.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Youcef Mégoura and Mekka Moussaoui on 04/05/2018.
 */

public class PopulateDatabase {


    public static void populate_categorie(SQLiteDatabase db, String DB_NAME){
        switch (DB_NAME){
            case "english.db":
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (1, 'physique', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (2, 'science', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (3, 'informatique', 0)" );
                break;

            case "french.db":
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (1, 'de', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (2, 'fr', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (3, 'fr', 0)" );
                break;


            case "german.db":
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (1, 'grde', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (2, 'grfr', 0); ");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (3, 'grfr', 0)" );
                break;
        }

    }

    public static void populate_niveau(SQLiteDatabase db, String DB_NAME){
        switch (DB_NAME) {
            case "english.db":
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (1, 'niv 1 ', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (2, 'niv 2', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (3, 'niv 3', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (4, 'niv 1', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (5, 'niv 2', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (6, 'niv 3', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (7, 'niv 1', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (8, 'niv 2', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (9, 'niv 3', 0, 3);");
                break;

            case "french.db":
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (1, 'frniv 1 ', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (2, 'fr 2', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (3, 'fr 3', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (4, 'fr 1', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (5, 'fr 2', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (6, 'fr 3', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (7, 'niv 1', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (8, 'niv 2', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (9, 'niv 3', 0, 3);");
                break;

            case "german.db":
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (1, 'frniv 1 ', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (2, 'fr 2', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (3, 'fr 3', 0, 1); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (4, 'fr 1', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (5, 'fr 2', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (6, 'fr 3', 0, 2); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (7, 'niv 1', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (8, 'niv 2', 0, 3); ");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (9, 'niv 3', 0, 3);");
                break;


        }

    }

    public static void populate_image(SQLiteDatabase db, String DB_NAME) {
        switch (DB_NAME) {
            case "english.db":
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (1, 'avocado', 0, 'avocado', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (2, 'bee', 0, 'bee', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (3, 'mushroom', 0, 'mushroom', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (4, 'flower', 0, 'flower', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (5, 'peas', 0, 'peas', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (6, 'beetle', 0, 'beetle', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (7, 'island', 0, 'island', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (8, 'pineapple', 0, 'pineapple', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (9, 'moon', 0, 'moon', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (10, 'whale', 0, 'whale', 1, 1); ");
                break;

            case "french.db":
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (1, 'avocat', 0, 'avocado', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (2, 'abeille', 0, 'bee', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (3, 'champignon', 0, 'mushroom', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (4, 'fleur', 0, 'flower', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (5, 'pois', 0, 'peas', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (6, 'scarabée', 0, 'beetle', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (7, 'île', 0, 'island', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (8, 'ananas', 0, 'pineapple', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (9, 'lune', 0, 'moon', 3, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (10, 'baleine', 0, 'whale', 1, 1); ");
                break;

            case "german.db":
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (1, 'Rechtsanwalt', 0, 'avocado', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (2, 'Biene', 0, 'bee', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (3, 'Pilze', 0, 'mushroom', 1, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (4, 'Blume', 0, 'flower', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (5, 'Erbsen', 0, 'peas', 2, 1); ");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (7, 'Insel', 0, 'island', 3, 1); ");
                break;

        }

    }

}

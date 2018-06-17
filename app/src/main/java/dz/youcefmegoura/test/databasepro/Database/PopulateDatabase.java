package dz.youcefmegoura.test.databasepro.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Youcef Mégoura and Mekka Moussaoui on 04/05/2018.
 */

public class PopulateDatabase {


    public static void populate_categorie(SQLiteDatabase db, String DB_NAME){
        switch (DB_NAME){
            case "english.db":
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (1, 'Animaux', 15);");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (2, 'Gastronomie', 0);");
                db.execSQL("INSERT INTO categorie (id_categorie, nom_categorie, score_categorie) VALUES (3, 'informatique', 0);");
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
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (1, 'niv 1 ', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (2, 'niv 2', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (3, 'niv 3', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (4, 'niv 4', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (5, 'niv 5', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (6, 'niv 6', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (7, 'niv 7', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (8, 'niv 8', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (9, 'niv 9', 0, 1);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (10, 'niv 1 ', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (11, 'niv 2', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (12, 'niv 3', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (13, 'niv 4', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (14, 'niv 5', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (15, 'niv 6', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (16, 'niv 7', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (17, 'niv 8', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (18, 'niv 9', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (19, 'niv 10', 0, 2);");
                db.execSQL("INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, id_categorie) VALUES (20, 'niv 11', 0, 2);");
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
                //Categorie 1
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (1, 'ant', 0, 'ant', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (2, 'bear', 0, 'bear', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (3, 'bee', 0, 'bee', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (4, 'cat', 0, 'cat', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (5, 'cow', 0, 'cow', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (6, 'dog', 0, 'dog', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (7, 'fly', 0, 'fly', 1, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (8, 'bug', 0, 'bug', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (9, 'bull', 0, 'bull', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (10, 'crab', 0, 'crab', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (11, 'fish', 0, 'fish', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (12, 'frog', 0, 'frog', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (13, 'lion', 0, 'lion', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (14, 'mite', 0, 'mite', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (15, 'wolf', 0, 'wolf', 2, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (16, 'bird', 0, 'bird', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (17, 'corgi', 0, 'corgi', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (18, 'deer', 0, 'deer', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (19, 'duck', 0, 'duck', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (20, 'llama', 0, 'llama', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (21, 'shark', 0, 'shark', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (22, 'snail', 0, 'snail', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (23, 'stork', 0, 'stork', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (24, 'whale', 0, 'whale', 3, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (25, 'chicken', 0, 'chicken', 4, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (26, 'pig', 0, 'pig', 4, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (27, 'spider', 0, 'spider', 4, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (28, 'turtle', 0, 'turtle', 4, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (29, 'wasp', 0, 'wasp', 4, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (30, 'falcon', 0, 'falcon', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (31, 'gorilla', 0, 'gorilla', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (32, 'horse', 0, 'horse', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (33, 'insect', 0, 'insect', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (34, 'panda', 0, 'panda', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (35, 'sheep', 0, 'sheep', 5, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (36, 'beaver', 0, 'beaver', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (37, 'butterfly', 0, 'butterfly', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (38, 'dolphin', 0, 'dolphin', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (39, 'elephant', 0, 'elephant', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (40, 'hornet', 0, 'hornet', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (41, 'prawn', 0, 'prawn', 6, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (42, 'giraffe', 0, 'giraffe', 7, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (43, 'ladybird', 0, 'ladybird', 7, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (44, 'leopard', 0, 'leopard', 7, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (45, 'rabbit', 0, 'rabbit', 7, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (46, 'unicorn', 0, 'unicorn', 7, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (47, 'aquarium', 0, 'aquarium', 8, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (48, 'mosquito', 0, 'mosquito', 8, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (49, 'puffin bird', 0, 'puffin_bird', 8, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (50, 'starfish', 0, 'starfish', 8, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (51, 'tentacles', 0, 'tentacles', 8, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (52, 'badger', 0, 'badger', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (53, 'bumblebee', 0, 'bumblebee', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (54, 'caterpillar', 0, 'caterpillar', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (55, 'dinosaur', 0, 'dinosaur', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (56, 'dragonfly', 0, 'dragonfly', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (57, 'grasshopper', 0, 'grasshopper', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (58, 'kangaroo', 0, 'kangaroo', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (59, 'octopus', 0, 'octopus', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (60, 'rhinoceros', 0, 'rhinoceros', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (61, 'seahorse', 0, 'seahorse', 9, 1);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (62, 'anesthesia', 0, 'anesthesia', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (63, 'antibiotic', 0, 'antibiotic', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (64, 'arm', 0, 'arm', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (65, 'articulation', 0, 'articulation', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (66, 'bacteria', 0, 'bacteria', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (67, 'band aid', 0, 'band_aid', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (68, 'blood', 0, 'blood', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (69, 'bone', 0, 'bone', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (70, 'braces', 0, 'braces', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (71, 'brain', 0, 'brain', 10, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (72, 'canine', 0, 'canine', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (73, 'cardiogram', 0, 'cardiogram', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (74, 'caries', 0, 'caries', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (75, 'crutches', 0, 'crutches', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (76, 'dental drill', 0, 'dental_drill', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (77, 'dental floss', 0, 'dental_floss', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (78, 'dna', 0, 'dna', 11, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (79, 'dropper', 0, 'dropper', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (80, 'drugs', 0, 'drugs', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (81, 'ear', 0, 'ear', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (82, 'earbuds', 0, 'earbuds', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (83, 'edema', 0, 'edema', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (84, 'electric toothbrush', 0, 'electric_toothbrush', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (85, 'epidermis', 0, 'epidermis', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (86, 'extraction', 0, 'extraction', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (87, 'eye drops', 0, 'eye_drops', 12, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (88, 'eye', 0, 'eye', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (89, 'femur', 0, 'femur', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (90, 'finger', 0, 'finger', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (91, 'first aid kit', 0, 'first_aid_kit', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (92, 'fit', 0, 'fit', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (93, 'gum', 0, 'gum', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (94, 'hammer', 0, 'hammer', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (95, 'heart', 0, 'heart', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (96, 'implants', 0, 'implants', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (97, 'inhalator', 0, 'inhalator', 13, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (98, 'intestines', 0, 'intestines', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (99, 'iris', 0, 'iris', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (100, 'kidney', 0, 'kidney', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (101, 'knee', 0, 'knee', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (102, 'kneecap', 0, 'kneecap', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (103, 'liver', 0, 'liver', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (104, 'lozenge', 0, 'lozenge', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (105, 'lungs 2', 0, 'lungs_2', 14, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (106, 'lungs', 20, 'lungs', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (107, 'medical records', 0, 'medical_records', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (108, 'medicine', 0, 'medicine', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (109, 'microbe', 0, 'microbe', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (110, 'mirror', 0, 'mirror', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (111, 'molar 3', 0, 'molar_3', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (112, 'molar', 0, 'molar', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (113, 'nose', 0, 'nose', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (114, 'notepad', 0, 'notepad', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (115, 'ointment', 0, 'ointment', 15, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (116, 'optical', 0, 'optical', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (117, 'otoscope', 0, 'otoscope', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (118, 'pancreas', 0, 'pancreas', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (119, 'perfusion', 0, 'perfusion', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (120, 'pills', 0, 'pills', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (121, 'plastered foot', 0, 'plastered foot', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (122, 'records', 0, 'records', 16, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (123, 'ribbon', 0, 'ribbon', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (124, 'scalpel', 0, 'scalpel', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (125, 'scissors', 0, 'scissors', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (126, 'skeleton', 0, 'skeleton', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (127, 'skull', 0, 'skull', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (128, 'spinal column', 0, 'spinal column', 17, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (129, 'stethoscope', 0, 'stethoscope', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (130, 'stomach', 0, 'stomach', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (131, 'syringe', 0, 'syringe', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (132, 'tablets', 0, 'tablets', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (133, 'teeth', 0, 'teeth', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (134, 'thermometer', 0, 'thermometer', 18, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (135, 'thin', 0, 'thin', 19, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (136, 'tonsils tester', 0, 'tonsils tester', 19, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (137, 'tooth brush', 0, 'tooth brush', 19, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (138, 'tooth pliers', 0, 'tooth pliers', 19, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (139, 'tweezers', 0, 'tweezers', 19, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (140, 'virus', 0, 'virus', 20, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (141, 'wheelchair', 0, 'wheelchair', 20, 2);");
                db.execSQL("INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) VALUES (142, 'x ray', 0, 'x_ray', 20, 2);");
                //Categorie 2
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

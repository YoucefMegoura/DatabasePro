package dz.youcefmegoura.test.databasepro.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Objects.Categorie;
import dz.youcefmegoura.test.databasepro.Objects.Image;
import dz.youcefmegoura.test.databasepro.Objects.Niveau;


/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {


    private final static int DB_VERSION = 1;

    public String getDB_NAME() {
        return DB_NAME;
    }

    private String DB_NAME;

    public DatabaseManager(Context context, String DB_NAME) {
        super(context, DB_NAME, null, DB_VERSION);
        this.DB_NAME = DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creation de la table categorie
        String strSql_categorie = "CREATE TABLE categorie ("
                + "      id_categorie INTEGER NOT NULL UNIQUE,"
                + "      nom_categorie TEXT NOT NULL,"
                + "      score_categorie INTEGER NOT NULL DEFAULT 0,"
                + "      PRIMARY KEY(id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_categorie );

        //Creation de la table niveau
        String strSql_niveau = "CREATE TABLE niveau ("
                + "      id_niveau INTEGER NOT NULL UNIQUE,"
                + "      nom_niveau TEXT NOT NULL,"
                + "      score_niveau INTEGER NOT NULL DEFAULT 0,"
                + "      id_categorie INTEGER NOT NULL,"
                + "      PRIMARY KEY(id_niveau),"
                + "      FOREIGN KEY(id_categorie) REFERENCES categorie(id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_niveau );

        //Creation de la table image
        String strSql_images = "CREATE TABLE image ("
                + "      id_image INTEGER NOT NULL UNIQUE,"
                + "      nom_image TEXT NOT NULL,"
                + "      score_image INTEGER DEFAULT (0),"
                + "      url_image    TEXT,"
                + "      id_niveau INTEGER NOT NULL ,"
                + "      id_categorie INTEGER NOT NULL," //+ id_user foreing key references (id_user)
                + "      PRIMARY KEY (id_image),"
                +"       FOREIGN KEY (id_niveau) REFERENCES niveau (id_niveau),"
                + "      FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_images );

        Log.i( "DATABASE", "onCreate invoked" );


        PopulateDatabase.populate_categorie(db, DB_NAME);
        PopulateDatabase.populate_niveau(db, DB_NAME);
        PopulateDatabase.populate_image(db, DB_NAME);

        //jetons

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Rien a changer pour le moment ...
        Log.i( "DATABASE", "onUpgrade invoked" );
    }

    //Récupperer la liste des image de la table "image" where id_categorie et id_niveau
    public ArrayList<Image> readFrom_ImageTable_where_categorie_and_niveau(int id_categorie, int id_niveau){
        ArrayList<Image> arrayList_Images = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "image",
                new String[] { "id_image", "nom_image", "score_image", "url_image", "id_niveau", "id_categorie" },
                "id_niveau = " + id_niveau + " and id_categorie = " + id_categorie, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Image image = new Image( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getInt( 2 ), cursor.getString(3)  );
            arrayList_Images.add( image );
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_Images;
    }

    //Récupperer la liste des categorie de la table "categorie" where
    public ArrayList<Categorie> readFrom_CategorieTable(){
        ArrayList<Categorie> arrayList_categories = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "categorie",
                new String[] { "id_categorie", "nom_categorie", "score_categorie"},
                null, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Categorie categorie = new Categorie( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getInt( 2 ));
            arrayList_categories.add( categorie );
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_categories;
    }

    //Récupperer la liste des niveaux de la table "niveau" where id_categorie
    public ArrayList<Niveau> readFrom_NiveauTable_where_categorie(int id_categorie){
        ArrayList<Niveau> arrayList_Niveaux = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "niveau",
                new String[] { "id_niveau", "nom_niveau", "score_niveau", "id_categorie" },
                "id_categorie = " + id_categorie, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Niveau niveau = new Niveau( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getInt( 2 )  );
            arrayList_Niveaux.add( niveau );
            cursor.moveToNext();
        }
        cursor.close();

        return arrayList_Niveaux;
    }

    //Recupperer la somme des scores de toutes les images qui se trouvent dans le niveau
    public int somme_score_images_dans_niveau(int id_categorie, int id_niveau){
        int score_image = 0;
        Cursor cursor = this.getReadableDatabase().query( "image",
                new String[] { "score_image" },
                "id_niveau = " + id_niveau + " and id_categorie = " + id_categorie, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            score_image = score_image + cursor.getInt( 0 );
            cursor.moveToNext();
        }
        cursor.close();

        return score_image;
    }

    //Recupperer la somme des scores de toutes les niveaux qui se trouvent dans la categorie
    public int somme_score_niveaux_dans_categorie(int id_categorie) {
        int score_niveau = 0;

        Cursor cursor = this.getReadableDatabase().query( "niveau",
                new String[] { "score_niveau" },
                "id_categorie = " + id_categorie, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            score_niveau = score_niveau + cursor.getInt( 0 );
            cursor.moveToNext();
        }
        cursor.close();

        return score_niveau;
    }

    //Recupperer la somme des scores de toutes les categories
    public int somme_score_categorie() {
        int score_categorie = 0;

        Cursor cursor = this.getReadableDatabase().query( "categorie",
                new String[] { "score_categorie" },
                null, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            score_categorie = score_categorie + cursor.getInt( 0 );
            cursor.moveToNext();
        }
        cursor.close();

        return score_categorie;
    }

    //Changer le score de l'image where le id_image
    public void changer_score_image(int id_image, int nouveau_score_image){
        String strSql = "UPDATE 'image' SET 'score_image'=" + nouveau_score_image + " WHERE id_image=" + id_image + ";";
        this.getWritableDatabase().execSQL(strSql);
        Log.i( "DATABASE", "Changer_Score_image invoked" );
    }

    //Changer le score du niveau where le id_niveau
    public void changer_score_niveau(int id_niveau, int nouveau_score_niveau){
        String strSql = "UPDATE 'niveau' SET 'score_niveau'=" + nouveau_score_niveau + " WHERE id_niveau=" + id_niveau + ";";
        this.getWritableDatabase().execSQL(strSql);
        Log.i( "DATABASE", "changer_score_niveau invoked" );
    }

    //Changer le score de la categorie where le id_categorie
    public void changer_score_categorie(int id_categorie, int nouveau_score_categorie){
        String strSql = "UPDATE 'categorie' SET 'score_categorie'=" + nouveau_score_categorie + " WHERE id_categorie=" + id_categorie + ";";
        this.getWritableDatabase().execSQL(strSql);
        Log.i( "DATABASE", "changer_score_categorie invoked" );
    }

    public int combien_dimage_dans_niveau(int id_categorie, int id_niveau){
        int count = 0;

        Cursor cursor = this.getReadableDatabase().query( "image",
                new String[] { "count(id_image)" },
                "id_niveau = "+id_niveau+" and id_categorie =" + id_categorie, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            count = cursor.getInt( 0 );
            cursor.moveToNext();
        }
        cursor.close();

        return count;
    }

    /*
    public int calculateScore (){
        ArrayList<LevelScore> arrayList = new ArrayList<>(readTop10());
        int somme = 0;
        for(int i = 0; i<arrayList.size();i++){
            somme = somme + arrayList.get(i).getScore();
        }
        return somme;
    }
    */



    /*
    INSERT INTO THE DATABASE METHODES

    public void insert_image_in_db(int id_image, String nom_image, int score_image, int id_niveau, int id_categorie){
        String strSql = "INSERT INTO image (id_image, nom_image, score_image, url_image, id_niveau, id_categorie) " +
                "VALUES (" + id_image + ", " + nom_image + ", " + score_image + ", " + id_niveau + ", " + id_categorie + ")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "INSERT query of image invoked");
    }

    public void insert_niveau_in_db(int id_niveau, String nom_niveau, int score_niveau, int id_categorie){
        String strSql = "INSERT INTO niveau (id_niveau, nom_niveau, score_niveau, url_niveau, id_categorie) " +
                "VALUES (" + id_niveau + ", " + nom_niveau + ", " + score_niveau + ", " + id_categorie + ")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "INSERT query of niveau invoked");
    }

    public void insert_categorie_in_db(int id_categorie, String nom_categorie, int score_categorie){
        String strSql = "INSERT INTO categorie (id_categorie, nom_categorie, score_categorie " +
                "VALUES (" + id_categorie + ", " + nom_categorie + ", " + score_categorie + ")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "INSERT query of categorie invoked");
    }
*/


}

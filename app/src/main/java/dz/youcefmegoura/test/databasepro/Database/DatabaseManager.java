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
 * Created by Youcef Mégoura on 21/04/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private final static String DB_NAME = "sqlite.db";
    private final static int DB_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql_categorie = "CREATE TABLE categorie ("
                + "     id_categorie INTEGER NOT NULL UNIQUE,"
                + "     nom_categorie TEXT NOT NULL,"
                + "     score_categorie INTEGER NOT NULL DEFAULT 0,"
                + "     PRIMARY KEY(id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_categorie );

        String strSql_niveau = "CREATE TABLE niveau ("
                + "     id_niveau INTEGER NOT NULL UNIQUE,"
                + "     nom_niveau TEXT NOT NULL,"
                + "     score_niveau INTEGER NOT NULL DEFAULT 0,"
                + "     id_categorie INTEGER NOT NULL,"
                + "     PRIMARY KEY(id_niveau),"
                + "     FOREIGN KEY(id_categorie) REFERENCES categorie(id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_niveau );



        String strSql_images = "CREATE TABLE image ("
                + "      id_image INTEGER NOT NULL UNIQUE,"
                + "      nom_image TEXT NOT NULL,"
                + "      score_image INTEGER DEFAULT (0),"
                + "      url_image    TEXT,"
                + "      id_niveau INTEGER NOT NULL ,"
                + "      id_categorie INTEGER NOT NULL,"
                + "      PRIMARY KEY (id_image),"
                +"       FOREIGN KEY (id_niveau) REFERENCES niveau (id_niveau),"
                + "      FOREIGN KEY (id_categorie) REFERENCES categorie (id_categorie)"
                + ") WITHOUT ROWID;";
        db.execSQL( strSql_images );


        Log.i( "DATABASE", "onCreate invoked" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //String strSql = "alter table T_Scores add column ...";
        String strSql = "drop table image";
        db.execSQL( strSql );
        this.onCreate( db );
        Log.i( "DATABASE", "onUpgrade invoked" );
    }
 
    public void insertScore( int id,String name, int score ) {
        name = name.replace( "'", "''" );
        String strSql = "insert into categorie (id_categorie, nom_categorie, score_categorie) values (" + id +",'"
                + name + "', " + score + ")";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertScore invoked" );
    }


//    public void changeScore(int id, int newScore){
//        String strSql = "UPDATE 'T_Scores' SET 'score'=" + newScore + " WHERE idScore='" + id + "';";
//        this.getWritableDatabase().execSQL(strSql);
//
//        Log.i( "DATABASE", "ChangeScore invoked" );
//    }


    /********************************selection***********************/
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

    public ArrayList<Categorie> readFrom_CategorieTable(){
        ArrayList<Categorie> arrayList_categories = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "categorie",
                new String[] { "id_categorie", "nom_categorie", "score_categorie"},
                null, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Categorie categorie = new Categorie( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getInt( 2 )  );
            arrayList_categories.add( categorie );
            cursor.moveToNext();
        }
        cursor.close();


        return arrayList_categories;
    }


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

    public void changer_score_image(int id_image, int nouveau_score_image){
        String strSql = "UPDATE 'image' SET 'score_image'=" + nouveau_score_image + " WHERE id_image=" + id_image + ";";
        this.getWritableDatabase().execSQL(strSql);
        Log.i( "DATABASE", "ChangeScore invoked 0" );
    }

    public Image readFrom_ImageTable_one_image(int id_image){
        Cursor cursor = this.getReadableDatabase().query( "image",
                new String[] { "id_image", "nom_image", "score_image", "url_image", "id_niveau", "id_categorie" },
                "id_image = " + id_image, null, null, null, null, null );
        cursor.moveToFirst();
        Image image = new Image( cursor.getInt( 0 ), cursor.getString( 1 ),
                cursor.getInt( 2 ), cursor.getString(3)  );
        return image;
    }
    /*
    public ArrayList<LevelScore> readTop10() {
        ArrayList<LevelScore> scores = new ArrayList<>();

        // 1ère technique : SQL
        //String strSql = "select * from T_Scores order by score desc limit 10";
        //Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );

        // 2nd technique "plus objet"
        Cursor cursor = this.getReadableDatabase().query( "T_Scores",
                new String[] { "idScore", "name", "score" },
                null, null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            LevelScore score = new LevelScore( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getInt( 2 )  );
            scores.add( score );
            cursor.moveToNext();
        }
        cursor.close();

        return scores;
    }

    public int calculateScore (){
        ArrayList<LevelScore> arrayList = new ArrayList<>(readTop10());
        int somme = 0;
        for(int i = 0; i<arrayList.size();i++){
            somme = somme + arrayList.get(i).getScore();
        }
        return somme;
    }
    */


}

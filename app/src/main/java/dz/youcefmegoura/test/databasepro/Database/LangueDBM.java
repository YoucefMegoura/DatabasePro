package dz.youcefmegoura.test.databasepro.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Objects.Langue;
import dz.youcefmegoura.test.databasepro.Objects.Niveau;

/**
 * Created by Youcef Mégoura on 14/06/2018.
 */

public class LangueDBM extends SQLiteOpenHelper {


    private final static String DB_NAME = "langue.db";
    private final static int DB_VERSION = 1;



    public LangueDBM(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creation de la table categorie
        String strSql_categorie = "CREATE TABLE langue ("
                + "      id_langue INTEGER NOT NULL UNIQUE,"
                + "      nom_langue TEXT NOT NULL,"
                + "      url_image_langue TEXT NOT NULL ,"
                + "      db_name TEXT NOT NULL,"
                + "      if_selected INTEGER,"
                + "      PRIMARY KEY(id_langue)"
                + ") WITHOUT ROWID;";
        db.execSQL(strSql_categorie);

        populate_langue(db);

        Log.i( "DATABASE", "onCreate invoked" );



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i( "DATABASE", "onUpgrade invoked" );

    }

    public void populate_langue(SQLiteDatabase db){
        db.execSQL("INSERT INTO langue (id_langue, nom_langue, url_image_langue, db_name, if_selected) " +
                "VALUES (1, 'english', 'english', 'english.db', 1);");
        db.execSQL("INSERT INTO langue (id_langue, nom_langue, url_image_langue, db_name, if_selected) " +
                "VALUES (2, 'french', 'french', 'french.db', 0);");
        db.execSQL("INSERT INTO langue (id_langue, nom_langue, url_image_langue, db_name, if_selected) " +
                "VALUES (3, 'german', 'german', 'german.db', 0);");


    }

    public void ajouter_a_selected(String nom_langue){
        this.getWritableDatabase().execSQL("UPDATE langue SET if_selected = 1 WHERE nom_langue = '" + nom_langue + "';");
    }

    public ArrayList<Langue> get_selected_langues(){
        ArrayList<Langue> langueArrayList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "langue",
                new String[] { "id_langue", "nom_langue", "url_image_langue", "db_name" },
                "if_selected = 1", null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Langue langue = new Langue( cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3) );
            langueArrayList.add( langue );
            cursor.moveToNext();
        }
        cursor.close();

        return langueArrayList;
    }

    public ArrayList<Langue> get_non_selected_langues(){
        ArrayList<Langue> langueArrayList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( "langue",
                new String[] { "id_langue", "nom_langue", "url_image_langue", "db_name" },
                "if_selected = 0", null, null, null, null, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Langue langue = new Langue( cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3) );
            langueArrayList.add( langue );
            cursor.moveToNext();
        }
        cursor.close();

        return langueArrayList;
    }


}

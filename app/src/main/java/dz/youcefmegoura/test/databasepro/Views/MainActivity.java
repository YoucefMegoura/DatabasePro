package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.R;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class MainActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int a = 5;

        //databaseManager = new DatabaseManager(this, );

        sharedPreferences = getSharedPreferences("int", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("integer", a);
        editor.apply();
    }

    // onCLick Button
    public void commencer_click(View view) {
        startActivity(new Intent(this, ChoisirLangue.class));
    }

    public void sinscrire_click(View view) {
        startActivity(new Intent(this, Inscription.class));
    }

    public void jai_un_compte_click(View view) {
        startActivity(new Intent(this, Connexion.class));
    }
}
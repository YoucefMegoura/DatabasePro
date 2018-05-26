package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.R;

public class ChoisirLangue extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_langue);
    }

    public void englishClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("DB_NAME", "english.db");
        Intent intent = new Intent(this, ListeCategories.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }
    public void frenchClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("DB_NAME", "french.db");
        Intent intent = new Intent(this, ListeCategories.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void germanClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("DB_NAME", "german.db");
        Intent intent = new Intent(this, ListeCategories.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



}

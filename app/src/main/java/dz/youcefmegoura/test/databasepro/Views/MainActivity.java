package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.R;

public class MainActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);
    }

    // onCLick Button
    public void ClickBtn(View view) {
        startActivity(new Intent(this, ListeCategories.class));
    }
}
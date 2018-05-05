package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dz.youcefmegoura.test.databasepro.R;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
    }

    public void se_connecter_click(View view) {
    }

    public void se_connecter_google_click(View view) {
    }

    public void se_connecter_facebook_click(View view) {
    }

    public void back_click(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

}

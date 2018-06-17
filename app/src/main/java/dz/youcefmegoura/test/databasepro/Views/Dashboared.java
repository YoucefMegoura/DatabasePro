package dz.youcefmegoura.test.databasepro.Views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.AboutUs;

public class Dashboared extends AppCompatActivity {

    private Dialog myDialog;
    Button yes, no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboared);
    }

    public void Jouer_btn(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("DB_NAME", "english.db");
        Intent intent = new Intent(this, ListeCategories.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void multijouer_btn(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("DB_NAME", "english.db");
        Intent intent = new Intent(this, ListeCategories.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void choisir_langue_btn(View view) {
        startActivity(new Intent(this, ChoisirLangue.class));
    }

    public void Statistiques_btn(View view) {
        startActivity(new Intent(this, Statistiques.class));

    }

    public void Exit_btn(View view) {
        CustomAlertDialog();
    }
    public void CustomAlertDialog(){
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.exit_dialog);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        yes = (Button) myDialog.findViewById(R.id.yes_btn);
        no = (Button) myDialog.findViewById(R.id.no_btn);


        yes.setEnabled(true);
        no.setEnabled(true);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });
        myDialog.show();
    }

    public void Settings_btn(View view) {
        startActivity(new Intent(this, SettingsActivity.class));

    }

    public void About_us_btn(View view) {
        startActivity(new Intent(this, AboutUs.class));
    }
}

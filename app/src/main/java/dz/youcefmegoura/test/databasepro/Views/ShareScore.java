package dz.youcefmegoura.test.databasepro.Views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.AboutUs;

public class ShareScore extends AppCompatActivity {

    CircleMenu circleMenu ;
    Toolbar toolbar;
    public Dialog myDialog;
    Button yes, no;
 String list []= {"Facebook","Twitter", "Instagram","Whatsapp"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_score);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        circleMenu= (CircleMenu)findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add, R.drawable.remove)
                .addSubMenu(Color.parseColor("#25BCFF"),R.drawable.facebk)
                .addSubMenu(Color.parseColor("#6d4c41"),R.drawable.twitter)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.instagram)
                .addSubMenu(Color.parseColor("#1a237e"),R.drawable.whatsapp)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(ShareScore.this, list[i], Toast.LENGTH_SHORT).show();

                    }
                });
    }

    //creation Menu toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;

    }
    //select item menu toolbar


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myintent;
        int id = item.getItemId();
        switch (id){
            case R.id.setting_id:
                myintent= new Intent(this, SettingsActivity.class);
                startActivity(myintent);
                break;
            case R.id.aboutus_id:
                myintent= new Intent(this, AboutUs.class);
                startActivity(myintent);
                break;
            case R.id.dashboard_id:
                myintent= new Intent(this, Dashboared.class);
                startActivity(myintent);

                break;
            case R.id.logout_id:
                CustomAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);



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

    ////////////////////////////////////////////////////////////////////////////////

}

package dz.youcefmegoura.test.databasepro.Views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import dz.youcefmegoura.test.databasepro.R;

public class ShareScore extends AppCompatActivity {

    CircleMenu circleMenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_score);

        circleMenu= (CircleMenu)findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add, R.drawable.remove)
                .addSubMenu(Color.parseColor("#25BCFF"),R.drawable.facebk)
                .addSubMenu(Color.parseColor("#6d4c41"),R.drawable.twitter)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.instagram)
                .addSubMenu(Color.parseColor("#1a237e"),R.drawable.whatsapp)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(ShareScore.this, "hello", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}

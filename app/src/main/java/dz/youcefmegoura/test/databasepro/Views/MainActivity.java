package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.SlideAdapter;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class MainActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //SlideLayout
    private ViewPager mSlideViewPager;
    private LinearLayout mdotLayout;
    private TextView[]mdots;
    private SlideAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //  databaseManager = new DatabaseManager(this);

        sharedPreferences = getSharedPreferences("int", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();

        //SlideLayout
        mSlideViewPager= (ViewPager)findViewById(R.id.slideviewPager);
        mdotLayout= (LinearLayout)findViewById(R.id.dotslayout);
        sliderAdapter=new SlideAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }
    //Add_Dots_Slide
    public void addDotsIndicator(int position){
        mdots = new TextView[4];
        mdotLayout.removeAllViews();

        for (int i=0 ;i < mdots.length;i++){
            mdots[i]= new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparent));

            mdotLayout.addView(mdots[i]);
        }

        if (mdots.length>0){
            mdots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    //ViewPager
    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    // onCLick Button
    public void commencer_click(View view) {
        startActivity(new Intent(this, Dashboared.class));

    }

    public void sinscrire_click(View view) {
        startActivity(new Intent(this, Inscription.class));
    }

    public void jai_un_compte_click(View view) {
        startActivity(new Intent(this, Connexion.class));
    }
}
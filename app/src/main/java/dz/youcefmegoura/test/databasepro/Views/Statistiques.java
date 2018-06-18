package dz.youcefmegoura.test.databasepro.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Categorie;
import dz.youcefmegoura.test.databasepro.Objects.Langue;
import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.AboutUs;

public class Statistiques extends AppCompatActivity {

    private ArrayList<Categorie> Categories_array_EN = new ArrayList<>();
    private ArrayList<Categorie> Categories_array_FR = new ArrayList<>();
    private ArrayList<Categorie> Categories_array_GR = new ArrayList<>();

    private DatabaseManager databaseManagerEn, databaseManagerFr, databaseManagerGr;
    protected static String DB_NAME;
    /////////////////////////////////////////////

    public Dialog myDialog;
    Button yes, no;
    Toolbar toolbar;

    private ExpandableListAdapter ExpAdapter;
    private ArrayList<Langue> ExpListlangue;
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        databaseManagerEn = new DatabaseManager(this, "english.db");
        databaseManagerFr = new DatabaseManager(this, "french.db");
        databaseManagerGr = new DatabaseManager(this, "german.db");
        Categories_array_EN = new ArrayList<Categorie>(databaseManagerEn.readFrom_CategorieTable()) ;
        Categories_array_FR = new ArrayList<Categorie>(databaseManagerFr.readFrom_CategorieTable()) ;
        Categories_array_GR = new ArrayList<Categorie>(databaseManagerGr.readFrom_CategorieTable()) ;





    }
    @Override
    protected void onResume() {
        super.onResume();
        ExpandList = (ExpandableListView) findViewById(R.id.list_viewStat);
        ExpListlangue = SetStandardGroups();
        ExpAdapter = new ExpandableListAdapter(Statistiques.this, ExpListlangue);
        ExpandList.setAdapter(ExpAdapter);
       //databaseManager.close();

    }

    public ArrayList<Langue> SetStandardGroups() {
        ArrayList<Integer> score_english = new ArrayList<>();

        int langue_images[] = {R.drawable.english, R.drawable.french, R.drawable.german, R.drawable.portugais, R.drawable.italian
                , R.drawable.spanish};


        int Images[] = {R.mipmap.animals, R.mipmap.arts, R.mipmap.communication,
                R.mipmap.sport, R.mipmap.medecine, R.mipmap.music,
                R.mipmap.transport, R.mipmap.informatique, R.mipmap.animals, R.mipmap.science};

        ArrayList<Langue> list = new ArrayList<Langue>();
        ArrayList<Categorie> ch_list;

        for (Integer langue_image : langue_images) {

            Langue gru = new Langue();
            gru.setImage_langue(langue_image);

            ch_list = new ArrayList<Categorie>();
            for (int j =0 ; j < Categories_array_EN.size(); j++) {
                score_english.add(Categories_array_EN.get(j).getScore_categorie());

                Categorie ch = new Categorie();
                ch.setNom_categorie(Categories_array_EN.get(j).getNom_categorie());
                ch.setImage(Images[j]);

                ch.setScore_categorie(Categories_array_EN.get(j).getScore_categorie());
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

        }

        return list;
    }

    public void back_click(View view) {finish();
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private ArrayList<Langue> langues;



        public ExpandableListAdapter(Context context, ArrayList<Langue> langues) {
            this.context = context;
            this.langues = langues;
        }


        @Override
        public Object getChild(int groupPosition, int childPosition) {
            ArrayList<Categorie> categories = langues.get(groupPosition).getItems();
            return categories.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            Categorie categorie = (Categorie) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_cat_stat, null);
            }
            TextView categorie_name = (TextView) convertView.findViewById(R.id.text_cat_stat);
            ImageView categorie_image = (ImageView) convertView.findViewById(R.id.img_cat_stat);
            TextView categorie_score = (TextView) convertView.findViewById(R.id.text_scor_cat);

            categorie_name.setText(categorie.getNom_categorie().toString());
            categorie_image.setImageResource(categorie.getImage());
            categorie_score.setText(String.valueOf(categorie.getScore_categorie()));

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<Categorie> categoriesList = langues.get(groupPosition).getItems();
            return categoriesList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return langues.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return langues.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            Langue langue = (Langue) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inf = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inf.inflate(R.layout.list_langue_stat, null);
            }

            ImageView langue_image = convertView.findViewById(R.id.imag_languestat);
            langue_image.setImageResource(langue.getImage_langue());


            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }




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
    ////////////////////exit menu toolbar///////////////////////
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
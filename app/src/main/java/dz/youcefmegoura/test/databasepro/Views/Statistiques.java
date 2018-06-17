package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Categorie;
import dz.youcefmegoura.test.databasepro.Objects.Langue;
import dz.youcefmegoura.test.databasepro.R;

public class Statistiques extends AppCompatActivity {

    private ArrayList<Categorie> Categories_array = new ArrayList<>();
    private DatabaseManager databaseManager;
    protected static String DB_NAME;
    private int id_categorie_from_bundle;
    /////////////////////////////////////////////

    private ExpandableListAdapter ExpAdapter;
    private ArrayList<Langue> ExpListlangue;
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);

        /*****************Get from Bundle****************/
      //  Bundle bundle = getIntent().getExtras();
        // id_categorie_from_bundle = bundle.getInt("id_categorie");

       // databaseManager = new DatabaseManager(this, Statistiques.DB_NAME);

    }
    @Override
    protected void onResume() {
        super.onResume();
       //Categories_array = new ArrayList<Categorie>(databaseManager.readFrom_CategorieTable()) ;
        ExpandList = (ExpandableListView) findViewById(R.id.list_viewStat);
        ExpListlangue = SetStandardGroups();
        ExpAdapter = new ExpandableListAdapter(Statistiques.this, ExpListlangue);
        ExpandList.setAdapter(ExpAdapter);
       //databaseManager.close();

    }

    public ArrayList<Langue> SetStandardGroups() {

        int langue_images[] = {R.drawable.english, R.drawable.french, R.drawable.german, R.drawable.portugais, R.drawable.italian
                , R.drawable.spanish};
        String categories_names[] = {"Animals", "Arts", "Communication", "Sport", "medecine", "Musique", "Transport"
                , "Informatique", "Nourriture", "Science"};

        int Images[] = {R.mipmap.animals, R.mipmap.arts, R.mipmap.communication,
                R.mipmap.sport, R.mipmap.medecine, R.mipmap.music,
                R.mipmap.transport, R.mipmap.informatique, R.mipmap.animals, R.mipmap.science};

        ArrayList<Langue> list = new ArrayList<Langue>();
        ArrayList<Categorie> ch_list;

        for (Integer langue_image : langue_images) {
            Langue gru = new Langue();
            gru.setImage_langue(langue_image);

            ch_list = new ArrayList<Categorie>();
            for (int j =0 ; j < categories_names.length; j++) {
                Categorie ch = new Categorie();
                ch.setNom_categorie(categories_names[j]);
                ch.setImage(Images[j]);
                //TODO : hnaya lazém tjibou men la BD set
                // ch.setScore_categorie(ch_list.get(j).getScore_categorie());
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

        }

        return list;
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
//            categorie_score.setText(categorie.getScore_categorie());

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

}
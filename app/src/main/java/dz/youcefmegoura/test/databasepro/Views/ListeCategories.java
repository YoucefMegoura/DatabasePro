package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import at.markushi.ui.CircleButton;
import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Database.PopulateDatabase;
import dz.youcefmegoura.test.databasepro.Objects.Categorie;
import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.AboutUs;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class ListeCategories extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Categorie> Categories_array = new ArrayList<>();
    private DatabaseManager databaseManager;
    protected static String DB_NAME;

    ListView ls;

    Toolbar toolbar;

    TextView text_animals, text_arts ,text_communication, text_sport, text_medecine , text_musique,
            text_transport ,text_informatique ,text_nourriture ,text_science;

    CircleButton animals ,arts,communication , sport,medecine,musique,
            transport,informatique,nourriture,science;


    ////////////////////////////////////////////////////////////////////////
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    ArrayList<Integer> images_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_categories);


        images_list.add(R.drawable.french);
        images_list.add(R.drawable.italian);
        images_list.add(R.drawable.german);

        CustAdapt cus = new CustAdapt(this, images_list);
        ls = (ListView) findViewById(R.id.lstDrawerItems);
        ls.setAdapter(cus);


        Bundle bundle = getIntent().getExtras();
        DB_NAME = bundle.getString("DB_NAME");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animals= findViewById(R.id.animals);
        arts= findViewById(R.id.arts);
        communication= findViewById(R.id.communication);
        sport= findViewById(R.id.sport);
        medecine= findViewById(R.id.medical);
        musique= findViewById(R.id.musique);
        transport= findViewById(R.id.transport);
        informatique= findViewById(R.id.informatique);
        nourriture= findViewById(R.id.nourriture);
        science= findViewById(R.id.science);


        animals.setOnClickListener(this);
        arts.setOnClickListener(this);
        communication.setOnClickListener(this);
        sport.setOnClickListener(this);
        medecine.setOnClickListener(this);
        musique.setOnClickListener(this);
        transport.setOnClickListener(this);
        informatique.setOnClickListener(this);
        nourriture.setOnClickListener(this);
        science.setOnClickListener(this);

        text_animals=findViewById(R.id.text_animals);
        text_arts=findViewById(R.id.text_arts);
        text_communication=findViewById(R.id.text_communication);
        text_sport=findViewById(R.id.text_sport);
        text_medecine=findViewById(R.id.text_medecine);
        text_musique=findViewById(R.id.text_musique);
        text_transport=findViewById(R.id.text_transport);
        text_informatique=findViewById(R.id.text_informatique);
        text_nourriture=findViewById(R.id.text_nourriture);
        text_science=findViewById(R.id.text_science);


        ///////////////////////////////////////////
        mdrawerlayout =findViewById(R.id.categorieId);
        mtoggle = new ActionBarDrawerToggle(this,mdrawerlayout,toolbar,R.string.open,R.string.close){



            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                // Code here will execute once drawer is closed
            }


        };


        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
////////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    /////////////////////////////////////////////////////////////////////////////////
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
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutus_id:
                myintent= new Intent(this, AboutUs.class);
                startActivity(myintent);
                break;
            case R.id.dashboard_id:
                Toast.makeText(this, "dashboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_id:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    ////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        databaseManager = new DatabaseManager( this , DB_NAME);
        Categories_array = new ArrayList<Categorie>(databaseManager.readFrom_CategorieTable()) ;

        text_animals.setText(String.valueOf(Categories_array.get(0).getNom_categorie()));
        text_arts.setText(String.valueOf(Categories_array.get(1).getNom_categorie()));
        text_communication.setText(String.valueOf(Categories_array.get(2).getNom_categorie()));

    }
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();


        switch (v.getId()){
            case R.id.animals:
                bundle.putInt("id_categorie", Categories_array.get(0).getId_categorie());
                Toast.makeText(ListeCategories.this, Categories_array.get(0).getNom_categorie(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.arts:
                bundle.putInt("id_categorie", Categories_array.get(1).getId_categorie());
                Toast.makeText(ListeCategories.this, Categories_array.get(1).getNom_categorie(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.communication:
                bundle.putInt("id_categorie", Categories_array.get(2).getId_categorie());
                Toast.makeText(ListeCategories.this, Categories_array.get(2).getNom_categorie(), Toast.LENGTH_SHORT).show();
                break;
        }

        Intent intent = new Intent(ListeCategories.this, ListeNiveaux.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    class CustAdapt extends BaseAdapter {

        ArrayList<Integer> items = new ArrayList<>();
        Context context;

        CustAdapt(Context context,ArrayList items){
            this.context=context;
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater myInflater = getLayoutInflater();
            View myView = myInflater.inflate(R.layout.drawer_menu, null);

            ImageView imageView = (ImageView) myView.findViewById(R.id.image_id);

            imageView.setImageResource(items.get(position));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListeCategories.this, String.valueOf(items.get(position)), Toast.LENGTH_SHORT).show();
                }
            });

//            nom_niveau.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Toast.makeText(Main.this, LevelScore_array.get(position).getName(), Toast.LENGTH_SHORT).show();
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id_niveau", ListeNiveaux_array.get(position).getId_niveau());
//                    bundle.putInt("id_categorie", id_categorie_from_bundle);
//                    Intent intent = new Intent(ListeNiveaux.this, ImageGame.class);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            });
            return myView;
        }
    }

}

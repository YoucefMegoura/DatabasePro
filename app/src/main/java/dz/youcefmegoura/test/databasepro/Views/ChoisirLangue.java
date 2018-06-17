package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Database.LangueDBM;
import dz.youcefmegoura.test.databasepro.Objects.Langue;
import dz.youcefmegoura.test.databasepro.Objects.Niveau;
import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Views.Menu.AboutUs;

public class ChoisirLangue extends AppCompatActivity {

    private ListView listViewLangue;
    private ArrayList<Langue> langueArrayList = new ArrayList<>();
    private LangueDBM langueDBM;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_langue);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Choisir Langage");
        setSupportActionBar(toolbar);

        listViewLangue = (ListView) findViewById(R.id.listView_langue);
        CustAdapt cus = new CustAdapt(langueArrayList);
        listViewLangue.setAdapter(cus);

    }

    @Override
    protected void onResume() {
        super.onResume();
        langueDBM = new LangueDBM( this);
        langueArrayList.addAll(langueDBM.get_non_selected_langues());

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
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    ////////////////////////////////////////////////////////////////////////////////


    /*public void englishClick(View view) {
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
*/
    class CustAdapt extends BaseAdapter {

        ArrayList<Langue> items = new ArrayList<>();

        CustAdapt(ArrayList items){
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
            return items.indexOf(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater myInflater = getLayoutInflater();
            View myView = myInflater.inflate(R.layout.drawer_menu, null);

            ImageView image_langue = myView.findViewById(R.id.image_id);

            int drawableResourceId = getResources().getIdentifier(items.get(position).getUrl_image_langue(), "drawable", getPackageName());

            image_langue.setImageResource(drawableResourceId);

            listViewLangue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    langueDBM.ajouter_a_selected(items.get(position).getNom_langue());
                    Bundle bundle = new Bundle();
                    bundle.putString("DB_NAME", items.get(position).getDb_name());
                    Intent intent = new Intent(ChoisirLangue.this, ListeCategories.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            return myView;
        }
    }

}

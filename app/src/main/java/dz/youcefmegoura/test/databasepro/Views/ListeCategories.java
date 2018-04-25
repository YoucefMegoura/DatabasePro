package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Categorie;
import dz.youcefmegoura.test.databasepro.R;

public class ListeCategories extends AppCompatActivity {

    private  ArrayList<Categorie> Categories_array = new ArrayList<>();
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_categories);
        databaseManager = new DatabaseManager( this );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Categories_array = new ArrayList<Categorie>(databaseManager.readFrom_CategorieTable()) ;



        CustAdapt cus = new CustAdapt(Categories_array);
        ListView ls = (ListView) findViewById(R.id.liste_view_categorie);
        ls.setAdapter(cus);
        databaseManager.close();

    }

    class CustAdapt extends BaseAdapter {

        ArrayList<Categorie> items = new ArrayList<>();

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
            View myView = myInflater.inflate(R.layout.template_categories, null);

            TextView id_categorie = myView.findViewById(R.id.id_categorie);
            TextView nom_categorie = myView.findViewById(R.id.nom_categorie);
            TextView score_categorie = myView.findViewById(R.id.score_categorie);

            id_categorie.setText(String.valueOf(items.get(position).getId_categorie()));
            nom_categorie.setText(items.get(position).getNom_categorie());
            score_categorie.setText(String.valueOf(items.get(position).getScore_categorie()));

            nom_categorie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListeCategories.this, Categories_array.get(position).getNom_categorie(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_categorie", Categories_array.get(position).getId_categorie());
                    Intent intent = new Intent(ListeCategories.this, ListeNiveaux.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return myView;
        }
    }
}

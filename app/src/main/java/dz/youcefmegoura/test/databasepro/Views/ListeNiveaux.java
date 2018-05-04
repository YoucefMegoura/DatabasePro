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

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Niveau;
import dz.youcefmegoura.test.databasepro.R;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class ListeNiveaux extends AppCompatActivity {

    private ArrayList<Niveau> ListeNiveaux_array = new ArrayList<>();
    private DatabaseManager databaseManager;
    private int id_categorie_from_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_niveaux);
        databaseManager = new DatabaseManager( this );

        /*****************Get from Bundle****************/
        Bundle bundle = getIntent().getExtras();
        id_categorie_from_bundle = bundle.getInt("id_categorie");
        /************************************************/

    }

    @Override
    protected void onResume() {
        super.onResume();
        ListeNiveaux_array = new ArrayList<Niveau>(databaseManager.readFrom_NiveauTable_where_categorie(id_categorie_from_bundle)) ;

        CustAdapt cus = new CustAdapt(ListeNiveaux_array);
        ListView ls = (ListView) findViewById(R.id.list_view_niveau);
        ls.setAdapter(cus);
        databaseManager.close();
    }

    class CustAdapt extends BaseAdapter {

        ArrayList<Niveau> items = new ArrayList<>();

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
            View myView = myInflater.inflate(R.layout.template_niveau, null);

            TextView id_niveau = myView.findViewById(R.id.id_niveau);
            TextView nom_niveau = myView.findViewById(R.id.nom_niveau);
            TextView score_niveau = myView.findViewById(R.id.score_niveau);

            id_niveau.setText(String.valueOf(items.get(position).getId_niveau()));
            nom_niveau.setText(items.get(position).getNom_niveau());
            score_niveau.setText(String.valueOf(items.get(position).getScore_niveau()));

            nom_niveau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(Main.this, LevelScore_array.get(position).getName(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_niveau", ListeNiveaux_array.get(position).getId_niveau());
                    bundle.putInt("id_categorie", id_categorie_from_bundle);
                    Intent intent = new Intent(ListeNiveaux.this, ImageGame.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            return myView;
        }
    }
}

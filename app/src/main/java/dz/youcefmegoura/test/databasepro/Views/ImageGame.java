package dz.youcefmegoura.test.databasepro.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Image;
import dz.youcefmegoura.test.databasepro.R;

public class ImageGame extends AppCompatActivity {

    private int id_categorie_from_bundle;
    private int id_niveau_from_bundle;

    private DatabaseManager databaseManager;
    private ArrayList<Image> Images_array;

    private int cursseur_id_array_image;

    ImageView image_view;
    TextView score_text_view, nom_image_textView;
    EditText edit_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game);

        /**********************************************/
        score_text_view = (TextView) findViewById(R.id.score_text_view);
        image_view = (ImageView) findViewById(R.id.image_view);
        nom_image_textView = (TextView) findViewById(R.id.nom_image_textView);
        edit_text = (EditText) findViewById(R.id.edit_text);
        /**********************************************/


        /*****************Get from Bundle****************/
        Bundle bundle = getIntent().getExtras();
        id_categorie_from_bundle = bundle.getInt("id_categorie");
        id_niveau_from_bundle = bundle.getInt("id_niveau");
        /************************************************/


        databaseManager = new DatabaseManager(this);
        Images_array = new ArrayList<>(databaseManager.readFrom_ImageTable_where_categorie_and_niveau(id_categorie_from_bundle, id_niveau_from_bundle));
        cursseur_id_array_image = Images_array.get(0).getId_image();
        afficher_imageObject(cursseur_id_array_image);
    }


    public void afficher_imageObject(int cursseur_id_array_image){
        score_text_view.setText("Score : " + String.valueOf(Images_array.get(cursseur_id_array_image).getScore_image()));
        int drawableResourceId = this.getResources().getIdentifier(Images_array.get(cursseur_id_array_image).getUrl_image(), "drawable", this.getPackageName());
        image_view.setImageResource(drawableResourceId);
        nom_image_textView.setText("Nom image : " + Images_array.get(cursseur_id_array_image).getNom_image());
    }

    public void saveClick(View view) {
        if (edit_text.getText().toString().length() != 0){
            int score = Integer.valueOf(edit_text.getText().toString());
            databaseManager.changer_score_image(cursseur_id_array_image, score);
            score_text_view.setText("Score : " + edit_text.getText().toString());
        }else{
            Toast.makeText(this, "Veillez entrer score", Toast.LENGTH_SHORT).show();
        }
    }


    public void nextClick(View view) {
        if (cursseur_id_array_image == Images_array.size() - 1)
            cursseur_id_array_image = 0;
        else
            cursseur_id_array_image++;
        afficher_imageObject(cursseur_id_array_image);

    }


    public void backClick(View view) {
        if (cursseur_id_array_image == 0)
            cursseur_id_array_image = Images_array.size()-1;
        else
            cursseur_id_array_image--;
        afficher_imageObject(cursseur_id_array_image);
    }

}

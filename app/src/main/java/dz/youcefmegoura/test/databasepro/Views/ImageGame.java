package dz.youcefmegoura.test.databasepro.Views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Database.SharedPref;
import dz.youcefmegoura.test.databasepro.Objects.Image;
import dz.youcefmegoura.test.databasepro.R;
import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class ImageGame extends AppCompatActivity implements RecognitionListener {
    /********  Shared Preferences  ************/

    /********************************************/

    /******************* XML References ******************/
    private ImageView image_view;
    private TextView score_text_view, nom_image_textView, score_tout_categorie, jetons_user;
    private Button save_btn;
    /*****************************************************/

    /***************** To Get from Bundle ****************/
    private int id_categorie_from_bundle;
    private int id_niveau_from_bundle;
    /*****************************************************/

    private TextToSpeech textToSpeech;

    private DatabaseManager databaseManager;
    private ArrayList<Image> Images_array;

    private int cursseur_id_array_image;//Image ID in database
    private int indice;//Array


      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game);

        /*************** XML References ******************/
        score_text_view = (TextView) findViewById(R.id.score_text_view);
        image_view = (ImageView) findViewById(R.id.image_view);
        nom_image_textView = (TextView) findViewById(R.id.nom_image_textView);
        score_tout_categorie = (TextView) findViewById(R.id.score_tout_categorie);
        jetons_user = (TextView) findViewById(R.id.jetons_user);
        save_btn = (Button) findViewById(R.id.save_btn);
        /************************************************/


        /***************** Get from Bundle ****************/
        Bundle bundle = getIntent().getExtras();
        id_categorie_from_bundle = bundle.getInt("id_categorie");
        id_niveau_from_bundle = bundle.getInt("id_niveau");
        /**************************************************/


        /**************** Initialisation ******************/
        indice = 0;
        databaseManager = new DatabaseManager(this);
        Images_array = new ArrayList<>(databaseManager.readFrom_ImageTable_where_categorie_and_niveau(id_categorie_from_bundle, id_niveau_from_bundle));
        cursseur_id_array_image = Images_array.get(indice).getId_image();

        afficher_imageObject(indice);//Afficher la premiere image dans onCreate
        /*************************************************/


        /************* Initialisation Text to Speech ************/
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(ImageGame.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Log.e("Text to Speech", "Initilization Failed !");
            }
        });
        /*******************************************************/


        /********************* Pocketsphinx **********************/
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
            return;
        }

        new SetupTask(this).execute();
        /*********************************************************/

        score_tout_categorie.setText("Total points : " + String.valueOf(databaseManager.somme_score_categorie()));
        jetons_user.setText("Jetons : " + String.valueOf(SharedPref.JETONS_USER));
        save_btn.setEnabled(false);
    }

    //Simple methode pour afficher tout les attributs d'une image dans XML ...
    public void afficher_imageObject(int cursseur_id_array_image) {
        score_text_view.setText("Score : " + String.valueOf(Images_array.get(indice).getScore_image()));
        int drawableResourceId = this.getResources().getIdentifier(Images_array.get(indice).getUrl_image(), "drawable", this.getPackageName());
        image_view.setImageResource(drawableResourceId);
        nom_image_textView.setText("Nom image : " + Images_array.get(indice).getNom_image());
    }

    //onClick Button
    public void saveClick(View view) {
        recognizer.stop();
        recognizer.startListening(WORD_SEARCH, 5000);
    }

    //onClick Button
    public void nextClick(View view) {
        if (indice == Images_array.size() - 1)
            indice = 0;
        else
            indice++;
        cursseur_id_array_image = Images_array.get(indice).getId_image();
        afficher_imageObject(cursseur_id_array_image);

    }

    //onClick Button
    public void backClick(View view) {
        if (indice == 0)
            indice = Images_array.size() - 1;
        else
            indice--;
        cursseur_id_array_image = Images_array.get(indice).getId_image();
        afficher_imageObject(cursseur_id_array_image);
    }

    //onClick Button
    public void speakClick(View view) {
        if (SharedPref.JETONS_USER > 0 ){
            String mot_a_prononce = Images_array.get(indice).getNom_image();
            if (mot_a_prononce == null || mot_a_prononce.length() == 0) {
                Toast.makeText(this, "Some Error occured !", Toast.LENGTH_SHORT).show();
            } else
                textToSpeech.speak(mot_a_prononce, TextToSpeech.QUEUE_FLUSH, null);

            SharedPref.JETONS_USER -- ;
            jetons_user.setText("Jetons : " + String.valueOf(SharedPref.JETONS_USER));
        }else{
            Button button = (Button) findViewById(R.id.speak_btn);
            button.setEnabled(false);
            button.setClickable(false);
        }

    }

    /*
    *
    *
    *
    *  POCKET SPHINX
    *
    *
    * */
    private String grammar_sphinx = "en-en-first.gram";
    private String dictionnaire_sphinx = "cmudict-en-us.dict";
    private String ptm_sphinx = "en-us-ptm";

    private static final String WORD_SEARCH = "world";
    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private SpeechRecognizer recognizer;


    @Override
    public void onBeginningOfSpeech() {    }

    @Override
    public void onEndOfSpeech() {
        recognizer.stop();
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {    }

    @Override
    public void onResult(Hypothesis hypothesis) {

        int score = hypothesis.getBestScore();
        float final_score = 0;
        String mot_a_prononce = Images_array.get(indice).getNom_image();
        if (hypothesis != null) {
            if(if_word_correct(mot_a_prononce, hypothesis)) {
                String text = hypothesis.getHypstr();
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                final_score = getScoreStars(score);

            }else {
                Toast.makeText(this, "mot incorrect !!", Toast.LENGTH_SHORT).show();
                final_score = 0;
            }
            databaseManager.changer_score_image(cursseur_id_array_image, (int)(final_score * 2));
            score_text_view.setText("Score : " + String.valueOf((int)(final_score * 2)));

            /************** Pour changer le score du niveau et de la categorie dans la base de donnée ************/
            //Niveau
            int somme_score_images = databaseManager.somme_score_images_dans_niveau(id_categorie_from_bundle, id_niveau_from_bundle);
            databaseManager.changer_score_niveau(id_niveau_from_bundle, somme_score_images);

            //Categorie
            int somme_score_niveau = databaseManager.somme_score_niveaux_dans_categorie(id_categorie_from_bundle);
            databaseManager.changer_score_categorie(id_categorie_from_bundle, somme_score_niveau);

            //Somme des categories dans l'activité image_activity
            score_tout_categorie.setText("Total points : " + String.valueOf(databaseManager.somme_score_categorie()));
            /*****************************************************************************************************/


        }
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(this, "Some error occured !", Toast.LENGTH_SHORT).show();
        Log.i("Pocketsphinx", "OnError Invoked !");
    }

    @Override
    public void onTimeout() {
        recognizer.stop();
    }

    private class SetupTask extends AsyncTask<Void, Void, Exception> {
        WeakReference<ImageGame> activityReference;
        SetupTask(ImageGame activity) {
            this.activityReference = new WeakReference<>(activity);
        }

        @Override
        protected Exception doInBackground(Void... params) {
            try {
                Assets assets = new Assets(activityReference.get());
                File assetDir = assets.syncAssets();
                activityReference.get().setupRecognizer(assetDir);
            } catch (IOException e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception result) {
            if (result == null) {
                save_btn.setEnabled(true);

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new SetupTask(this).execute();
            } else {
                finish();
            }
        }
    }

    private void setupRecognizer(File assetsDir) throws IOException {

        recognizer = SpeechRecognizerSetup.defaultSetup()
                .setAcousticModel(new File(assetsDir, ptm_sphinx))
                .setDictionary(new File(assetsDir, dictionnaire_sphinx))

                .setRawLogDir(assetsDir) // To disable logging of raw audio comment out this call (takes a lot of space on the device)

                .getRecognizer();
        recognizer.addListener(this);

        // Create keyword-activation search.
        recognizer.addKeyphraseSearch(WORD_SEARCH, WORD_SEARCH);

        // Create grammar-based search for digit recognition
        File digitsGrammar = new File(assetsDir, grammar_sphinx);
        recognizer.addGrammarSearch(WORD_SEARCH, digitsGrammar);
    }

    ////////////////////////////////////////////////////
    public float getScoreStars(int getBestScore){
        if (getBestScore <= 0 && getBestScore > -1000)
            return 3.0f;
        else if (getBestScore <= -1000 && getBestScore > -2000)
            return 2.5f;
        else if (getBestScore <= -2000 && getBestScore > -3000)
            return 2;
        else if (getBestScore <= -3000 && getBestScore > -4000)
            return 1.5f;
        else if (getBestScore <= -4000 && getBestScore > -5000)
            return 1;
        else if (getBestScore <= -5000 && getBestScore > -6000)
            return 0.5f;
        else
            return 0;
    }
    ///////////////////////////////////////////////////

    public Boolean if_word_correct(String mot_a_prononce, Hypothesis hypothesis){
        if (hypothesis.getHypstr().equals(mot_a_prononce))
            return true;
        else
            return false;
    }
    /////////////////////////////////////////////////////
}

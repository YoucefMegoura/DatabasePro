package dz.youcefmegoura.test.databasepro.Views;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

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
import es.dmoral.toasty.Toasty;


/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 21/04/2018.
 */

public class ImageGame extends AppCompatActivity implements RecognitionListener {
    private static int JETON_BEGIN = 12;
    private static int JETON_TO_ADD = 5;

    /********  Shared Preferences  ************/
    private static final String USER_PREFS = "PREFS";
    private static final String PREF_JETON = "JETON_PREFS";
    private int jeton_from_pref ;
    private SharedPreferences sharedPreferences;
    /********************************************/

    /******************* XML References ******************/
    private ImageView image_view;
    private TextView score_text_view, nom_image_textView, nom_categorie, score_tout_categorie, jetons_user,
            congart_score;
    private ImageButton speak_btn;
    private ProgressBar progress;
    private Button next_btn;
    private Dialog myDialog, dialog, exitdialog;
    Button Oui, Non, next, share, yes, no;
    ImageView image_categorie ;

    /*****************************************************/

    /***************** To Get from Bundle ****************/
    private int id_categorie_from_bundle;
    private int id_niveau_from_bundle;
    private  String name_categorie_from_bundle;
    private  int image_categorie_from_bundle;
    /*****************************************************/

    private TextToSpeech textToSpeech;

    private DatabaseManager databaseManager;
    private ArrayList<Image> Images_array;

    private int cursseur_id_array_image;//Image ID in database
    private int indice;//Array


    /*****************Music player******************/
    private MediaPlayer valid_wav, wrog_wav, congratulation_wav;
    /************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game);


        /*********** Initialisation Shared preferences ***********/
        sharedPreferences = getBaseContext().getSharedPreferences(USER_PREFS, MODE_PRIVATE);
        if (sharedPreferences.contains(PREF_JETON)) {
            jeton_from_pref = sharedPreferences.getInt(PREF_JETON, 0);
        }else{
            sharedPreferences
                    .edit()
                    .putInt(PREF_JETON, JETON_BEGIN )
                    .apply();
        }
        /******************************************************/


        choix_language(ListeCategories.DB_NAME);
        /*************** XML References ******************/
        score_text_view = (TextView) findViewById(R.id.score_text_view);
        congart_score= (TextView)findViewById(R.id.congarat_score);
        image_view = (ImageView) findViewById(R.id.image_view);
        score_tout_categorie = (TextView) findViewById(R.id.score_tout_categorie);
        jetons_user = (TextView) findViewById(R.id.jetons_user);
        speak_btn = (ImageButton) findViewById(R.id.speak_btn);
        progress = (ProgressBar) findViewById(R.id.progress);
        next_btn = (Button) findViewById(R.id.next_btn);
        progress = (ProgressBar) findViewById(R.id.progress);
        nom_image_textView = findViewById(R.id.nom_image_textView);
        image_categorie = findViewById(R.id.image_categorie);


        valid_wav = MediaPlayer.create(this, R.raw.wrong);
        wrog_wav = MediaPlayer.create(this, R.raw.valid);
        congratulation_wav = MediaPlayer.create(this, R.raw.congratulation);
        nom_categorie = (TextView)findViewById(R.id.name_categorie_from_bundle);

        /************************************************/



        /***************** Get from Bundle ****************/
        Bundle bundle = getIntent().getExtras();
        id_categorie_from_bundle = bundle.getInt("id_categorie");
        id_niveau_from_bundle = bundle.getInt("id_niveau");
        name_categorie_from_bundle = bundle.getString("nom_categorie");
        image_categorie_from_bundle = bundle.getInt("image_categorie");
        /**************************************************/
        nom_categorie.setText(name_categorie_from_bundle);

        image_categorie.setImageResource(image_categorie_from_bundle);


        /**************** Initialisation ******************/
        indice = 0;
        databaseManager = new DatabaseManager(this, ListeCategories.DB_NAME);
        Images_array = new ArrayList<>(databaseManager.readFrom_ImageTable_where_categorie_and_niveau(id_categorie_from_bundle, id_niveau_from_bundle));
        cursseur_id_array_image = Images_array.get(indice).getId_image();

        afficher_imageObject(indice);//Afficher la premiere image dans onCreate
        progress.setProgress(0);
        /*************************************************/


        /************* Initialisation Text to Speech ************/
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(loc);
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

        score_tout_categorie.setText(String.valueOf(databaseManager.somme_score_categorie()));
        jetons_user.setText( String.valueOf(jeton_from_pref));
        speak_btn.setEnabled(false);
    }

    //Simple methode pour afficher tout les attributs d'une image dans XML ...
    public void afficher_imageObject(int cursseur_id_array_image) {
        score_text_view.setText( String.valueOf(Images_array.get(indice).getScore_image())+"/10");
        int drawableResourceId = this.getResources().getIdentifier(Images_array.get(indice).getUrl_image(), "drawable", this.getPackageName());
        image_view.setImageResource(drawableResourceId);
        nom_image_textView.setText(Images_array.get(indice).getNom_image());
    }

    //onClick Button
    public void startClick(View view) {
        recognizer.stop();
        speak_btn.setBackgroundResource(R.drawable.mic_blue_round);
        speak_btn.setEnabled(false);
        recognizer.startListening(WORD_SEARCH, 5000);
    }

    //onClick Button
    public void nextClick(View view) {
        if (indice == Images_array.size() - 1) {
            next_btn.setVisibility(View.GONE);

            congratulation_wav.start();
            congratulationAlertDialog();


        }else
            indice++;
        cursseur_id_array_image = Images_array.get(indice).getId_image();
        afficher_imageObject(cursseur_id_array_image);

        next_btn.setVisibility(View.GONE);
    }

    //onClick Button
    /*public void backClick(View view) {
        if (indice == 0)
            indice = Images_array.size() - 1;
        else
            indice--;
        cursseur_id_array_image = Images_array.get(indice).getId_image();
        afficher_imageObject(cursseur_id_array_image);
    }*/

    /*
    *
    *
    *
    *  POCKET SPHINX
    *
    *
    * */

    private String grammar_sphinx ;
    private String dictionnaire_sphinx;
    private String ptm_sphinx ;
    private String WORD_SEARCH ;
    private Locale loc;

    public void choix_language(String DB_NAME){
        switch (DB_NAME){
            case "english.db" :
                grammar_sphinx = "en-en-first.gram";
                dictionnaire_sphinx = "cmudict-en-us.dict";
                ptm_sphinx = "en-us-ptm";
                WORD_SEARCH = "world";
                loc = Locale.ENGLISH;
                break;

            case "french.db" :
                grammar_sphinx = "mot.gram";
                dictionnaire_sphinx = "cmudict-fr-fr.dict";
                ptm_sphinx = "fr-fr-ptm";
                WORD_SEARCH = "mot";
                loc = Locale.FRANCE;
                break;

            case "german.db" :
                grammar_sphinx = "gr-gr-first.gram";
                dictionnaire_sphinx = "cmudict-gr-gr.dict";
                ptm_sphinx = "gr-gr-ptm";
                WORD_SEARCH = "mot-gr";
                loc = Locale.GERMANY;
                break;

        }
    }

    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private SpeechRecognizer recognizer;




    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {
        recognizer.stop();
        speak_btn.setEnabled(true);
        speak_btn.setBackgroundResource(R.drawable.mic_round);
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        int score ;
        try {
            score = hypothesis.getBestScore();
        }catch(NullPointerException e){
            score = 0 ;
            Log.i("score", "nullpointer");
        }
        float final_score = 0;
        String mot_a_prononce = Images_array.get(indice).getNom_image();
        if (hypothesis != null) {
            if(if_word_correct(mot_a_prononce, hypothesis)) {

                Toasty.success(this,"Ta prononciation est correcte",Toast.LENGTH_SHORT).show();
                final_score = getScoreStars(score);
            }else {
                Toasty.error(this,"Ta prononciation est incorrecte",Toast.LENGTH_SHORT).show();
                final_score = 0;
            }
            databaseManager.changer_score_image(cursseur_id_array_image, (int)(final_score));
            score_text_view.setText( String.valueOf((int)(final_score))+"/10");

            /************** Pour changer le score du niveau et de la categorie dans la base de donnée ************/
            //Niveau
            int somme_score_images = databaseManager.somme_score_images_dans_niveau(id_categorie_from_bundle, id_niveau_from_bundle);
            databaseManager.changer_score_niveau(id_niveau_from_bundle, somme_score_images);

            //Categorie
            int somme_score_niveau = databaseManager.somme_score_niveaux_dans_categorie(id_categorie_from_bundle);
            databaseManager.changer_score_categorie(id_categorie_from_bundle, somme_score_niveau);

            //Somme des categories dans l'activité image_activity
            score_tout_categorie.setText( String.valueOf(databaseManager.somme_score_categorie()));
            /*****************************************************************************************************/

            next_btn.setVisibility(View.VISIBLE);
            progress.setProgress((indice + 1) * 100 / Images_array.size());

            if(final_score != 0){
                wrog_wav.start();
            }else{
                valid_wav.start();
            }
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

    public void closeBtn(View view) {
        Exit_image_game_alert();
    }

    public void SpeakIt_Click(View view) {
        textToSpeechAlertDialog();
    }


    private class SetupTask extends AsyncTask<Void, Void, Exception> {
        WeakReference<ImageGame> activityReference;
        SetupTask(ImageGame activity) {
            this.activityReference = new WeakReference<>(activity);
            ////////////////////
            next_btn.setVisibility(View.GONE);
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
                speak_btn.setEnabled(true);
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


    public float getScoreStars(int getBestScore){
        if (getBestScore <= 0 && getBestScore > -500)
            return 10f;
        else if (getBestScore <= -500 && getBestScore > -1000)
            return 9f;
        else if (getBestScore <= -1000 && getBestScore > -1500)
            return 8;
        else if (getBestScore <= -1500 && getBestScore > -2500)
            return 7f;
        else if (getBestScore <= -2500 && getBestScore > -3500)
            return 6;
        else if (getBestScore <= -3500 && getBestScore > -5000)
            return 5f;
        else if (getBestScore <= -5000 && getBestScore > -6000)
            return 4f;
        else if (getBestScore <= -6000 && getBestScore > -7000)
            return 3f;
        else if (getBestScore <= -7000 && getBestScore > -8000)
            return 2f;
        else if (getBestScore <= -8000 && getBestScore > -9000)
            return 1f;
        else
            return 0f;
    }


    public Boolean if_word_correct(String mot_a_prononce, Hypothesis hypothesis){
        if (hypothesis.getHypstr().equals(mot_a_prononce))
            return true;
        else
            return false;
    }


    //Custum Alert Dialog
    public void congratulationAlertDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.congratultion);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        next = (Button)dialog.findViewById(R.id.next_btn);
        share =(Button)dialog.findViewById(R.id.share_btn) ;

        jeton_from_pref += JETON_TO_ADD;

        sharedPreferences
                .edit()
                .putInt(PREF_JETON, jeton_from_pref)
                .apply();



        next.setEnabled(true);
        share.setEnabled(true);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                dialog.cancel();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ImageGame.this, ShareScore.class));

            }
        });
        dialog.show();
    }
    public void Exit_image_game_alert(){
        exitdialog = new Dialog(this);
        exitdialog.setContentView(R.layout.quitter_image_game_dialog);

        exitdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        yes = (Button) exitdialog.findViewById(R.id.yes_btn1);
        no = (Button) exitdialog.findViewById(R.id.no_btn1);


        yes.setEnabled(true);
        no.setEnabled(true);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitdialog.cancel();
                finish();

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitdialog.cancel();
            }
        });
        exitdialog.show();
    }
    public void textToSpeechAlertDialog(){
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.speak_it_dialog);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Oui = (Button)myDialog.findViewById(R.id.oui_btn);
        Non = (Button)myDialog.findViewById(R.id.non_btn);

        Oui.setEnabled(true);
        Non.setEnabled(true);

        Oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jeton_from_pref > 0 ){
                    String mot_a_prononce = Images_array.get(indice).getNom_image();
                    if (mot_a_prononce == null || mot_a_prononce.length() == 0) {
                        Toast.makeText(ImageGame.this, "some Error occured", Toast.LENGTH_SHORT).show();
                    } else
                        textToSpeech.speak(mot_a_prononce, TextToSpeech.QUEUE_FLUSH, null);

                    jeton_from_pref -- ;
                    jetons_user.setText(String.valueOf(jeton_from_pref));

                    sharedPreferences
                            .edit()
                            .putInt(PREF_JETON, jeton_from_pref)
                            .apply();

                }else{
                    ImageButton button = (ImageButton) findViewById(R.id.speak_btn);
                    button.setEnabled(false);
                    button.setClickable(false);
                }
                myDialog.cancel();
            }

        });

        Non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });
        myDialog.show();
    }
}



package dz.youcefmegoura.test.databasepro.Views;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import dz.youcefmegoura.test.databasepro.Database.DatabaseManager;
import dz.youcefmegoura.test.databasepro.Objects.Image;
import dz.youcefmegoura.test.databasepro.R;
import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;
import es.dmoral.toasty.Toasty;

import static dz.youcefmegoura.test.databasepro.Database.HelperMySQL.ADRESS_SERVER;

public class OnlineGame extends AppCompatActivity implements RecognitionListener {
    int mon_score = 0;

    private final static int TIME_TO_END_GAME = 25000;//15 second
    int id_game;
    EditText text ;
    String type_user;
    Handler handler;
    int score_sender, score_receiver;

    Dialog winDialog , loseDalog;
    Button winIgnorer , loseIgnorer;



    Locale loc;
    private ImageView image_view;
    private TextView score_text_view, nom_image_textView, nom_categorie, score_tout_categorie, jetons_user,
            congart_score;
    private ImageButton speak_btn;
    private ProgressBar progress;
    private Button next_btn;
    private Dialog myDialog, dialog, exitdialog;
    Button Oui, Non, next, share, yes, no;
    ImageView image_categorie ;

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
        setContentView(R.layout.activity_online_game);

        choix_language("english.db");

        handler = new Handler();

        //text = (EditText) findViewById(R.id.editText);

        Bundle bundle = getIntent().getExtras();
        id_game = bundle.getInt("id_game");
        type_user = bundle.getString("type_user");

        end_game();


        ////////////////////////pocket

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

        nom_categorie.setText("Online");

        /**************** Initialisation ******************/
        indice = 0;
        databaseManager = new DatabaseManager(this, "english.db");
        Images_array = new ArrayList<>(databaseManager.readFrom_ImageTable_where_categorie_and_niveau(7, 63));
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
                        Toast.makeText(OnlineGame.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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



    }

    @Override
    protected void onResume() {
        super.onResume();
        mon_score = 0;
    }

    public void afficher_imageObject(int cursseur_id_array_image) {
        score_text_view.setText( String.valueOf(Images_array.get(indice).getScore_image())+"/10");
        int drawableResourceId = this.getResources().getIdentifier(Images_array.get(indice).getUrl_image(), "drawable", this.getPackageName());
        image_view.setImageResource(drawableResourceId);
        nom_image_textView.setText(Images_array.get(indice).getNom_image());
    }

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
            mon_score =+ (int)(final_score);
            score_text_view.setText( String.valueOf((int)(final_score))+"/10");



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

    }


    private class SetupTask extends AsyncTask<Void, Void, Exception> {
        WeakReference<OnlineGame> activityReference;
        SetupTask(OnlineGame activity) {
            activityReference = new WeakReference<>(activity);
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

                startActivity(new Intent(OnlineGame.this, ShareScore.class));

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



    ////////////////////////////////////////////////////////////




    //onClick
    public void btnClick(View view) {

    }


    public void end_game(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setScore(type_user);
                show_result();
            }
        }, TIME_TO_END_GAME);
    }

    public void setScore(String type_user){
        String url = null;
        if (type_user.equals("sender")){
            url = ADRESS_SERVER + "set_score_sender.php?id_game=" + id_game + "&score=" + Integer.valueOf(mon_score);
        }else if (type_user.equals("receiver")){

            url = ADRESS_SERVER + "set_score_receiver.php?id_game=" + id_game + "&score=" + Integer.valueOf(mon_score);
        }
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response)
            {
                try {
                    JSONArray jsonArray =new JSONArray(response);
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
//                    String invited = jsonObject1.getString("is_invited");



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void getScore(){
        String url_logout_user = ADRESS_SERVER + "get_score.php?id_game=" + id_game;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_logout_user
                , new Response.Listener<String>() {
            @Override

            public void onResponse(String response)
            {
                try {

                    JSONArray jsonArray =new JSONArray(response);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    score_sender = jsonObject1.getInt("score_sender");
                    score_receiver = jsonObject1.getInt("score_receiver");

                    if (!type_user.equals("sender")) {
                        if (score_receiver > score_sender) {
                            //TODO : mettre online 0 f la table game
                            LoserAlertDialog();
                        } else {
                            LoserAlertDialog();
                        }
                    }else{
                        if (score_receiver > score_sender) {
                            WinnerAlertDialog();
                        } else {

                            WinnerAlertDialog();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }
        };
        queue.add(stringRequest);
    }

    ////////////////////////win dialog////////////////////////////////////////////////////////////////////
    public void WinnerAlertDialog(){
        winDialog = new Dialog(this);
        winDialog.setContentView(R.layout.winner_dialog);

        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        winIgnorer = (Button) winDialog.findViewById(R.id.win_btn);



        winIgnorer.setEnabled(true);



        winIgnorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        finish();
            }
        });
        winDialog.setCancelable(false);
        winDialog.show();
    }
    ////////////////////////lose dialog////////////////////////////////////////////////////////////////////
    public void LoserAlertDialog(){
        loseDalog = new Dialog(this);
        loseDalog.setContentView(R.layout.loser_dialog);

        loseDalog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        loseIgnorer = (Button) loseDalog.findViewById(R.id.lose_btn);
        loseDalog.setCancelable(false);


        loseIgnorer.setEnabled(true);




        loseIgnorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loseDalog.show();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void show_result(){
        getScore();
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
}

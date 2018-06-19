package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.common.Method;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Database.HelperMySQL;
import es.dmoral.toasty.Toasty;

public class Connexion extends AppCompatActivity {
    private static final String USER_PREFS = "PREFS";
    private static final String PREF_PSEUDO = "PREFS_PSEUDO";
    private static final String PREF_PASSWORD = "PREF_PASSWORD";
    SharedPreferences sharedPreferences;


    /************** XML References ***************/
    private EditText email_ET, mot_de_passe_ET;
    /*********************************************/


    /*************** Firebase Authentification ***************/
    private FirebaseAuth auth_firebase;
    /*********************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        /************** XML References ***************/
        email_ET = (EditText) findViewById(R.id.email_ET);
        mot_de_passe_ET = (EditText) findViewById(R.id.mot_de_passe_ET);
        /*********************************************/


        /*********** Firebase Authentification ************/
        auth_firebase = FirebaseAuth.getInstance();
        /**************************************************/

        sharedPreferences = getBaseContext().getSharedPreferences(USER_PREFS, MODE_PRIVATE);
        if (sharedPreferences.contains(PREF_PSEUDO) && sharedPreferences.contains(PREF_PASSWORD)) {

            String pseudo_user_db = sharedPreferences.getString(PREF_PSEUDO, null);
            String password_user_db = sharedPreferences.getString(PREF_PASSWORD, null);

            email_ET.setText(pseudo_user_db);
            mot_de_passe_ET.setText(password_user_db);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = auth_firebase.getCurrentUser();
        //updateUI(currentUser);
    }

    public void se_connecter_click(View view) {
        String email = email_ET.getText().toString().trim();
        String password = mot_de_passe_ET.getText().toString().trim();

        if (email.isEmpty()){
            email_ET.setError("Email is required");
            email_ET.requestFocus() ;
            return;
        }

        if (password.isEmpty()){
            mot_de_passe_ET.setError("Password is required");
            mot_de_passe_ET.requestFocus() ;
            return;
        }

        if (isNetworkAvailable()){
            login_user(email, password);
        }

    }

    //onClick Button
    public void se_connecter_google_click(View view) {
    }

    //onClick Button
    public void se_connecter_facebook_click(View view) {
    }

    //onClick Button
    public void back_click(View view) {
        finish();
    }

    //onClick Button
    public void mot_de_passe_oublie_click(View view) {
        startActivity(new Intent(this, MotDePasseOublie.class));
    }

    //Verifier si l'appareil est connecter a internet
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public void login_user(String pseudo, String password){
        String password_hashed = sha1(password);

        String url_create_user = HelperMySQL.ADRESS_SERVER + "login_user.php?pseudo=" + pseudo + "&password=" + password_hashed;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Method.GET, url_create_user,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");

                            if (code.equals("OK")){
                                //si aucun utilisateur n'est sauvegardé, on ajouter
                                sharedPreferences
                                        .edit()
                                        .putString(PREF_PSEUDO, email_ET.getText().toString())
                                        .putString(PREF_PASSWORD, mot_de_passe_ET.getText().toString())
                                        .apply();
                                /////////////////////////////////////////////////
                                startActivity(new Intent(Connexion.this, Salon.class));
                                Toasty.success(Connexion.this, "Connexion réussi", Toast.LENGTH_SHORT).show();

                            }else{
                                Toasty.error(Connexion.this, "Pseudo/ Login invalide !", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("Error.Response", "zfef");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error.Response", "zfef");
                    }
                }
        );

        queue.add(getRequest);


    }

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            // error action
        }
        return null;
    }

    public static String md5(String txt) {
        return getHash(txt, "MD5");
    }

    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}

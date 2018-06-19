package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.common.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Database.HelperMySQL;
import es.dmoral.toasty.Toasty;

public class Inscription extends AppCompatActivity {
    private EditText pseudo_ET,
                     email_ET ,
                     mot_de_passe_ET,
                     confirmer_mot_de_passe_ET,
                     age_ET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        /************** XML References ***************/
        pseudo_ET = (EditText) findViewById(R.id.pseudo_ET);
        email_ET = (EditText) findViewById(R.id.email_ET);
        mot_de_passe_ET = (EditText) findViewById(R.id.mot_de_passe_ET);
        confirmer_mot_de_passe_ET = (EditText) findViewById(R.id.confirmer_mot_de_passe_ET);
        age_ET = (EditText) findViewById(R.id.age_ET);
        /********************************************/

    }


    public void cree_compte_click(View view) {

        final String pseudo = pseudo_ET.getText().toString().trim();
        final String email = email_ET.getText().toString().trim();
        final String mot_de_passe = mot_de_passe_ET.getText().toString().trim();
        final String confirmer_mot_de_passe = confirmer_mot_de_passe_ET.getText().toString().trim();
        final String age = age_ET.getText().toString().trim();

        if (pseudo.isEmpty()) {
            pseudo_ET.setError("Pseudo is required");
            pseudo_ET.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            email_ET.setError("Email is required");
            email_ET.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_ET.setError("Email is not valid");
            email_ET.requestFocus();
            return;
        }

        if (mot_de_passe.isEmpty()) {
            mot_de_passe_ET.setError("Password is required");
            mot_de_passe_ET.requestFocus();
            return;
        }
        if (mot_de_passe.length() < 6) {
            mot_de_passe_ET.setError("Minimum lenght of password should be 6");
            mot_de_passe_ET.requestFocus();
            return;
        }
        if (confirmer_mot_de_passe.isEmpty()) {
            confirmer_mot_de_passe_ET.setError("Confirmer Password is required");
            confirmer_mot_de_passe_ET.requestFocus();
            return;
        }
        if (!confirmer_mot_de_passe.equals(mot_de_passe)) {
            confirmer_mot_de_passe_ET.setError("Les mots de passe ne sont pas identique !    ");
            confirmer_mot_de_passe_ET.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            age_ET.setError("Age is required");
            age_ET.requestFocus();
            return;
        }
        if (isNetworkAvailable()) {
            inscription_a_la_bd(pseudo, mot_de_passe, email, Integer.parseInt(age));
            Intent intent = new Intent(Inscription.this, Connexion.class);
            startActivity(intent);
            Toasty.success(Inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();

        }
    }


    public void se_connecter_google_click(View view) {
    }

    public void se_connecter_facebook_click(View view) {
    }

    public void back_click(View view) {
        finish();
    }

    //Verifier si l'appareil est connecter a internet
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public void inscription_a_la_bd(String pseudo, String password, String email, int age){

        String password_hashed = sha1(password);

        String url_create_user = HelperMySQL.ADRESS_SERVER + "create_user.php?pseudo=" + pseudo + "&password=" + password_hashed + "&email=" + email + "&age=" + age;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_create_user, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String if_success = jsonObject.getString("code");
                            Toast.makeText(Inscription.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();

                            if (if_success.equals("OK")){
                                Intent intent = new Intent(Inscription.this, Connexion.class);
                                startActivity(intent);
                                Toasty.success(Inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                            }else {
                                Toasty.error(Inscription.this, "Inscription echouée", Toast.LENGTH_SHORT).show();
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

        queue.add(stringRequest);
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

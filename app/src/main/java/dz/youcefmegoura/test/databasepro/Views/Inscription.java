package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import dz.youcefmegoura.test.databasepro.R;

public class Inscription extends AppCompatActivity {
    private EditText pseudo_ET,
                     email_ET ,
                     mot_de_passe_ET,
                     confirmer_mot_de_passe_ET,
                     age_ET;
    private FirebaseAuth auth_firebase;

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

        auth_firebase = FirebaseAuth.getInstance();
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
            auth_firebase.createUserWithEmailAndPassword(email, mot_de_passe).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User registered succesfull", Toast.LENGTH_SHORT).show();


                        startActivity(new Intent(Inscription.this, Connexion.class));
                        String user_id = auth_firebase.getCurrentUser().getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("user").child(user_id);

                        Map newPost = new HashMap();
                        newPost.put("username", pseudo);
                        newPost.put("age", Integer.valueOf(age));
                        newPost.put("score", 0);


                        current_user_db.setValue(newPost);
                    } else
                        Toast.makeText(getApplicationContext(), "Some error occured", Toast.LENGTH_SHORT).show();
                }
            });
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
}

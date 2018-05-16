package dz.youcefmegoura.test.databasepro.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dz.youcefmegoura.test.databasepro.R;

public class Connexion extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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

        sharedPreferences = getSharedPreferences("int", MODE_PRIVATE);

        int a = sharedPreferences.getInt("integer", 0);
        Toast.makeText(this, String.valueOf(a), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
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
            auth_firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent myintent= new Intent(Connexion.this,ListeCategories.class);
                        startActivity(myintent);
                        Log.d("Firebase", "signInWithEmail:success");
                    }else{
                        Log.w("Firebase", "signInWithEmail:failure", task.getException());
                        Toast.makeText(Connexion.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Impossible de se connecter, verifiez votre internet", Toast.LENGTH_SHORT).show();
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
}

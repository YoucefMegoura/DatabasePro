package dz.youcefmegoura.test.databasepro.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import dz.youcefmegoura.test.databasepro.R;

/**
 * Created by Youcef Mégoura and Moussaoui Mekka on 05/05/2018.
 */

public class MotDePasseOublie extends AppCompatActivity {

    /*************** Firebase Authentification ***************/
    private FirebaseAuth auth_firebase;
    /*********************************************************/

    /************** XML References ***************/
    private EditText email_ET;
    private ProgressBar progressBar;
    /*********************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mot_de_passe_oublie);

        /************** XML References ***************/
        email_ET = (EditText) findViewById(R.id.email_ET);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        /*********************************************/

        auth_firebase = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
    }

    //onCLick button
    public void back_click(View view) {
        finish();
    }

    public void renitialise_mot_de_passe(View view) {
        String email = email_ET.getText().toString().trim();

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
        progressBar.setVisibility(View.VISIBLE);
        auth_firebase.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MotDePasseOublie.this, "Nous avons envoyer l'email de rénitialisation avec succés !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MotDePasseOublie.this, "Adresse Email non enregistrer", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }
}

/*
https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
 */

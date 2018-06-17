package dz.youcefmegoura.test.databasepro.Views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import dz.youcefmegoura.test.databasepro.Objects.Niveau;
import dz.youcefmegoura.test.databasepro.R;

public class SettingsActivity extends AppCompatActivity  {

    public ImageView imageAvatar, sonOn;
    public ImageButton boyOne, girlOne;
    public Dialog dialog, mydialog , difficultdialog;
    public Button valider, annuler, changer , nochanger, validerdiff,annulerdiff;
    public EditText entrerpseudo;
    public TextView changerpseudo;
    public String msg;
    public Switch switch_sound;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView difficultTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        imageAvatar = (ImageView)findViewById(R.id.image_avatar);
        imageAvatar.setBackgroundResource(R.drawable.boyone);
        difficultTextview = findViewById(R.id.difficult_textview);
        changerpseudo= findViewById(R.id.changer_pseudo);

        changerpseudo.setText("Youcef");
        difficultTextview.setText("Débutant");

        boyOne = findViewById(R.id.boyOne_btn);
        girlOne = findViewById(R.id.girlOne_btn);

      //////////////////////////// son on /off/////////////////////
        sonOn = findViewById(R.id.sonOn);
        switch_sound = findViewById(R.id.switch_sound);

        //default to being available
        switch_sound.setChecked(true);
        sonOn.setBackgroundResource(R.drawable.sonon);
        // attach an OnClickListener
        switch_sound.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {//an AudioManager object, to change the volume settings
                AudioManager amanager;
                amanager = (AudioManager)getSystemService(AUDIO_SERVICE);

                // Is the toggle on?
                boolean on = ((Switch)v).isChecked();

                if (on) {

                    sonOn.setBackgroundResource(R.drawable.sonon);
                    Log.i("onToggleIsChecked", "ToggleClick Is Off");

                    //turn ringer silent
                    amanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Log.i(".RINGER_MODE_NORMAL", "Set to true");

                    // turn on sound, enable notifications
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                    Log.i("STREAM_SYSTEM", "Set to False");
                    //notifications
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                    Log.i("STREAM_NOTIFICATION", "Set to False");
                    //alarm
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
                    Log.i("STREAM_ALARM", "Set to False");
                    //ringer
                    amanager.setStreamMute(AudioManager.STREAM_RING, false);
                    Log.i("STREAM_RING", "Set to False");
                    //media
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    Log.i("STREAM_MUSIC", "Set to False");

                } else {
                    sonOn.setBackgroundResource(R.drawable.sonoff);
                    //turn ringer silent
                    amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Log.i("RINGER_MODE_SILENT", "Set to true");

                    //turn off sound, disable notifications
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
                    Log.i("STREAM_SYSTEM", "Set to true");
                    //notifications
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                    Log.i("STREAM_NOTIFICATION", "Set to true");
                    //alarm
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
                    Log.i("STREAM_ALARM", "Set to true");
                    //ringer
                    amanager.setStreamMute(AudioManager.STREAM_RING, true);
                    Log.i("STREAM_RING", "Set to true");
                    //media
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                    Log.i("STREAM_MUSIC", "Set to true");

                }
            }
        });

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void back_click(View view) {
        finish();
    }
////////////////changer difficulte/////////////////////////////////////////
    public void Editdifficulté(View view) {

        ChangerDifficulteDialog();

    }
    public void ChangerDifficulteDialog(){
        difficultdialog = new Dialog(this);
        difficultdialog.setContentView(R.layout.difficulte_dialog);

        difficultdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        radioGroup = findViewById(R.id.radioGroup);



        validerdiff = (Button)difficultdialog.findViewById(R.id.validerDiff);
        annulerdiff =(Button)difficultdialog.findViewById(R.id.annulerDiff) ;

        validerdiff.setEnabled(true);
        annulerdiff.setEnabled(true);

        validerdiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int radioId = radioGroup.getCheckedRadioButtonId();
//                radioButton = findViewById(radioId);
                //radioButton.getText()
                        difficultTextview.setText("Expert");




                difficultdialog.cancel();
            }
        });

        annulerdiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultdialog.cancel();

            }
        });
       difficultdialog.show();
    }


    ///////////////////changer pseudo/////////////////
    public void EditPseudo(View view) {

        ChangerPseudoDialog();
    }

    public void ChangerPseudoDialog(){
        mydialog = new Dialog(this);
        mydialog.setContentView(R.layout.editpseudodialog);

        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        changer = (Button)mydialog.findViewById(R.id.changer);
        nochanger =(Button)mydialog.findViewById(R.id.nochanger) ;
        entrerpseudo = findViewById(R.id.entrer_pseudo);




        changer.setEnabled(true);
        nochanger.setEnabled(true);


        changer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

           //  msg =entrerpseudo.getText().toString();
                changerpseudo.setText("mekka");

                mydialog.cancel();
            }
        });
        nochanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.cancel();

            }
        });
        mydialog.show();
    }

    ////////////////////////////changer avatar/////////////////////////
   public void BoyOne (View view){

    imageAvatar.setBackgroundResource(R.drawable.boyone);

}
    public void GirlOne(View view) {
        imageAvatar.setBackgroundResource(R.drawable.girlone);
    }

    public void Boytwo(View view) {
        imageAvatar.setBackgroundResource(R.drawable.boytwo);
    }

    public void girltwo(View view) {
        imageAvatar.setBackgroundResource(R.drawable.girltwo);
    }

    public void boythree(View view) {
        imageAvatar.setBackgroundResource(R.drawable.boythree);
    }

    public void girlthree(View view){
        imageAvatar.setBackgroundResource(R.drawable.girlthree);

    }
    //////////////////////////////////////////////////////////////////////////////////////
    public void EditAvatar(View view) {
        ChangerAvatarDialog();

    }

    public void ChangerAvatarDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.changeravatar);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        valider = (Button)dialog.findViewById(R.id.valider);
        annuler =(Button)dialog.findViewById(R.id.annuler) ;


        valider.setEnabled(true);
        annuler.setEnabled(true);


        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                dialog.cancel();
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();

            }
        });
        dialog.show();
    }
//////////////////////////////////////////////////////////////////////////////////////////////


}


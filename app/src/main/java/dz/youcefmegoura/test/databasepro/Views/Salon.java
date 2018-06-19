package dz.youcefmegoura.test.databasepro.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.common.Method;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dz.youcefmegoura.test.databasepro.Objects.User;
import dz.youcefmegoura.test.databasepro.R;
import dz.youcefmegoura.test.databasepro.Database.HelperMySQL;

import static dz.youcefmegoura.test.databasepro.Database.HelperMySQL.ADRESS_SERVER;

public class Salon extends AppCompatActivity {

    Dialog Invitationdialog;
    Button  accepter, decliner;

    /******************* Shared Preferences ******************/
    private static final String USER_PREFS = "PREFS";
    private static final String PREF_PSEUDO = "PREFS_PSEUDO";
    private static final String PREF_PASSWORD = "PREF_PASSWORD";
    private SharedPreferences sharedPreferences;
    /*********************************************************/
    String pseudo, password;
    String the_sender_pseud;
    private ListView listView;

    ArrayList<User> ListeUser_array;
    CustAdapt cus;
    int invitation_reçu = 0;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            IfMyInvitationAccepted();
            get_online_users();
            if (invitation_reçu == 0)
                IfImInvited();
            timerHandler.postDelayed(this, 1000);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        /******************* Shared Preferences ******************/
        sharedPreferences = getBaseContext().getSharedPreferences(USER_PREFS, MODE_PRIVATE);
        pseudo = sharedPreferences.getString(PREF_PSEUDO, null);
        password = sharedPreferences.getString(PREF_PASSWORD, null);
        /*********************************************************/

        ChekRequest();

        ListeUser_array = new ArrayList<User>();
        cus = new CustAdapt(ListeUser_array);
        listView = findViewById(R.id.listview);
    }

    public void logoutClick(View view) {
        HelperMySQL.logout(pseudo, password, this);
        finish();
    }

    public void get_online_users(){
        String urlString = ADRESS_SERVER + "get_all_connected_users.php?pseudo=" + pseudo;

        Ion.with(this)
                .load(urlString)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        ListeUser_array.clear();
                        ListeUser_array.addAll(User.getListOfUserFromJson(result));
                    }
                });
        listView.setAdapter(cus);

    }

    public void ChekRequest(){

        timerHandler.postDelayed(timerRunnable, 0);

    }

    public void dialog_show_invitation_recu(){
        invitation_reçu = 1;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Salon.this);
        builder1.setMessage("accepte the invitation ?" + the_sender_pseud);
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //totod
                        CreateGame(the_sender_pseud);
                        dialog.cancel();
                        Toast.makeText(Salon.this, "yes", Toast.LENGTH_SHORT).show();
                        invitation_reçu = 0;
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        refuser_invitation();
                        Toast.makeText(Salon.this, "no", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        invitation_reçu = 0;
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void IfImInvited(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = ADRESS_SERVER + "is_invited.php?pseudo=" + pseudo;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response)
            {
                try {
                    JSONArray jsonArray =new JSONArray(response);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String invited = jsonObject1.getString("is_invited");
                    if (invited.equals("1")){
                        the_sender_pseud= jsonObject1.getString("sender_user");
                        InvitationAlertDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Salon.this,"Errorcnx",Toast.LENGTH_LONG).show();
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

    public void refuser_invitation(){
        String url_logout_user = ADRESS_SERVER + "refuse_invitation.php?pseudo=" + pseudo;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest getRequest = new JsonObjectRequest(Method.GET, url_logout_user, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", "zfef");
                    }

                }
        );

        queue.add(getRequest);
    }

    public void IfMyInvitationAccepted(){
        String url_logout_user = ADRESS_SERVER + "if_my_invitation_is_accepted.php?sender=" + pseudo;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_logout_user, new Response.Listener<String>() {
            @Override

            public void onResponse(String response)
            {
                try {
                    JSONArray jsonArray =new JSONArray(response);

                    if(response != null){
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        int id_game = jsonObject.getInt("id_game");

                        Intent intent = new Intent(Salon.this, OnlineGame.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id_game", id_game);
                        bundle.putString("type_user", "sender");

                        intent.putExtras(bundle);
                        timerHandler.removeCallbacksAndMessages(null);
                        startActivity(intent);

                    }
                    //if(jsonArray.length() > 0){

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Salon.this,"Errorcnx",Toast.LENGTH_LONG).show();
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

    public void CreateGame(String sender){
        String url_logout_user = ADRESS_SERVER + "create_game.php?pseudo=" + pseudo + "&sender=" + sender;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_logout_user
                , new Response.Listener<String>() {
            @Override

            public void onResponse(String response)
            {
                try {

                    JSONArray jsonArray =new JSONArray(response);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    int id_game = jsonObject1.getInt("id_game");

                    Intent intent = new Intent(Salon.this, OnlineGame.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_game", id_game);
                    bundle.putString("type_user", "receiver");
                    Toast.makeText(Salon.this, String.valueOf(id_game), Toast.LENGTH_SHORT).show();

                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Salon.this,"Errorcnx",Toast.LENGTH_LONG).show();
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


    //Adapter
    class CustAdapt extends BaseAdapter {

        ArrayList<User> items = new ArrayList<>();

        CustAdapt(ArrayList items){
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return items.indexOf(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater myInflater = getLayoutInflater();
            View myView = myInflater.inflate(R.layout.template_user_salon, null);

            TextView user_TV = myView.findViewById(R.id.user_TV);
            user_TV.setText(items.get(position).getPseudo());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HelperMySQL.send_invitation(pseudo, items.get(position).getPseudo(), Salon.this);
                }
            });
            return myView;
        }
    }

    ////////////////////////////////invitation ///////////////////////////////////////

    public void InvitationAlertDialog(){
        invitation_reçu = 1;
        Invitationdialog = new Dialog(this);
        Invitationdialog.setContentView(R.layout.invitation_dialog);
        TextView txt_id_pseudo = (TextView) findViewById(R.id.txt_id_pseudo);

//        txt_id_pseudo.setText(txt_id_pseudo.getText().toString() + " de : " + the_sender_pseud);

        Invitationdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        decliner = (Button) Invitationdialog.findViewById(R.id.decliner_btn);
        accepter = (Button) Invitationdialog.findViewById(R.id.accepter_btn);

        accepter.setEnabled(true);
        decliner.setEnabled(true);

        accepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateGame(the_sender_pseud);
                Invitationdialog.cancel();
                Toast.makeText(Salon.this, "yes", Toast.LENGTH_SHORT).show();
                invitation_reçu = 0;

            }
        });
        decliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refuser_invitation();
                Toast.makeText(Salon.this, "no", Toast.LENGTH_SHORT).show();
                invitation_reçu = 0;
                Invitationdialog.cancel();
            }
        });
        Invitationdialog.show();
    }
}

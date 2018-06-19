package dz.youcefmegoura.test.databasepro.Database;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.common.Method;

import org.json.JSONObject;

/**
 * Created by Youcef Mégoura on 01/06/2018.
 */

public class HelperMySQL {

    public static final String ADRESS_SERVER = "http://192.168.1.6/android/";

    public static void logout(String pseudo, String password, Context context) {

        String url_logout_user = ADRESS_SERVER + "logout_user.php?pseudo=" + pseudo + "&password=" + password;

        RequestQueue queue = Volley.newRequestQueue(context);

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


    public static void send_invitation(String pseudo_sender, String pseudo_receiver, Context context) {

        String url_logout_user = ADRESS_SERVER + "send_invitation.php?sender=" + pseudo_sender + "&receiver=" + pseudo_receiver;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest getRequest = new JsonObjectRequest(Method.GET, url_logout_user, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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

    public static void get_my_tuple(String pseudo_sender, String pseudo_receiver, Context context) {

        String url_logout_user = ADRESS_SERVER + "send_invitation.php?sender=" + pseudo_sender + "&receiver=" + pseudo_receiver;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest getRequest = new JsonObjectRequest(Method.GET, url_logout_user, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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

}

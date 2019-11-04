package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.Softpig.Model.Race;
import com.Softpig.R;
import com.Softpig.View.fragment.DashBoard.DashBoardFragment;
import com.Softpig.View.fragment.ProfileFragment;
import com.Softpig.View.fragment.Race.RaceFragment;
import com.Softpig.View.fragment.ToolEmployeeFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ProfileFragment()).commit();
    }



    public boolean inflarToolsEmployeeFragment() {
        /**
         * final ProgressDialog progressDialog = new ProgressDialog(context);
         *         progressDialog.setMessage("Loading...");
         *         progressDialog.show();
         *         String url = "http://5ff0bac3.ngrok.io/api/race_list";
         *         JsonObjectRequest json = new JsonObjectRequest(
         *                 Request.Method.GET,
         *                 url,
         *                 null,
         *                 new Response.Listener<JSONObject>() {
         *                     @Override
         *                     public void onResponse(JSONObject response) {
         *
         *                         try {
         *                             ArrayList<Race> listRaces = new ArrayList<>();
         *                             JSONArray jsonRaces = response.getJSONArray("races");
         *
         *                             for(int i = 0; i < jsonRaces.length(); i++) {
         *                                 JSONObject raceObject = jsonRaces.getJSONObject(i);
         *                                 short idRace = (short) raceObject.getInt("id");
         *                                 String race = raceObject.getString("name");
         *                                 String description = raceObject.getString("description");
         *                                 listRaces.add(new Race(idRace, race, description));
         *                             }
         *                             raceFragment = new RaceFragment(listRaces);
         *                             context.inflarFragment("Razas", raceFragment);
         *                             progressDialog.dismiss();
         *
         *                         } catch (Exception e) { e.printStackTrace(); }
         *                     }
         *                 }, new Response.ErrorListener() {
         *             @Override
         *             public void onErrorResponse(VolleyError error) {
         *
         *                 try {
         *                     context.inflarFragment("Error", null);
         *                     progressDialog.dismiss();
         *
         *                 } catch (Exception e) { e.printStackTrace(); }
         *             }
         *         });
         *
         *         RequestQueue queue = Volley.newRequestQueue(context);
         *         queue.add(json);
         */

        return true;

    }
}

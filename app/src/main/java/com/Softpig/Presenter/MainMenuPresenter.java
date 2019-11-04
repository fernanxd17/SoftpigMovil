package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.Softpig.Model.Race;
import com.Softpig.R;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.Race.RaceFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainMenuPresenter {

    private RaceFragment raceFragment;

    public MainMenuPresenter(){

    }

    public boolean inflarRacesFragment(final MainMenuActivity context) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String url = "http://5ff0bac3.ngrok.io/api/race_list";
        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<Race> listRaces = new ArrayList<>();
                            JSONArray jsonRaces = response.getJSONArray("races");

                                for(int i = 0; i < jsonRaces.length(); i++) {
                                    JSONObject raceObject = jsonRaces.getJSONObject(i);
                                    short idRace = (short) raceObject.getInt("id");
                                    String race = raceObject.getString("name");
                                    String description = raceObject.getString("description");
                                    listRaces.add(new Race(idRace, race, description));
                                }
                                raceFragment = new RaceFragment(listRaces);
                                context.inflarFragment("Razas", raceFragment);
                                progressDialog.dismiss();

                        } catch (Exception e) { e.printStackTrace(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    context.inflarFragment("Error", null);
                    progressDialog.dismiss();

                } catch (Exception e) { e.printStackTrace(); }
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);
        return true;

    }
}

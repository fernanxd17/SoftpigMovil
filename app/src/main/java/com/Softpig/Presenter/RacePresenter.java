package com.Softpig.Presenter;

import android.content.Context;

import com.Softpig.Model.Race;
import com.Softpig.View.fragment.Race.RaceFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RacePresenter {

    /*public static ArrayList<Race> getRaces(){

        ArrayList<Race> listRace = new ArrayList<>();
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        listRace.add(new Race((short) 01, "Duroc", "Desciba aqui todo lo que desee de esta raaza"));
        return listRace;
    }*/

    public ArrayList<Race> getRaces(Context context){
        final ArrayList<Race> listRace = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://c2cc3ab4.ngrok.io/api/race_list";
        JsonObjectRequest json = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray races = response.getJSONArray("data");
                            for (int i=0; i<races.length();i++){
                                JSONObject raceObject = races.getJSONObject(i);
                                short idRace = (short) raceObject.getInt("id");
                                String race = raceObject.getString("name");
                                String description = raceObject.getString("description");

                                listRace.add(new Race(idRace,race,description));
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }

        );
        queue.add(json);
        return listRace;
    }
}

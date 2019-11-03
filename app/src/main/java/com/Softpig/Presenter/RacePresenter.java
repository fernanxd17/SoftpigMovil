package com.Softpig.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.Softpig.Model.Race;
import com.Softpig.Presenter.Adapters.RaceAdapter;
import com.Softpig.R;
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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RacePresenter {

    private RaceFragment raceFragment;
    private RaceAdapter raceAdapter;


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

    public RacePresenter(RaceFragment raceFragment){
        this.raceFragment = raceFragment;
    }

    public boolean getRaces(final Context context, final ArrayList<Race> listRaces) {

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
                            JSONArray races = response.getJSONArray("races");
                            if(races.length() > 0) {
                                for (int i = 0; i < races.length(); i++) {
                                    JSONObject raceObject = races.getJSONObject(i);
                                    short idRace = (short) raceObject.getInt("id");
                                    String race = raceObject.getString("name");
                                    String description = raceObject.getString("description");
                                    listRaces.add(new Race(idRace, race, description));
                                }
                                raceFragment.setConsultado(false);
                            }else{
                                //pone un text diciendo que no se encontraron datos
                            }
                            raceAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try {
                    viewRace =  inflater.inflate(R.layout.fragment_error, container, false);
                    progressDialog.dismiss();
                } catch (Exception e) {
                    //Handle a malformed json response
                }
            }
        }

        );
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(json);

        return true;

    }
        }



package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.Softpig.R;
import com.Softpig.View.fragment.ProfileFragment;
import com.Softpig.View.fragment.Tool.ToolFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ProfileFragment()).commit();
    }



    public boolean inflarToolsEmployeeFragment() {
        boolean toolEmployee = true;
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ToolFragment(toolEmployee)).commit();
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

    //código para quitar las barras que de navegación de android en algunas versiones
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void agregarArticle(String nameArticle) {

        System.out.println("Agregando articulo");
    }
}

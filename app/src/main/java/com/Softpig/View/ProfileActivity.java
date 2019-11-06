package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.Softpig.R;
import com.Softpig.View.fragment.ProfileFragment;
import com.Softpig.View.fragment.Tool.ToolFragment;

public class ProfileActivity extends AppCompatActivity {

    private MenuInflater inflater;
    private MenuItem searchItem;
    private SearchView searchView;
    private ToolFragment toolFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_profile);
        toolFragment = new ToolFragment(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ProfileFragment()).commit();
    }



    public boolean inflarToolsEmployeeFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, toolFragment).addToBackStack(null).commit();
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
/*
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
*/
    public void agregarArticle(String nameArticle) {

        System.out.println("Agregando articulo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);


        searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        return true;
    }

    public void modificar(final String fragmentSearch) {
        searchView.setQueryHint(getText(R.string.searchTool));
        searchItem.setVisible(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (fragmentSearch){
                    case "tool": toolFragment.toolAdapter.getFilter().filter(newText); break;

                }

                return  false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

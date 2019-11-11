package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.Softpig.Model.Employee;
import com.Softpig.Presenter.ProfilePresenter;
import com.Softpig.R;
import com.Softpig.View.fragment.ErrorFragment;
import com.Softpig.View.fragment.ProfileFragment;
import com.Softpig.View.fragment.ToolFragment;

public class ProfileActivity extends AppCompatActivity {

    private ProfilePresenter profilePresenter;
    private ErrorFragment errorFragment;
    private MenuInflater inflater;
    private MenuItem searchItem;
    private SearchView searchView;
    private ToolFragment toolFragment;
    private Bundle bundle;
    private Employee employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            profilePresenter = new ProfilePresenter();
        }
        bundle = this.getIntent().getExtras();
        employee = (Employee) bundle.getSerializable("Empleado");
        errorFragment = new ErrorFragment();
        setContentView(R.layout.container_profile);
        toolFragment = new ToolFragment(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ProfileFragment(employee)).commit();
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

    public void presentarFragment(String toolsPerson) {
        switch (toolsPerson){
            case "ToolsPerson":
                profilePresenter.presentarToolsPerson(this, toolFragment, employee.getIdEmployee());
                break;
        }
    }

    public void inflarFragment(String toolsPerson){
        switch (toolsPerson){
            case "ToolPerson":
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, toolFragment).commit();
                break;
             default:
                 getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, errorFragment).commit();
                    break;
        }
    }

    public void cambiarEstado(String nuevoEstado) {
        profilePresenter.cambiarEstado(this, employee.getIdEmployee(), nuevoEstado);

    }
}

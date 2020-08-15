package com.softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import com.softpig.model.Employee;
import com.softpig.model.Tool;
import com.softpig.presenter.ProfilePresenter;
import com.softpig.R;
import com.softpig.View.Fragment.ErrorFragment;
import com.softpig.View.Fragment.ProfileFragment;
import com.softpig.View.Fragment.ToolFragment;

public class ProfileActivity extends AppCompatActivity {

    private ProfilePresenter profilePresenter;
    private ErrorFragment errorFragment;
    private MenuInflater inflater;
    private MenuItem searchItem;
    private SearchView searchView;
    private ToolFragment toolFragment;
    private Bundle bundle;
    private Employee employee;
    private ProfileFragment profileFragment;
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
        profileFragment = new ProfileFragment(employee);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, profileFragment).addToBackStack(null).commit();
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
                profilePresenter.presentarToolsPerson(this, toolFragment, employee.getIdEmployee(), null, true);
                break;
        }
    }

    public void inflarFragment(Fragment fragment){
       getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, fragment).addToBackStack(null).commit();
    }

    public void cambiarEstado(final Employee employee,String nuevoEstado) {
        profilePresenter.cambiarEstado(this, employee, nuevoEstado);
        this.employee = employee;
    }

    public void inflarFragmentError() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ErrorFragment()).addToBackStack(null).commit();
    }

    public void eliminarArticuloPersona(final short idArticle, final String table) {
        profilePresenter.eliminarArticuloPersona(toolFragment, idArticle, table);
    }

    public Employee getEmployee(){
        return employee;
    }

    public void agregarTool(final ToolFragment toolFragment, final Tool tool) {
        profilePresenter.addToolEmployee(toolFragment,employee.getIdEmployee(), tool);
    }

    public void addWorkedHours(String workedHours) {
        profilePresenter.addHoursWorked(this, employee.getIdEmployee(), workedHours);
    }

    public void cambiarEstadoEmpleado(String nuevoEstado){
        profileFragment.setTvState(nuevoEstado);
    }

    public void actualizarListaTool(final SwipeRefreshLayout refreshListTool) {
        profilePresenter.presentarToolsPerson(this, toolFragment, employee.getIdEmployee(),
                refreshListTool, false);
    }
}
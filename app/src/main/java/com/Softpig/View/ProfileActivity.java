package com.Softpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

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
    public void agregarArticle(String nameArticle, String copias, int typeArticle) {

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

    public void inflarFragment(Fragment fragment){
       getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, fragment).commit();
    }

    public void cambiarEstado(String nuevoEstado) {
        profilePresenter.cambiarEstado(this, employee.getIdEmployee(), nuevoEstado);

    }

    public void inflarFragmentError() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentsProfile, new ErrorFragment()).commit();
    }

    public void eliminarArticuloPersona(short position, String articlePerson) {
        profilePresenter.eliminarArticuloPersona(this, position, articlePerson);
    }

    public Employee getEmployee(){
        return employee;
    }

    public void openDialogAddHoursPerson() {
       /* Employee empleado = ((ProfileActivity)getActivity()).getEmployee();
        String nombreEmpleado = empleado.getFirstName() + " " + empleado.getLastName();
        AddToolDialog addToolEmployeeDialog = new AddToolDialog(nombreEmpleado);
        addToolEmployeeDialog.show(getFragmentManager(), "Caulcular salario");*/
    }

    public void agregarTool(short idTool, String copias) {
        profilePresenter.addToolEmployee(this, employee.getIdEmployee(), idTool, copias);
    }

    public void addWorkedHours(String workedHours) {
        profilePresenter.addHoursWorked(this, employee.getIdEmployee(), workedHours);
    }
}

package com.Softpig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Softpig.Presenter.MasterPresenter;
import com.Softpig.View.MainMenuActivity;
import com.Softpig.View.fragment.AboutFragment;
import com.Softpig.View.fragment.DictionaryFragment;
import com.Softpig.View.fragment.ForgetPassFragment;
import com.Softpig.View.fragment.LoginFragment;

public class IndexActivity extends AppCompatActivity {


    private MasterPresenter masterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_login);

        if(savedInstanceState == null){
            masterPresenter = new MasterPresenter();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new LoginFragment()).commit();
        }




    }


    /**
     * Comunica al MasterPresenter la peticion de fragment_login
     * @param codeUser
     * @param password
     */
    public void login(String codeUser, String password){
        //JSONObject datos = IndexActivity.this.masterPresenter.fragment_login(codeUser, password);
        MasterPresenter.login(codeUser, password);
        Intent i = new Intent();
        i.setClass(this, MainMenuActivity.class);
        startActivity(i);
    }


    public boolean openForgetFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new ForgetPassFragment()).commit();
        return true;
    }

    public boolean openDictionary(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new DictionaryFragment()).commit();
        return true;
    }

    public boolean openAbout(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragments_login, new AboutFragment()).commit();
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
}
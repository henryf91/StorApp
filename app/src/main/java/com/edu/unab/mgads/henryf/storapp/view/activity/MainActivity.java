package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.viewmodel.UserViewModel;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LIFE_CYCLE = "lifeCicle";
    private ActivityMainBinding mainBinding;
    private UserViewModel userViewModel;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        userViewModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);

        mainBinding.setTitle(getString(R.string.txt_login));
        mainBinding.setUserViewModel(userViewModel);

        pref = getSharedPreferences(getString(R.string.preferences_name), MODE_PRIVATE);

        if(pref.getBoolean("authenticated", false)){
            Intent i = new Intent(MainActivity.this, ProductListActivity.class);
            finish(); /// no vuelve atras
            startActivity(i) ;
        }

        mainBinding.btSigninLogin.setOnClickListener(view -> {
            userViewModel.login();
            userViewModel.getUser().observe(MainActivity.this, user -> {
                if(user != null){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("authenticated", true);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, ProductListActivity.class);
                    finish(); /// no vuelve atras
                    startActivity(i) ;
                }else{
                    Toast.makeText(MainActivity.this, "Datos errados", Toast.LENGTH_SHORT).show();
                }
            });
        });

        mainBinding.btSignupLogin.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, UserFormActivity.class);
            startActivity(i) ;
        });

        //Log.d(LIFE_CYCLE, "Método OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LIFE_CYCLE, "Método OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Método onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LIFE_CYCLE, "Método OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LIFE_CYCLE, "Método OnRestart");
    }
}
package com.edu.unab.mgads.henryf.storapp;

import static com.edu.unab.mgads.henryf.storapp.BR.userViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.edu.unab.mgads.henryf.storapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LIFE_CYCLE = "lifeCycle";
    private ActivityMainBinding mainBinding;
    public UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        userViewModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);

        mainBinding.setTitle(getString(R.string.txt_login));
        mainBinding.setUserViewModel(userViewModel);

        mainBinding.btSigninLogin.setOnClickListener(view -> {
                if(userViewModel.login()){
                   Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ProductListActivity.class);
                    startActivity(i) ;
                }else{
                    Toast.makeText(MainActivity.this, "Datos errados", Toast.LENGTH_SHORT).show();
                }
            });
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
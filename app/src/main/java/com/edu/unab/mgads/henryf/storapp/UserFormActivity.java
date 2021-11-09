package com.edu.unab.mgads.henryf.storapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.edu.unab.mgads.henryf.storapp.databinding.ActivityUserFormBinding;

public class UserFormActivity extends AppCompatActivity {

    private ActivityUserFormBinding userFormBinding;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        userFormBinding = DataBindingUtil.setContentView(UserFormActivity.this, R.layout.activity_user_form);
        userViewModel = new ViewModelProvider(UserFormActivity.this).get(UserViewModel.class);

        userFormBinding.btCreateUserForm.setOnClickListener(view -> {
            userViewModel.createUser();
            Toast.makeText(UserFormActivity.this, "Usuario Creado...", Toast.LENGTH_SHORT).show();
        });
    }
}
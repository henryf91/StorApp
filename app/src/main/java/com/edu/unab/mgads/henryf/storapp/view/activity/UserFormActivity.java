package com.edu.unab.mgads.henryf.storapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityUserFormBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.viewmodel.UserViewModel;

public class UserFormActivity extends AppCompatActivity {

    private ActivityUserFormBinding userFormBinding;
    private UserViewModel userViewModel;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_form);
        userFormBinding = DataBindingUtil.setContentView(UserFormActivity.this, R.layout.activity_user_form);
        userViewModel = new ViewModelProvider(UserFormActivity.this).get(UserViewModel.class);

        userFormBinding.setUserViewModel(userViewModel);

        //myUser = (User) getIntent().getSerializableExtra("user");
        if(myUser == null) {
            myUser = new User();
            //userFormBinding.setUser(myUser);

            userFormBinding.btCreateUserForm.setOnClickListener(view -> {
                EditText editText=findViewById(R.id.et_password_user_form);
                String password = editText.getText().toString();
                userViewModel.createUser(password);
                Toast.makeText(UserFormActivity.this, "Usuario Creado...", Toast.LENGTH_SHORT).show();
                finish();
            });
        }


    }
}
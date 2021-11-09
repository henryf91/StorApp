package com.edu.unab.mgads.henryf.storapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.edu.unab.mgads.henryf.storapp.databinding.ActivityUserListBinding;

public class UserListActivity extends AppCompatActivity {

    private ActivityUserListBinding userListBinding;
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userListBinding = DataBindingUtil.setContentView(UserListActivity.this, R.layout.activity_user_list);
        userViewModel = new ViewModelProvider(UserListActivity.this).get(UserViewModel.class);

        userAdapter = new UserAdapter(UserListActivity.this, userViewModel.getUsersList());

        userListBinding.setUserAdapter(userAdapter);
    }
}
package com.edu.unab.mgads.henryf.storapp.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityUserListBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.view.adapter.UserAdapter;
import com.edu.unab.mgads.henryf.storapp.viewmodel.UserViewModel;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private ActivityUserListBinding userListBinding;
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userListBinding = DataBindingUtil.setContentView(UserListActivity.this, R.layout.activity_user_list);
        userViewModel = new ViewModelProvider(UserListActivity.this).get(UserViewModel.class);

        userAdapter = new UserAdapter(UserListActivity.this, new ArrayList<>());

        userViewModel.getUsersList().observe(UserListActivity.this, users -> {
            //Log.d("FirestoreData", "LISTA PEX: "+users.toString());
            if(users.size() == 0){
                //userViewModel.setFakeData();
            }
            userAdapter.setList((ArrayList<User>) users);
        });

        userListBinding.setUserAdapter(userAdapter);
        
    }
}
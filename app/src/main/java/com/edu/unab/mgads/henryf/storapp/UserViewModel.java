package com.edu.unab.mgads.henryf.storapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class UserViewModel extends AndroidViewModel {

    private String email = "juan@correo.com";
    private String password = "123456";
    private String etEmail;
    private String etPassword;

    public UserViewModel(@NonNull Application application){
        super(application);
    }

    public String getEtEmail() {
        return etEmail;
    }

    public void setEtEmail(String etEmail) {
        this.etEmail = etEmail;
    }

    public String getEtPassword() {
        return etPassword;
    }

    public void setEtPassword(String etPassword) {
        this.etPassword = etPassword;
    }

    public boolean login(){
        return this.email.equals(this.etEmail) && this.password.equals(this.etPassword);
    }

}

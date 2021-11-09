package com.edu.unab.mgads.henryf.storapp;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class UserViewModel extends AndroidViewModel {

    private String email = "juan@correo.com";
    private String password = "123456";
    private String etEmail;
    private String etPassword;
    private ArrayList<User> usersList;

    public ArrayList<User> getUsersList() {
        setFakeData();
        return usersList;
    }

    private void setFakeData(){
        usersList = new ArrayList<>();

        usersList.add(new User("Andres Perez", "1025635", "elmanytal@mail.com", "https://empresas.infoempleo.com/hrtrends/media/2018/09/voz-del-empleado.jpg"));
        usersList.add(new User("Chamo Gomez", "190123", "que@pex.com", "https://www.megadatosgratis.com/wp-content/uploads/2014/04/excelentes-ejemplos-de-carta-para-felicitar-a-un-empleado-por-su-labor.jpg"));
        usersList.add(new User("Adrianakas Serna", "185056", "ingeniero@sistemas.com", "https://thinkandsell.com/wp-content/uploads/2012/11/importancia-empleados-experiencia-de-cliente.jpg"));
        usersList.add(new User("Rulo Rules", "153495", "devs@test.com", "https://le-cdn.website-editor.net/efa07738149d4fff9f91752a625d9f12/dms3rep/multi/opt/profilepicture-rulorules-circuloSINFONDO-1920w.png"));
    }

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

    public void createUser(){

    }

}

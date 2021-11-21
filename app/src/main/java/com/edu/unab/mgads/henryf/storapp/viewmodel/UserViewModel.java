package com.edu.unab.mgads.henryf.storapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.model.repository.UserRepository;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {

    private String etEmail;
    private String etPassword;
    private UserRepository repository;
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

    public UserViewModel(){
        repository = new UserRepository();
    }

    public void login(){
        repository.login(this.etEmail, this.etPassword);
    }

    public void signUp(User myUser, String pass){
        repository.createUser(myUser, pass);
    }

    public void createUser(){

    }

    public LiveData<User> getUser(){
        return repository.getMyUser();
    }
}

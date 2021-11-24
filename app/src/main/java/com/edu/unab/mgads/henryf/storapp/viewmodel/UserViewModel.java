package com.edu.unab.mgads.henryf.storapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private String etEmail;
    private String etPassword;
    private String suEmail;
    private String suName;
    private String suDocument;
    private String suUrlPicture;
    private String suPassword;
    private UserRepository repository;
    private ArrayList<User> usersList;

    /*public ArrayList<User> getUsersList() {
        setFakeData();
        return usersList;
    }*/

    public LiveData<List<User>> getUsersList() {
        return repository.getAllUsersFirestore();
    }

    public String getSuEmail() {
        return suEmail;
    }

    public void setSuEmail(String suEmail) {
        this.suEmail = suEmail;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuDocument() {
        return suDocument;
    }

    public void setSuDocument(String suDocument) {
        this.suDocument = suDocument;
    }

    public String getSuUrlPicture() {
        return suUrlPicture;
    }

    public void setSuUrlPicture(String suUrlPicture) {
        this.suUrlPicture = suUrlPicture;
    }

    public String getSuPassword() {
        return suPassword;
    }

    public void setSuPassword(String suPassword) {
        this.suPassword = suPassword;
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

    public void createUser(String password){
        repository.signUp(this.suName, this.suEmail, this.suDocument, this.suUrlPicture, password);
    }

    public LiveData<User> getUser(){
        return repository.getMyUser();
    }
}

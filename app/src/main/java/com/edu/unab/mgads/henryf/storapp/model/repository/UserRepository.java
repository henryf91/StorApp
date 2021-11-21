package com.edu.unab.mgads.henryf.storapp.model.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.model.network.StorAppAPI;
import com.edu.unab.mgads.henryf.storapp.model.network.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private static final String USERS_COLLECTION = "users";
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private MutableLiveData<User> myUser;
    private UserService userService;

    public UserRepository(){
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        myUser = new MutableLiveData<>();
        Retrofit api = StorAppAPI.getInstance();
        userService = api.create(UserService.class);
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener( task -> {
            if(task.isSuccessful()){
                //getByIdFirestore(auth.getUid());
                getByIdAPI(auth.getUid());
            }
        });
    }

    public void createUser(User myNewUser, String pass) {
        auth.createUserWithEmailAndPassword(myNewUser.getEmail(), pass).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                //createUserFirestore(myNewUser);
                createUserAPI(myNewUser);
            }
        });
    }

    private void createUserFirestore(User myNewUser) {
        firestore.collection(USERS_COLLECTION).document(auth.getUid()).set(myNewUser).addOnCompleteListener(task1 -> {
            if(task1.isSuccessful()){
                myUser.setValue(myNewUser);
            }
        });
    }

    public void getByIdFirestore(String id){
        firestore.collection(USERS_COLLECTION).document(id).get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               if(task.getResult() != null) {
                   User myUserObj = task.getResult().toObject(User.class);
                   myUser.setValue(myUserObj);
               }
           }
        });
    }

    public void getByIdAPI(String id){
        userService.getById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("API", response.body().toString());
                myUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }

    public void setMyUser(MutableLiveData<User> myUser) {
        this.myUser = myUser;
    }

    public void createUserAPI(User myNewUser){
        userService.create(auth.getUid(), myNewUser).enqueue((new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("API", response.body().toString());
                myUser.setValue(myNewUser);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        }));
    }

    public LiveData<User> getMyUser() {
        return myUser;
    }
}

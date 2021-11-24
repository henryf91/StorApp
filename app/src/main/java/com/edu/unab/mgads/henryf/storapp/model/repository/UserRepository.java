package com.edu.unab.mgads.henryf.storapp.model.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String USERS_COLLECTION = "users";
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private MutableLiveData<User> myUser;
    private MutableLiveData<List<User>> userList;


    public UserRepository(){
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        myUser = new MutableLiveData<>();
        userList = new MutableLiveData<>();
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener( task -> {
            if(task.isSuccessful()){
                getByIdFirestore(auth.getUid());
            }else {
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Log.d("myTag", "This is my message" + email + " "+password);
            }
        });
    }

    public void signUp(String name, String email, String document, String urlPicture, String suPassword){

        String password = suPassword;

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User newUser = new User(name, document, email, urlPicture);
                createUserFirestoreFirebase(newUser);

            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Log.d("myTag", "This is my message" + email + " "+password);
            }
        }

        );

    }

    private void createUserFirestoreFirebase(User newUser) {
        firestore.collection(USERS_COLLECTION).document(auth.getUid()).set(newUser).addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                myUser.setValue(newUser);
            }
        });
    }

    public MutableLiveData<List<User>> getAllUsersFirestore() {
        firestore.collection(USERS_COLLECTION).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                List<User> users = new ArrayList<>();

                if(!task.getResult().isEmpty()){
                    for (DocumentSnapshot document: task.getResult().getDocuments()) {

                        User myUser = document.toObject(User.class);
                        //myUser.setId(document.getId());
                        Log.d("FirestoreData", myUser.toString());
                        users.add(myUser);
                    }
                }
                userList.setValue(users);
            }

        });
        return userList;
    }

    public void getByIdFirestore(String id){
        firestore.collection(USERS_COLLECTION).document(id).get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               if(task.getResult() != null) {
                   User myUserObj = task.getResult().toObject(User.class);
                   Gson gson = new Gson();
                   String json = gson.toJson(myUserObj);

                   myUser.setValue(myUserObj);
               }
           }else {
               Log.w(TAG, "createUserWithEmail:failure", task.getException());

           }
        });
    }

    public void setMyUser(MutableLiveData<User> myUser) {
        this.myUser = myUser;
    }

    public LiveData<User> getMyUser() {
        return myUser;
    }
}

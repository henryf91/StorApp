package com.edu.unab.mgads.henryf.storapp.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepository {
    private static final String USERS_COLLECTION = "users";
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private MutableLiveData<User> myUser;

    public UserRepository(){
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        myUser = new MutableLiveData<>();
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener( task -> {
            if(task.isSuccessful()){
                getByIdFirestore(auth.getUid());
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

    public void setMyUser(MutableLiveData<User> myUser) {
        this.myUser = myUser;
    }

    public LiveData<User> getMyUser() {
        return myUser;
    }
}

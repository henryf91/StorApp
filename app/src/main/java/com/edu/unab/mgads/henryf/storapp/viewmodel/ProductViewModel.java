package com.edu.unab.mgads.henryf.storapp.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.model.repository.ProductRepository;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.model.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ArrayList<Product> productList;
    private ProductRepository repository;
    private UserRepository userRepository;
    private FirebaseAuth auth;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
       // loadProducts();
        auth = FirebaseAuth.getInstance();
        userRepository = new UserRepository();
        //userRepository.getByIdFirestore(auth.getUid());
        userRepository.getByIdAPI(auth.getUid());
    }

    public void loadProducts() {
        //repository.getAllLocal();
        //repository.listenAllFirestore();
        repository.getAllAPI();
    }

    public LiveData<List<Product>> getProductList() {
 //       repository.getAllLocal();
   //     if(repository.getProductList().size()==0){
     //       setFakeData();
       // }

        return repository.getProductList();
    }

    public void setFakeData(){
        repository.addLocal(new Product("Mouse", 60000, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/6bf0ed99c663954fafc930039201ed07/l/2/l20m01.jpg"));
        repository.addLocal(new Product("Mouse", 60000, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/6bf0ed99c663954fafc930039201ed07/l/2/l20m01.jpg"));
        repository.addLocal(new Product("Mouse", 60000, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/6bf0ed99c663954fafc930039201ed07/l/2/l20m01.jpg"));
    }

    public void removeProduct(Product myProduct){

        //repository.removeFirestore(myProduct);
        repository.removeAPI(myProduct.getId());
    }

    public void addProduct(Product myProduct, Uri photoUri){

        //repository.addLocal(myProduct);
        myProduct.setOwnerId(auth.getUid());
        //repository.addFirestore(myProduct);
        repository.addAPI(myProduct, photoUri);
    }

    public void editProduct(Product myProduct) {
        //repository.editLocal(myProduct);
        //repository.editFirestore(myProduct);
        repository.updateAPI(myProduct);
    }

    public LiveData<Product> getProduct(){
        return repository.getMyProduct();
    }

    public void getByKey(int key){
        repository.getByKeyLocal(key);
    }

    public void getById(String id) {
        //repository.listenByIdFirestore(id);
        repository.getByIdAPI(id);
    }

    public LiveData<User> getUser(){
        return userRepository.getMyUser();
    }

    public LiveData<Boolean> getState(){
        return repository.getState();
    }

}

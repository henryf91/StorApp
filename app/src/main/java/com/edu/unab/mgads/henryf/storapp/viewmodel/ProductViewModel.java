package com.edu.unab.mgads.henryf.storapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.edu.unab.mgads.henryf.storapp.model.repository.ProductRepository;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ArrayList<Product> productList;
    private ProductRepository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        //repository.getAllLocal();
        repository.listenAllFirestore();
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

        repository.removeFirestore(myProduct);
    }

    public void addProduct(Product myProduct){

        //repository.addLocal(myProduct);
        repository.addFirestore(myProduct);
    }

    public void editProduct(Product myProduct) {
        //repository.editLocal(myProduct);
        repository.editFirestore(myProduct);
    }

    public LiveData<Product> getProduct(){
        return repository.getMyProduct();
    }

    public void getByKey(int key){
        repository.getByKeyLocal(key);
    }

    public void getById(String id) {
        repository.listenByIdFirestore(id);
    }
}

package com.edu.unab.mgads.henryf.storapp.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.model.local.AppDB;
import com.edu.unab.mgads.henryf.storapp.model.local.ProductDAO;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String PRODUCTS_COLLECTION = "products";
    private Context myContext;
    private ProductDAO productDAO;
    private MutableLiveData<Product> myProduct;
    private MutableLiveData<List<Product>> productList;
    private FirebaseFirestore firestore;


    public ProductRepository(Context myContext) {
        this.myContext = myContext;
        AppDB bd = AppDB.getInstance(myContext);
        productDAO = bd.productDAO();
        myProduct = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        productList = new MutableLiveData<>();
//        productList = new ArrayList<>();

    }

    public void getAllLocal(){
        productList = (MutableLiveData<List<Product>>) productDAO.getAll();
    }

    public void getByKeyLocal(int key){
        myProduct = (MutableLiveData<Product>) productDAO.getByKey(key);
    }

    public void addLocal(Product myProduct){
        productDAO.add(myProduct);
    }

    public void removeLocal(Product myProduct){
        productDAO.remove(myProduct);
    }

    public void editLocal(Product myProduct){
        productDAO.edit(myProduct);
    }

    public LiveData<Product> getMyProduct() {
        return myProduct;
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public void getAllFirestore() {
        firestore.collection(PRODUCTS_COLLECTION).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                List<Product> products = new ArrayList<>();

                if(!task.getResult().isEmpty()){
                    for (DocumentSnapshot document: task.getResult().getDocuments()) {

                        Product myProduct = document.toObject(Product.class);
                        myProduct.setId(document.getId());
                        Log.d("FirestoreData", myProduct.toString());
                        products.add(myProduct);
                    }
                }
                productList.setValue(products);
            }

        });
    }

    public void  listenAllFirestore(){
        firestore.collection(PRODUCTS_COLLECTION).addSnapshotListener( (value, error) -> {
            List<Product> products = new ArrayList<>();

            if(!value.isEmpty()){
                for (DocumentSnapshot document: value.getDocuments()) {

                    Product myProductObj = document.toObject(Product.class);
                    myProductObj.setId(document.getId());
                    Log.d("FirestoreData", myProductObj.toString());
                    products.add(myProductObj);
                }
            }
            productList.setValue(products);
        });
    }

    public void addFirestore(Product myProduct){
        firestore.collection(PRODUCTS_COLLECTION).add(myProduct);
    }



    public void listenByIdFirestore(String id){
        firestore.collection(PRODUCTS_COLLECTION).document(id).addSnapshotListener((value, error) -> {
            if(value != null) {
                Product myProductObj = value.toObject(Product.class);
                if(myProductObj != null){
                    myProductObj.setId(id);
                    myProduct.setValue(myProductObj);
                }
            }
        });
    }

    public void removeFirestore(Product myProduct){
        firestore.collection((PRODUCTS_COLLECTION)).document(myProduct.getId()).delete();
    }

    public void editFirestore(Product myProduct){
        firestore.collection(PRODUCTS_COLLECTION).document(myProduct.getId()).set(myProduct);
    }

}

































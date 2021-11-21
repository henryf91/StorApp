package com.edu.unab.mgads.henryf.storapp.model.repository;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.model.entity.User;
import com.edu.unab.mgads.henryf.storapp.model.local.AppDB;
import com.edu.unab.mgads.henryf.storapp.model.local.ProductDAO;
import com.edu.unab.mgads.henryf.storapp.model.network.ProductService;
import com.edu.unab.mgads.henryf.storapp.model.network.StorAppAPI;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private static final String PRODUCTS_COLLECTION = "products";
    private static final String USERS_COLLECTION = "users";
    private Context myContext;
    private ProductDAO productDAO;
    private MutableLiveData<Product> myProduct;
    private MutableLiveData<List<Product>> productList;
    private FirebaseFirestore firestore;
    private ProductService productService;
    private FirebaseStorage storage;
    private MutableLiveData<Boolean> state;

    public ProductRepository(Context myContext) {
        this.myContext = myContext;
        AppDB bd = AppDB.getInstance(myContext);
        productDAO = bd.productDAO();
        myProduct = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        productList = new MutableLiveData<>();
        state = new MutableLiveData<>();
//        productList = new ArrayList<>();
        Retrofit api = StorAppAPI.getInstance();
        productService = api.create(ProductService.class);
        storage =FirebaseStorage.getInstance();
        state.setValue(false);

    }

    public MutableLiveData<Boolean> getState() {
        return state;
    }

    public void setState(MutableLiveData<Boolean> state) {
        this.state = state;
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
                    if (myProductObj.getOwnerId() != null) {
                        firestore.collection(USERS_COLLECTION).document(myProductObj.getOwnerId()).get().addOnCompleteListener(task -> {
                            if(task.isSuccessful()){
                                User myUser = task.getResult().toObject(User.class);
                                myProductObj.setOwner(myUser);
                                products.add(myProductObj);
                            }
                            productList.setValue(products);
                        });
                    }

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
    public void getAllAPI(){

        productService.getAll().enqueue(new Callback<Map<String, Product>>() {
            @Override
            public void onResponse(Call<Map<String, Product>> call, Response<Map<String, Product>> response) {
                List<Product> products = new ArrayList<>();

                if(response.body() != null){
                    Log.d("API_PROD", response.body().toString());
                    Map<String, Product> data = response.body();
                    for(Map.Entry<String, Product> entry:data.entrySet()){
                        Product myProductObject = entry.getValue();
                        myProductObject.setId(entry.getKey());
                        products.add(myProductObject);
                    }
                }
                productList.setValue(products);

            }

            @Override
            public void onFailure(Call<Map<String, Product>> call, Throwable t) {
                Log.e("API_PROD", t.getMessage());

            }
        });
    }

    public void addAPI(Product myProductObject, Uri photoUri){
        if(photoUri != null){
            String imageName = myProductObject.getName()+".jpg";
            StorageReference file = storage.getReference("images/"+imageName);

            file.putFile(photoUri).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(task1 -> {
                        if(task1.isSuccessful()){
                            String url = task1.getResult().toString();
                            myProductObject.setUrlImage((url));

                            productService.create(myProductObject).enqueue(new Callback<Object>() {
                                @Override
                                public void onResponse(Call<Object> call, Response<Object> response) {
                                    Log.d("API_PROD", response.body().toString());
                                    getAllAPI();
                                    state.setValue(true);
                                }

                                @Override
                                public void onFailure(Call<Object> call, Throwable t) {
                                    Log.e("API_PROD", t.getMessage());
                                    state.setValue(false);
                                }
                            });
                        }
                    });
                }
            });
        }

    }

    public void getByIdAPI(String id){
        productService.getById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.body() != null){
                    Product myProductObject = response.body();
                    myProductObject.setId(id);
                    myProduct.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    public void removeAPI (String id){
        productService.delete(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getAllAPI();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void updateAPI (Product myProductObject){
        productService.update(myProductObject.getId(), myProductObject).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                getAllAPI();
                myProduct.setValue(myProductObject);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

}

































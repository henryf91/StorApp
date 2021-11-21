package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductDetailBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.viewmodel.ProductViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;

public class ProductDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityProductDetailBinding binding;
    private ProductViewModel viewModel;
    private Product myProduct;
    private FirebaseAuth auth;
    private String productId;
    private MapView mvMProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        binding = DataBindingUtil.setContentView(ProductDetailActivity.this, R.layout.activity_product_detail);
        viewModel = new ViewModelProvider(ProductDetailActivity.this).get(ProductViewModel.class);

        mvMProduct = findViewById(R.id.mv_product_detail);

        mvMProduct.getMapAsync(this);
        mvMProduct.onCreate(savedInstanceState);

        int productKey = getIntent().getIntExtra("product_key", 0);

        productId = getIntent().getStringExtra("product_id");

  //      viewModel.getByKey(productKey);

        viewModel.getUser().observe(this, user -> {
            setTitle("StorApp: "+user.getName());
        });

        viewModel.getProduct().observe(ProductDetailActivity.this, product -> {
            myProduct = product;
            binding.setProduct(myProduct);
            binding.setUser(auth.getUid());

        });

        binding.btRemoveProductDetail.setOnClickListener(view -> {
            viewModel.removeProduct(myProduct);
            finish();
        });

        binding.btEditProductDetail.setOnClickListener(view -> {
            Intent i = new Intent(ProductDetailActivity.this, ProductFormActivity.class);
            i.putExtra("product", myProduct);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        if(productId!=null)
            viewModel.getById(productId);
        super.onResume();
        mvMProduct.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mvMProduct.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvMProduct.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvMProduct.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvMProduct.onStart();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mvMProduct.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mvMProduct.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng unab = new LatLng(4.699320830325194, -74.0988917417561);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(unab, 10f));
        googleMap.setMinZoomPreference(10.0f);
        googleMap.setMaxZoomPreference(10.0f);

    }
}
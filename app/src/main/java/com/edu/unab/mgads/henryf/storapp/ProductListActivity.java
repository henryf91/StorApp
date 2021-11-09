package com.edu.unab.mgads.henryf.storapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductListBinding;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ActivityProductListBinding productListBinding;
    private ProductViewModel productViewModel;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productListBinding = DataBindingUtil.setContentView(ProductListActivity.this, R.layout.activity_product_list);
        productViewModel = new ViewModelProvider(ProductListActivity.this).get(ProductViewModel.class);

        adapter = new ProductAdapter(ProductListActivity.this, productViewModel.getProductList());

        productListBinding.setAdapter(adapter);

    }
}
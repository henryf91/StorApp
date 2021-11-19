package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductDetailBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.edu.unab.mgads.henryf.storapp.viewmodel.ProductViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;
    private ProductViewModel viewModel;
    private Product myProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(ProductDetailActivity.this, R.layout.activity_product_detail);
        viewModel = new ViewModelProvider(ProductDetailActivity.this).get(ProductViewModel.class);

        int productKey = getIntent().getIntExtra("product_key", 0);

        String productId = getIntent().getStringExtra("product_id");

  //      viewModel.getByKey(productKey);
        viewModel.getById(productId);

        viewModel.getProduct().observe(ProductDetailActivity.this, product -> {
            myProduct = product;
            binding.setProduct(myProduct);

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
}
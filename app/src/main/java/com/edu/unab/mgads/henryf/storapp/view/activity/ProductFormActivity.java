package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.edu.unab.mgads.henryf.storapp.viewmodel.ProductViewModel;
import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductFormBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

public class ProductFormActivity extends AppCompatActivity {

    private ActivityProductFormBinding binding;
    private ProductViewModel productViewModel;
    private Product myProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_form);

        binding = DataBindingUtil.setContentView(ProductFormActivity.this, R.layout.activity_product_form);
        productViewModel = new ViewModelProvider(ProductFormActivity.this).get(ProductViewModel.class);

        myProduct = (Product) getIntent().getSerializableExtra("product");

        if(myProduct == null){
            myProduct = new Product();
            binding.setProduct(myProduct);

            binding.btProductForm.setOnClickListener(view -> {
                productViewModel.addProduct(binding.getProduct());
                finish();
            });
        }else{
            binding.setProduct(myProduct);

            binding.tvTitleProductForm.setText(R.string.txt_btn_edit_product);

            binding.btProductForm.setText(R.string.txt_btn_edit_product);

            binding.btProductForm.setOnClickListener(view -> {
                productViewModel.editProduct(binding.getProduct());
                finish();
            });
        }



    }
}
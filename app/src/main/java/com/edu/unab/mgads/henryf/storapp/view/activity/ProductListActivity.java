package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.edu.unab.mgads.henryf.storapp.view.adapter.ProductAdapter;
import com.edu.unab.mgads.henryf.storapp.viewmodel.ProductViewModel;
import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductListBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ActivityProductListBinding productListBinding;
    private ProductViewModel productViewModel;
    private ProductAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==  R.id.mi_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_list);

        productListBinding = DataBindingUtil.setContentView(ProductListActivity.this, R.layout.activity_product_list);
        productViewModel = new ViewModelProvider(ProductListActivity.this).get(ProductViewModel.class);

        productViewModel.getUser().observe(this, user -> {
            if(user != null){
                setTitle("StorApp: "+user.getName());
            }
        });

        adapter = new ProductAdapter(ProductListActivity.this, new ArrayList<>());

        productViewModel.getProductList().observe(ProductListActivity.this, products -> {
            Log.d("FirestoreData", "LISTA PEX: "+products.toString());
            /*if(products.size() == 0){
                productViewModel.setFakeData();
            }*/
            adapter.setList((ArrayList<Product>) products);
        });

        productListBinding.setAdapter(adapter);

        adapter.setOnItemClickListener(myProduct -> {
          //  Toast.makeText(ProductListActivity.this, myProduct.getName()+" eliminado...",Toast.LENGTH_SHORT).show();
          //  productViewModel.removeProduct(myProduct);

            Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
            i.putExtra("product_key", myProduct.getKey());
            i.putExtra("product_id", myProduct.getId());
            startActivity(i);
        });

        productListBinding.btAddProductList.setOnClickListener(view -> {
            Intent i = new Intent(ProductListActivity.this, ProductFormActivity.class);
            startActivity(i);
        });

        productListBinding.btLogoutList.setOnClickListener(view -> {
            logout();

        });
    }

    private void logout() {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preferences_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        Intent i = new Intent(ProductListActivity.this, MainActivity.class);
        finish();
        startActivity(i);
    }

    @Override
    protected void onResume() {
        productViewModel.loadProducts();
        super.onResume();

    }
}
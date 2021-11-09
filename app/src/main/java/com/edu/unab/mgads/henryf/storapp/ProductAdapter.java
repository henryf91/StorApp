package com.edu.unab.mgads.henryf.storapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.unab.mgads.henryf.storapp.databinding.ItemProductBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    private Context myContext;
    private ArrayList<Product> list;

    public ProductAdapter(Context myContext, ArrayList<Product> list) {
        this.myContext = myContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(myContext), R.layout.item_product, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product myProduct = this.list.get(position);
        ProductViewHolder myHolder = (ProductViewHolder) holder;
        myHolder.bind(myProduct);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding =  binding;
        }

        public void bind(Product myProduct){
            this.binding.setProduct(myProduct);

            // Picasso.get().load(myProduct.getUrlImage()).into(binding.ivProductItem);
        }
    }
}

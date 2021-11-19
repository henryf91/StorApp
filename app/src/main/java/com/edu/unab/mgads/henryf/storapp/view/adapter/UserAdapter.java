package com.edu.unab.mgads.henryf.storapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ItemUserBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter {

    private Context myContext;
    private ArrayList<User> arrayList;

    public UserAdapter(Context myContext, ArrayList<User> arrayList) {
        this.myContext = myContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(myContext), R.layout.item_user, parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User myUser = this.arrayList.get(position);
        UserViewHolder myHolder = (UserViewHolder) holder;
        myHolder.bind(myUser);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private ItemUserBinding binding;

        public UserViewHolder(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding =  binding;
        }

        public void bind(User myUser){
            this.binding.setUser(myUser);

        }
    }
}

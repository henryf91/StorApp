package com.edu.unab.mgads.henryf.storapp.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("select * from products")
    LiveData<List<Product>> getAll();

    @Query("select * from products where `key`=:productKey")
    LiveData<Product> getByKey(int productKey);

    @Insert
    void add(Product myProduct);

    @Delete
    void remove(Product myProduct);

    @Update
    void edit(Product myProduct);
}

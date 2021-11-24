package com.edu.unab.mgads.henryf.storapp.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;
    public abstract ProductDAO productDAO();

    public static AppDB getInstance(Context myContext) {
        if (instance == null){
            instance = Room.databaseBuilder(myContext, AppDB.class, "storapp.db").allowMainThreadQueries().build();
        }

        return instance;
    }
}

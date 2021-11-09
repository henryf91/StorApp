package com.edu.unab.mgads.henryf.storapp;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseMethod;

import com.squareup.picasso.Picasso;

public class BindAdapter {

    @BindingAdapter("image")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }

    public static double stringToDouble(String value){
        try {
            return Double.parseDouble(value);
        }catch (Exception e) {
            return 0;
        }
    }

    @InverseMethod("stringToDouble")
    public static String doubleToString(double value){
        return String.valueOf(value);
    }
}

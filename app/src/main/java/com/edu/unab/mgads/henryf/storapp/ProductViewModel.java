package com.edu.unab.mgads.henryf.storapp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private ArrayList<Product> productList;

    public ArrayList<Product> getProductList() {
        setFakeData();
        return productList;
    }

    private void setFakeData(){
        productList = new ArrayList<>();

        productList.add(new Product("Mouse", 60000, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/6bf0ed99c663954fafc930039201ed07/l/2/l20m01.jpg"));
        productList.add(new Product("Disco Duro", 190000, "https://m.media-amazon.com/images/I/71yVo4Ed4CL._AC_SL1000_.jpg"));
        productList.add(new Product("Headset", 85000, "https://co.jbl.com/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw5999dc3c/JBL_Quantum_400_Product%20Image_Hero%2002.png?sw=537&sfrm=png"));
    }

}

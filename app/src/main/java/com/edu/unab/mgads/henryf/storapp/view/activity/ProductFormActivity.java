package com.edu.unab.mgads.henryf.storapp.view.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.edu.unab.mgads.henryf.storapp.viewmodel.ProductViewModel;
import com.edu.unab.mgads.henryf.storapp.R;
import com.edu.unab.mgads.henryf.storapp.databinding.ActivityProductFormBinding;
import com.edu.unab.mgads.henryf.storapp.model.entity.Product;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductFormActivity extends AppCompatActivity {

    private ActivityProductFormBinding binding;
    private ProductViewModel productViewModel;
    private Product myProduct;
    private ActivityResultLauncher<Intent> resultCamera;
    private ActivityResultLauncher<Intent> resultGallery;
    private Uri photoUri;


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
                productViewModel.addProduct(binding.getProduct(), photoUri);
                productViewModel.getState().observe(this, aBoolean -> {
                    if(aBoolean){
                        finish();
                    }
                });
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

        binding.ibCameraForm.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File

                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    photoUri = FileProvider.getUriForFile(this,
                            "com.edu.unab.mgads.henryf.storapp.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    resultCamera.launch(takePictureIntent);
                }

            }

        });

        binding.ibGalleryForm.setOnClickListener(view -> {
            Intent galleryPictureInternet = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if(galleryPictureInternet.resolveActivity((getPackageManager())) != null){
                resultGallery.launch(galleryPictureInternet);
            }
        });

        resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getResultCode() == RESULT_OK){
  //             Bundle extras = result.getData().getExtras();

    //           Bitmap imageBitmap = (Bitmap) extras.get("data");
      //         binding.ivProductForm.setImageBitmap(imageBitmap);
               Picasso.get().load(photoUri).into(binding.ivProductForm);

           }
        });

        resultGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK){
                photoUri = result.getData().getData();
                Picasso.get().load(photoUri).into(binding.ivProductForm);
            }
        });


        }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    timeStamp,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = image.getAbsolutePath();
            return image;

    }
}
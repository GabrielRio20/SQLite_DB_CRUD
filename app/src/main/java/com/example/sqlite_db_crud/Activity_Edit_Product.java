package com.example.sqlite_db_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

public class Activity_Edit_Product extends AppCompatActivity {

    ImageView btnBack;
    Button btnUpdate;
    Button btnRemove;
    EditText et_name;
    EditText et_price;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        btnBack = findViewById(R.id.backButton);
        btnUpdate = findViewById(R.id.btn_update);
        btnRemove = findViewById(R.id.btn_remove);
        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String selectedProduct = intent.getStringExtra("nama");

        List<Product> products = databaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(selectedProduct)){
                et_name.setText(product1.getName());
                et_price.setText(String.valueOf(product1.getPrice()));
                break;
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProduct(selectedProduct);
                Toast.makeText(Activity_Edit_Product.this, selectedProduct + " Edited", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Activity_Edit_Product.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Activity_Edit_Product.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(selectedProduct);
                Toast.makeText(Activity_Edit_Product.this, selectedProduct + " Removed", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Activity_Edit_Product.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void deleteProduct(String productName){
        List<Product> products = databaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                databaseHelper.deleteProduct(product1);
                break;
            }
        }
    }

    private void updateProduct(String productName){
        List<Product> products = databaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                product1.setName(String.valueOf(et_name.getText()));
                product1.setPrice(Integer.valueOf(String.valueOf(et_price.getText())));
                databaseHelper.updateProduct(product1);
                break;
            }
        }
    }
}
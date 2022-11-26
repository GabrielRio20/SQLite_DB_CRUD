package com.example.sqlite_db_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Activity_Add_Product extends AppCompatActivity {

    EditText etName;
    EditText etPrice;
    Button btnAdd;
    ImageView btnBack;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.backButton);

        databaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                etName.setText("");
                etPrice.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Add_Product.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insertData(){
        Product product = new Product();
        product.setName(etName.getText().toString());
        product.setPrice(Integer.valueOf(etPrice.getText().toString()));

        databaseHelper.insertRecord(product);
        Toast.makeText(Activity_Add_Product.this, "Add Product Success", Toast.LENGTH_SHORT).show();
    }
}
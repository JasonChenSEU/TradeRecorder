package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jason.traderecorder.R;

public class AddProductActivity extends AppCompatActivity {

    LinearLayout layoutComponent;
    Button btnAddProduct, btnAddMaterial,btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        layoutComponent = (LinearLayout) findViewById(R.id.layoutComponent);

        btnAddProduct = (Button) findViewById(R.id.btnAddProd);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(view.getContext());
                textView.setText("Product: Test2 num:2 curSale:12");
                layoutComponent.addView(textView);
                Intent i = new Intent(AddProductActivity.this, AddProductActivity.class);
                startActivityForResult(i,0);

            }
        });

        btnAddMaterial = (Button) findViewById(R.id.btnAddMat);
        btnAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(view.getContext());
                textView.setText("Material: Test3 num:3 curSale:3");
                layoutComponent.addView(textView);
                Intent i = new Intent(AddProductActivity.this, AddMaterialActivity.class);
                startActivityForResult(i,1);
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnProductSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:calculate and back
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            TextView textView = new TextView(this);
            textView.setText(data.getStringExtra("RtProd"));
            layoutComponent.addView(textView);
        }else if(requestCode == 1 && resultCode == 11){
            TextView textView = new TextView(this);
            textView.setText(data.getStringExtra("RtMat"));
            layoutComponent.addView(textView);
        }
    }
}

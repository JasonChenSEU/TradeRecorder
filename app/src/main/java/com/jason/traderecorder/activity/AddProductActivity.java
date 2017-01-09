package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

public class AddProductActivity extends AppCompatActivity {

    LinearLayout layoutComponent;
    Button btnAddProduct, btnAddMaterial,btnSubmit;

    RecyclerView listComponents;
    ProductItemAdapter adapter = new ProductItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        layoutComponent = (LinearLayout) findViewById(R.id.layoutComponent);

        listComponents = (RecyclerView) findViewById(R.id.list_component);
        listComponents.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        Product p = new Product("Test1");
        p.addMaterial(new Material("Test2",10.0),6);
        adapter.add(p);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.v("MainActivity","" +position);
            }
        });

        listComponents.setAdapter(adapter);

        btnAddProduct = (Button) findViewById(R.id.btnAddProd);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TextView textView = new TextView(view.getContext());
//                textView.setText("Product: Test2 num:2 curSale:12");
//                layoutComponent.addView(textView);
                Intent i = new Intent(AddProductActivity.this, AddProductActivity.class);
                startActivityForResult(i,0);

            }
        });

        btnAddMaterial = (Button) findViewById(R.id.btnAddMat);
        btnAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TextView textView = new TextView(view.getContext());
//                textView.setText("Material: Test3 num:3 curSale:3");
//                layoutComponent.addView(textView);
                Intent i = new Intent(AddProductActivity.this, AddMaterialActivity.class);
//                startActivityForResult(i,1);
                Product p = new Product("aaa");
                p.addMaterial(new Material("aaa",20.0),3);
                adapter.add(p);
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
//            TextView textView = new TextView(this);
//            textView.setText(data.getStringExtra("RtMatName"));

            String strMatName = data.getStringExtra("RtMatName");
            double dbMatCurSale = data.getDoubleExtra("RtMatCurSale",0.0);
            int iMatNums = data.getIntExtra("RtMatNum",0);

            Material material = new Material(strMatName,dbMatCurSale);
            Product p = new Product(strMatName);
            p.addMaterial(material,iMatNums);
            adapter.add(p);

//            layoutComponent.addView(textView);
        }
    }
}

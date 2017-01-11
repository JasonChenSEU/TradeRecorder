package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.GlobalData;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Button btnAddMaterial;
    ProductItemAdapter adapter = new ProductItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        Product p = new Product("Test1",12.0);
        p.addMaterial(new Material("Test2",10.0),6);
        adapter.add(p.getItemForItem());
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.v("MainActivity","" +adapter.getItem(position));
            }
        });
        mRecyclerView.setAdapter(adapter);
        btnAddMaterial = (Button) findViewById(R.id.btnAddMaterial);
        btnAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddProductActivity.class);
                startActivityForResult(i,3);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3 && resultCode == 12) {
            String strProdName = data.getStringExtra("RtProdName");
            int num = data.getIntExtra("RtProdNum",0);
            Product p = GlobalData.productMap.get(strProdName);
            adapter.add(p.getItemForItem());

        }
    }

}

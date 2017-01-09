package com.jason.traderecorder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        ProductItemAdapter adapter = new ProductItemAdapter();
        Product p = new Product("Test1");
        p.addMaterial(new Material("Test2",10.0),6);
        adapter.add(p);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.v("MainActivity","" +position);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

}

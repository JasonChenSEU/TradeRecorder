package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.GlobalData;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;
import com.jason.traderecorder.model.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jason.traderecorder.R.id.alertTitle;
import static com.jason.traderecorder.R.id.inputProductNums;

public class AddProductActivity extends AppCompatActivity {

    Button btnAddProduct, btnAddMaterial,btnSubmit;
    EditText etProName,etProNums,etProCurSale;

    RecyclerView listComponents;
    ProductItemAdapter adapter = new ProductItemAdapter();

    Map<String, Integer> mapComposites = new HashMap<>();
    List<Product> lsCompProducts = new ArrayList<>();
    List<Material> lsCompMaterials = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        listComponents = (RecyclerView) findViewById(R.id.list_component);
        listComponents.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        Product p = new Product("Test1",12.0);
        p.addMaterial(new Material("Test2",10.0),6);
        adapter.add(p.getItemForItem());
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.v("MainActivity","" +position);
            }
        });

        listComponents.setAdapter(adapter);

        etProName = (EditText) findViewById(R.id.inputProductName);
        etProNums = (EditText) findViewById(R.id.inputProductNums);
        etProCurSale = (EditText) findViewById(R.id.inputProductCurSale);

        btnAddProduct = (Button) findViewById(R.id.btnAddProd);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddProductActivity.this, AddProductActivity.class);
                startActivityForResult(i,0);

            }
        });

        btnAddMaterial = (Button) findViewById(R.id.btnAddMat);
        btnAddMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddProductActivity.this, AddMaterialActivity.class);
                startActivityForResult(i,1);
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnProductSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:calculate and back
                String strProName = etProName.getText().toString();
                String strProdNum = etProNums.getText().toString();
                if(GlobalData.productMap.get(strProName) == null) {
                    Product thisProduct = new Product(etProName.getText().toString(),
                            Double.valueOf(etProCurSale.getText().toString()));
                    thisProduct.lsCompMaterials = lsCompMaterials;
                    thisProduct.lsCompProducts = lsCompProducts;
                    thisProduct.mapComposites = mapComposites;
                    thisProduct.Calculate();
                }
                Intent i = new Intent();
                i.putExtra("RtProdName",strProName);
                i.putExtra("RtProdNum",strProdNum);
                setResult(12,i);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == 12) {
//            TextView textView = new TextView(this);
//            textView.setText(data.getStringExtra("RtProd"));
//            layoutComponent.addView(textView);
            String strProdName = data.getStringExtra("RtProdName");
            int num = data.getIntExtra("RtProdNum",0);
            Product p = GlobalData.productMap.get(strProdName);
            lsCompProducts.add(p);
            mapComposites.put(p.getStrName(),num);
            adapter.add(p.getItemForItem());

        }else if(requestCode == 1 && resultCode == 11){
            String strMatName = data.getStringExtra("RtMatName");
            Material m = GlobalData.materialMap.get(strMatName);
            int num = data.getIntExtra("RtMatNum",0);
            lsCompMaterials.add(m);
            mapComposites.put(m.getStrName(),num);
            adapter.add(m.getItemForItem());

        }
    }
}

package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jason.traderecorder.R;
import com.jason.traderecorder.model.CostRecord;
import com.jason.traderecorder.model.GlobalData;
import com.jason.traderecorder.model.Material;
import com.jason.traderecorder.model.Product;

public class AddMaterialActivity extends AppCompatActivity {

    EditText etMatName,etMatNums,etMatCurSale;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        etMatName = (EditText) findViewById(R.id.inputMaterialName);
        etMatNums = (EditText) findViewById(R.id.inputMaterialNums);
        etMatCurSale = (EditText) findViewById(R.id.inputMaterialCurSale);

        btnSubmit = (Button) findViewById(R.id.btnMaterialSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                //TODO:Check invalidation
                String strMatName = etMatName.getText().toString();
                double dbMatCurSale = Double.valueOf(etMatCurSale.getText().toString());
                int iMatNums = Integer.valueOf(etMatNums.getText().toString());

                if(GlobalData.materialMap.get(strMatName) == null) {
                    Material material = new Material(strMatName, dbMatCurSale);
//                    Product p = new Product(strMatName, dbMatCurSale);
//                    p.addMaterial(material, iMatNums);
                    i.putExtra("RtMat", material);
                }else{
                    boolean isUpdate = true;
                    Material material = GlobalData.materialMap.get(strMatName);
                    if(isUpdate){
                        if(Math.abs(dbMatCurSale - material.getCurSale().getPrice() ) < 0.000001){
                            material.updateRecord(new CostRecord(dbMatCurSale));
                        }
                    }
                    i.putExtra("RtMat", material);
                }
                i.putExtra("RtMatNum", iMatNums);
                setResult(11, i);
                finish();
            }
        });

    }
}

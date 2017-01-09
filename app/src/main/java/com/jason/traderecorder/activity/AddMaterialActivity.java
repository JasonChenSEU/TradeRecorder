package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jason.traderecorder.R;

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
                i.putExtra("RtMatName", etMatName.getText().toString());
                i.putExtra("RtMatNum", Integer.valueOf(etMatNums.getText().toString()));
                i.putExtra("RtMatCurSale", Double.valueOf(etMatCurSale.getText().toString()));
                setResult(11, i);
                finish();
            }
        });

    }
}

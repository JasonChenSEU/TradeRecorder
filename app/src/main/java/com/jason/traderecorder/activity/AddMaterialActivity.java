package com.jason.traderecorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jason.traderecorder.R;

public class AddMaterialActivity extends AppCompatActivity {

    EditText etMatName,etMatNums;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        etMatName = (EditText) findViewById(R.id.inputMaterialName);
        etMatNums = (EditText) findViewById(R.id.inputMaterialNums);

        btnSubmit = (Button) findViewById(R.id.btnMaterialSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("RtMat", etMatName.getText() + " " + etMatNums.getText());
                setResult(11, i);
                finish();
            }
        });

    }
}

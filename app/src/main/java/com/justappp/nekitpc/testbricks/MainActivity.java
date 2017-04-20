package com.justappp.nekitpc.testbricks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtRow, edtColumn, edtOneBricks, edtTwoBricks, edtThreeBricks;
    Button btnCheck, btnSetVoids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationWidgets();

        btnCheck.setOnClickListener(this);
        btnSetVoids.setOnClickListener(this);
    }

    /**
     * Метод обработки нажатия кнопок
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check:
                break;
            case R.id.btn_set_voids:
                break;
        }

    }

    /**
     *  Метод инициализации виджетов
     */
    private void initializationWidgets(){
        edtRow = (EditText) findViewById(R.id.edt_row);

        edtColumn = (EditText) findViewById(R.id.edt_column);
        edtOneBricks = (EditText) findViewById(R.id.edt_one_bricks);
        edtTwoBricks = (EditText) findViewById(R.id.edt_two_bricks);
        edtThreeBricks = (EditText) findViewById(R.id.edt_three_bricks);

        btnCheck = (Button) findViewById(R.id.btn_check);
        btnSetVoids = (Button) findViewById(R.id.btn_set_voids);
    }
}

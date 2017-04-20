package com.justappp.nekitpc.testbricks;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtRow, edtColumn, edtOneBricks, edtTwoBricks, edtThreeBricks;
    Button btnCheck, btnSetVoids;
    TextView txtAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initializationWidgets();
        editTextListener();

        btnCheck.setOnClickListener(this);
        btnSetVoids.setOnClickListener(this);
    }

    /**
     * Метод обработки нажатия кнопок
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check:
                checkMethod();
                break;
            case R.id.btn_set_voids:
                break;
        }

    }

    private void editTextListener() {
        edtRow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Метод позволяет проверить полностью ли заполняется стена кирпичами, при условии, что она без просветов и прямоугольной формы.
     */
    private void checkMethod() {
        try {
            int sumArea = Integer.parseInt(edtRow.getText().toString()) * Integer.parseInt(edtColumn.getText().toString());
            int sumAllBricks = (Integer.parseInt(edtOneBricks.getText().toString()))
                    + ((Integer.parseInt(edtTwoBricks.getText().toString()) * 2))
                    + ((Integer.parseInt(edtThreeBricks.getText().toString()) * 3));

            if (sumArea == sumAllBricks) {
                txtAnswer.setText(R.string.yes);
            } else txtAnswer.setText(R.string.no);
        } catch (NumberFormatException e) {
            txtAnswer.setText(R.string.number_format_exception);
        } catch (Exception e) {
            txtAnswer.setText(R.string.exception);
        }

    }

    /**
     * Метод инициализации виджетов
     */
    private void initializationWidgets() {
        edtRow = (EditText) findViewById(R.id.edt_row);
        edtColumn = (EditText) findViewById(R.id.edt_column);
        edtOneBricks = (EditText) findViewById(R.id.edt_one_bricks);
        edtTwoBricks = (EditText) findViewById(R.id.edt_two_bricks);
        edtThreeBricks = (EditText) findViewById(R.id.edt_three_bricks);

        btnCheck = (Button) findViewById(R.id.btn_check);
        btnSetVoids = (Button) findViewById(R.id.btn_set_voids);

        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
    }
}

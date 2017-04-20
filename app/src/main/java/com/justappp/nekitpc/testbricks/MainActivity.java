package com.justappp.nekitpc.testbricks;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        btnCheck.setOnClickListener(this);
        btnSetVoids.setOnClickListener(this);
    }

    /**
     * Метод обработки нажатия кнопок
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

    /**
     * Метод позволяет проверить полностью ли заполняется стена кирпичами, при условии, что она без просветов и прямоугольной формы.
     */
    private void checkMethod() {
        try {
            //Переменной присваивается произведение рядов на столбцы
            int sumArea = Integer.parseInt(edtRow.getText().toString()) * Integer.parseInt(edtColumn.getText().toString());

            int bricksOne;
            int bricksTwo;
            int bricksThree;

            //Условные операторы, проверяют, ввел ли пользователь какое либо число в виджет.
            //Если не ввел, то переменная получает значение ноль.
            //Если ввел, то переменная получает введеное значение.
            if (edtOneBricks.getText().toString().equals("")) {
                bricksOne = 0;
            } else bricksOne = Integer.parseInt(edtOneBricks.getText().toString());

            if (edtTwoBricks.getText().toString().equals("")) {
                bricksTwo = 0;
            } else bricksTwo = Integer.parseInt(edtTwoBricks.getText().toString()) * 2;

            if (edtThreeBricks.getText().toString().equals("")) {
                bricksThree = 0;
            } else bricksThree = Integer.parseInt(edtThreeBricks.getText().toString()) * 3;

            //Переменной присваивается сумма всех кирпичей
            int sumAllBricks = bricksOne + bricksTwo + bricksThree;

            //Условный опрератор проверяет, что если количество столбцов равно 2, и пользователь ввел какое-то кол-во кирпичей длиной 3,
            //то вывод текста в виджете txtAnswer будет "NO", так как такой кирпич нельзя поместить горизонтально.
            //Если количество столбцов равно 1, и пользователь ввел данные в поле для кирпичей длиной 2,
            //то вывод текста будет "NO"
            //Если произведение рядов и столбцов равно сумме всех кирпичей, при условии, что все условия соблюдены,
            //то выводится текст "YES"
            //При всех другий условиях будет выводится "NO"
            if (Integer.parseInt(edtColumn.getText().toString()) == 2 && !edtThreeBricks.getText().toString().equals("")) {
                txtAnswer.setText(R.string.no);
            } else if (Integer.parseInt(edtColumn.getText().toString()) == 1 && !edtTwoBricks.getText().toString().equals("") || !edtThreeBricks.getText().toString().equals("")) {
                txtAnswer.setText(R.string.no);
            } else if (sumArea == sumAllBricks) {
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

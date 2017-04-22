package com.justappp.nekitpc.testbricks;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtRow, edtColumn, edtOneBricks, edtTwoBricks, edtThreeBricks, edtRowAndColumnEnter;
    Button btnCheck, btnSetVoids;
    TextView txtAnswer;

    int sumArea;
    int bricksOne;
    int bricksTwo;
    int bricksThree;


//    int bricksOne;
//    int bricksTwo;
//    int bricksThree;
//    int counterBricksOne;
//    int counterBricksTwo;
//    int counterBricksThree;

    List<Character> matrixRowAndColumn = new ArrayList<>();
    List<Integer> arrLineRowAndColumn = new ArrayList<>();

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
                break;
            case R.id.btn_set_voids:
                putArr();

                String s = String.valueOf(matrixRowAndColumn);
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                s = String.valueOf(arrLineRowAndColumn);
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                matrixRowAndColumn.clear();
                arrLineRowAndColumn.clear();
                break;
        }
    }

    private void putArr() {
        String str = String.valueOf(edtRowAndColumnEnter.getText());
        String array = str.replaceAll("\n", "0");
        char[] myCharArray = array.toCharArray();

        for (int i = 0; i < myCharArray.length; i++) {
            matrixRowAndColumn.add(i, myCharArray[i]);
        }

        int counter = 0;

        for (int i = 0; i < matrixRowAndColumn.size(); i++) {
            if (Integer.parseInt(String.valueOf(matrixRowAndColumn.get(i))) > 0) {
                counter++;
            } else {
                arrLineRowAndColumn.add(counter);
                counter = 0;
            }
        }
        arrLineRowAndColumn.add(counter);
        someMethod();
    }

    private void someMethod() {

        if (edtOneBricks.getText().toString().equals("")) {
            bricksOne = 0;
        } else bricksOne = Integer.parseInt(edtOneBricks.getText().toString());

        if (edtTwoBricks.getText().toString().equals("")) {
            bricksTwo = 0;
        } else bricksTwo = Integer.parseInt(edtTwoBricks.getText().toString());

        if (edtThreeBricks.getText().toString().equals("")) {
            bricksThree = 0;
        } else bricksThree = Integer.parseInt(edtThreeBricks.getText().toString());

        for (int i = 0; i < arrLineRowAndColumn.size(); i++) {
            for (int j = arrLineRowAndColumn.size(); j > 0; j--) {
                if (bricksThree > 0 || bricksTwo > 0 || bricksOne > 0) {
                    if (arrLineRowAndColumn.get(i) >= 3) {
                        arrLineRowAndColumn.set(i, arrLineRowAndColumn.get(i) - 3);
                        bricksThree--;
                    } else if (arrLineRowAndColumn.get(i) == 2) {
                        arrLineRowAndColumn.set(i, arrLineRowAndColumn.get(i) - 2);
                        bricksTwo--;
                    } else if (arrLineRowAndColumn.get(i) == 1){
                        arrLineRowAndColumn.set(i, arrLineRowAndColumn.get(i) - 1);
                        bricksOne--;
                        break;
                    }
                }
            }
        }
    }


//    private void someMethod() {
//
//
//        // Если поля "Ряд" , "Колонка" пустые, то ответ будет "НЕТ"
//        if (edtRow.getText().toString().equals("") || edtColumn.getText().toString().equals("")) {
//            txtAnswer.setText(R.string.no);
//            return;
//        } else
//            sumArea = Integer.parseInt(edtRow.getText().toString()) * Integer.parseInt(edtColumn.getText().toString());
//
//
//        // Если поле "Ввод кирпичей" пустое, то ответ будет "НЕТ"
//        if (edtRowAndColumnEnter.getText().toString().equals("")) {
//            txtAnswer.setText(R.string.no);
//            // Если произведение рядов на столбцы не равно общему кол-ву заполненых ячеек кирпичами, то ответ будет "НЕТ"
//        } else if (sumArea != matrixRowAndColumn.size()) {
//            txtAnswer.setText(R.string.no);
//        } else txtAnswer.setText(R.string.yes);
//
//
//        if (edtOneBricks.getText().toString().equals("")) {
//            bricksOne = 0;
//        } else bricksOne = Integer.parseInt(edtOneBricks.getText().toString());
//
//        if (edtTwoBricks.getText().toString().equals("")) {
//            bricksTwo = 0;
//        } else bricksTwo = Integer.parseInt(edtTwoBricks.getText().toString()) * 2;
//
//        if (edtThreeBricks.getText().toString().equals("")) {
//            bricksThree = 0;
//        } else bricksThree = Integer.parseInt(edtThreeBricks.getText().toString()) * 3;
//
//
//    }

//    private void hardMethod(){
//        // Если есть тройной кирпич, заходим
//        if (bricksThree > 0 & bricksTwo > 0 & bricksOne > 0) {
//            int count = 0;
//            // Проходимся по циклу
//            for (int i = 0; i < matrixRowAndColumn.size(); i++) {
//                if (count < 4) {
//                    // если текущий индекс массива > 0
//                    // счетчик итерируется
//                    if (matrixRowAndColumn.get(i) > 0) {
//                        count++;
//                        // если счетчик = 3, то отнимаем от тройный одно чмсло
//                        if (count == 3) {
//                            bricksThree--;
//                        }
//                        // если же текущий индекс массива < 0
//                        // счетчик сбрасывается
//                    } else {
//                        count = 0;
//                    }
//                }
//            }
//        }
//    }


    /**
     * Метод позволяет проверить полностью ли заполняется стена кирпичами, при условии, что она без просветов и прямоугольной формы.
     */
//    private void checkMethod() {
//
//        int sumArea;
//        int row;
//        int column;
//
//        try {
//            row = Integer.parseInt(edtRow.getText().toString());
//            column = Integer.parseInt(edtColumn.getText().toString());
//            //Переменной присваивается произведение рядов на столбцы
//            sumArea = row * column;
//
//            int bricksOne;
//            int bricksTwo;
//            int bricksThree;
//
//            //Условные операторы, проверяют, ввел ли пользователь какое либо число в виджет.
//            //Если не ввел, то переменная получает значение ноль.
//            //Если ввел, то переменная получает введеное значение.
//            if (edtOneBricks.getText().toString().equals("")) {
//                bricksOne = 0;
//            } else bricksOne = Integer.parseInt(edtOneBricks.getText().toString());
//
//            if (edtTwoBricks.getText().toString().equals("")) {
//                bricksTwo = 0;
//            } else bricksTwo = Integer.parseInt(edtTwoBricks.getText().toString()) * 2;
//
//            if (edtThreeBricks.getText().toString().equals("")) {
//                bricksThree = 0;
//            } else bricksThree = Integer.parseInt(edtThreeBricks.getText().toString()) * 3;
//
//            //Переменной присваивается сумма всех кирпичей
//            int sumAllBricks = bricksOne + bricksTwo + bricksThree;
//
//            //Условный опрератор проверяет, что если количество столбцов равно 2, и пользователь ввел какое-то кол-во кирпичей длиной 3,
//            //то вывод текста в виджете txtAnswer будет "NO", так как такой кирпич нельзя поместить горизонтально.
//            //Если количество столбцов равно 1, и пользователь ввел данные в поле для кирпичей длиной 2,
//            //то вывод текста будет "NO"
//            //Если произведение рядов и столбцов равно сумме всех кирпичей, при условии, что все условия соблюдены,
//            //то выводится текст "YES"
//            //При всех другий условиях будет выводится "NO"
//            if (Integer.parseInt(edtColumn.getText().toString()) == 2 && !edtThreeBricks.getText().toString().equals("")) {
//                txtAnswer.setText(R.string.no);
//            } else if (Integer.parseInt(edtColumn.getText().toString()) == 1 && !edtTwoBricks.getText().toString().equals("") || !edtThreeBricks.getText().toString().equals("")) {
//                txtAnswer.setText(R.string.no);
//            } else if (sumArea == sumAllBricks) {
//                txtAnswer.setText(R.string.yes);
//            } else txtAnswer.setText(R.string.no);
//        } catch (NumberFormatException e) {
//            txtAnswer.setText(R.string.number_format_exception);
//        } catch (Exception e) {
//            txtAnswer.setText(R.string.exception);
//        }
//    }

    /**
     * Метод инициализации виджетов
     */
    private void initializationWidgets() {
        edtRow = (EditText) findViewById(R.id.edt_row);
        edtColumn = (EditText) findViewById(R.id.edt_column);
        edtOneBricks = (EditText) findViewById(R.id.edt_one_bricks);
        edtTwoBricks = (EditText) findViewById(R.id.edt_two_bricks);
        edtThreeBricks = (EditText) findViewById(R.id.edt_three_bricks);
        edtRowAndColumnEnter = (EditText) findViewById(R.id.edt_row_and_column_enter);

        btnCheck = (Button) findViewById(R.id.btn_check);
        btnSetVoids = (Button) findViewById(R.id.btn_set_voids);

        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
    }
}

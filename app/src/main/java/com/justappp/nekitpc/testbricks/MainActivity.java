package com.justappp.nekitpc.testbricks;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Приложение разработано в среде разработки Android Studio 2.3.1
 *
 * Автор: Романенко Никита
 * email: nekit88j@gmail.com
 *
 * Программа выполняет проверку возможности построить стены из задонного количества кирпичей задонного размера.
 * Учтены все условия, конфигурация стены не ограничена: она может разбиться на несколько частей, может показаться нестабильной, может иметь отверстия.
 * Параметры стены и размеры кирпичей настраиваются программно в редакторе текста (EditText).
 * Результат проверки печатается в текстовом виде, либо «YES» (стена может быть построена), либо «NO» (стена не может быть построена).
 *
 * Для ввода значение количества рядов и количества столбцов, следует вводить данные в редакторе текста (Row) для рядов и (Column) для столбцов.
 *
 * Для ввода заданного количества кирпичей заданного размера, следует вводить данные в поля редактора текста (One) для одинарных кирпичей,(Two) для двойных и (Three) для тройных.
 *
 * Для коррестности работы приложения, следует обратить внимание на то, что ввод конфигурации стены осуществляется в редакторе текста (Enter wall configuration)
 * Вводить можно с переносом или разделяя ряды нулями.
 * Для ввода отверстия или разделения стены на несколько частей нужно ввести - 0, для ввода кирпича подходит любая цифра > 0
 * Пример:
 *  10101
 *  11111  =  10101011111011111
 *  11111
 *
 *  В данном примере, описана стена размером три ряда на пять столбцов, с двумя отвертстиями в верхнем ряде.
 *
 *  После ввода заданного количества рядов, столбцов, кирпичей заданного размера и конфигурации стены, для получения результата следует нажть на кнопку "Check"
 */

public class MainActivity extends AppCompatActivity{

    EditText edtRow, edtColumn, edtOneBricks, edtTwoBricks, edtThreeBricks, edtRowAndColumnEnter;
    Button btnCheck;
    TextView txtAnswer;

    int sumArea;
    int bricksOne;
    int bricksTwo;
    int bricksThree;

    List<Character> matrixRowAndColumn = new ArrayList<>();
    List<Integer> arrLineRowAndColumn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initWidgets();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMethod();

                matrixRowAndColumn.clear();
                arrLineRowAndColumn.clear();
            }
        });
    }

    /**
     * Метод заполнение массивов значениями из EditTExt edtRowAndColumnEnter
     */
    private void putInArr() {
        //инициализируем переменную значением с поля ввода стены
        String str = String.valueOf(edtRowAndColumnEnter.getText());
        //все переносы когда заканчивается ряд замещаем нулями
        String array = str.replaceAll("\n", "0");
        //инициализирую char массив данными.
        char[] myCharArray = array.toCharArray();

        //заполняю массив matrixRowAndColumn данными из массива myCharArray
        for (int i = 0; i < myCharArray.length; i++) {
            matrixRowAndColumn.add(i, myCharArray[i]);
        }

        //объявляю переменую - счетчик для заполнения им массива arrLineRowAndColumn
        int counter = 0;

        //цикл проходит по массиву matrixRowAndColumn, и если текущий индекс массива больше нуля, инкрементирует счетчик.
        //иначе добавляет значение счетчика в массив и сбрасывает его для следующих шагов
        for (int i = 0; i < matrixRowAndColumn.size(); i++) {
            if (Integer.parseInt(String.valueOf(matrixRowAndColumn.get(i))) > 0) {
                counter++;
            } else {
                arrLineRowAndColumn.add(counter);
                counter = 0;
            }
        }
        //добавляем последнее значение счетчика в массив
        arrLineRowAndColumn.add(counter);
    }

    /**
     * Метод присвоения значений примитивам, которые соответсвуют размерам кирпичей
     */
    private void initPrimitiv() {
        //усовные операторы проверяют, если место под ввод данных для кирпичей пустое,
        //то инициализирует его 0, иначе присваивает значение с поля ввода.
        if (edtOneBricks.getText().toString().equals("")) {
            bricksOne = 0;
        } else bricksOne = Integer.parseInt(edtOneBricks.getText().toString());

        if (edtTwoBricks.getText().toString().equals("")) {
            bricksTwo = 0;
        } else bricksTwo = Integer.parseInt(edtTwoBricks.getText().toString());

        if (edtThreeBricks.getText().toString().equals("")) {
            bricksThree = 0;
        } else bricksThree = Integer.parseInt(edtThreeBricks.getText().toString());
    }

    /**
     * Метод вставки кирпичей в пустые места
     */
    private void insertBricksInEmptySpace() {
        // вызов метода присвоения значений примитивам
        initPrimitiv();
        // вызов метода заполнения массивов значениями из EditTExt edtRowAndColumnEnter
        putInArr();

        //цикл проходится по массиву matrixRowAndColumn
        for (int i = 0; i < matrixRowAndColumn.size(); i++) {
            // цикл проходится по массиву arrLineRowAndColumn
            for (int j = 0; j < arrLineRowAndColumn.size(); j++) {
                //при условии, что если есть тройные кирпичи проходим в следующий условный оператор
                if (bricksThree > 0) {
                    //при условии, что текущее значение индекса массива arrLineRowAndColumn больше или равна 3,
                    //мы это значение уменьшаем на 3 и убираем один кирпич из суммы тройных кирпичей
                    //остальные циклы выполняют такую же работу, только для двойных кирпичей и одинарных
                    if (arrLineRowAndColumn.get(j) >= 3) {
                        arrLineRowAndColumn.set(j, arrLineRowAndColumn.get(j) - 3);
                        bricksThree--;
                    }
                }
            }
        }
        for (int i = 0; i < matrixRowAndColumn.size(); i++) {
            for (int j = 0; j < arrLineRowAndColumn.size(); j++) {
                if (bricksTwo > 0) {
                    if (arrLineRowAndColumn.get(j) >= 2) {
                        arrLineRowAndColumn.set(j, arrLineRowAndColumn.get(j) - 2);
                        bricksTwo--;
                    }
                }
            }
        }
        for (int i = 0; i < matrixRowAndColumn.size(); i++) {
            for (int j = 0; j < arrLineRowAndColumn.size(); j++) {
                if (bricksOne > 0) {
                    if (arrLineRowAndColumn.get(j) >= 1) {
                        arrLineRowAndColumn.set(j, arrLineRowAndColumn.get(j) - 1);
                        bricksOne--;
                    }
                }
            }
        }
    }

    /**
     * Метод проверки всех условий для вывода текса "YES" или "NO"
     */
    private void checkMethod() {
        //Вызов метода вставки кирпичей в пустые места
        insertBricksInEmptySpace();
        try {

            int row = Integer.parseInt(edtRow.getText().toString()); // количество рядов
            int column = Integer.parseInt(edtColumn.getText().toString()); // количество колонок
            sumArea = row * column; // произведение рядов и столбцов
            int fillPlace = 0; // сумма места под кирпичи
            int sumBricks = bricksOne + (bricksTwo * 2) + (bricksThree * 3); //сумма заполняемого кирпичами места
            int resultMethodInsert = 0; // результат метода insertBricksInEmptySpace()

            // получение суммы места под кирпичи
            for (int i = 0; i < arrLineRowAndColumn.size(); i++) {
                fillPlace += arrLineRowAndColumn.get(i);
            }

            // Если есть пустые EditText( ряд, столбец или ввод данных как будут размещаться кирпичи) то вывод сразу "NO",
            // так как нельзя построить стену без рядов, столбцов или без кирпичей.
            if (edtRow.getText().toString().equals("") || edtColumn.getText().toString().equals("") || edtRowAndColumnEnter.getText().toString().equals("")) {
                txtAnswer.setText(R.string.no);
                return;
                // Если площадь стен не равна возможным местам под кирпичи, то ответ будет "NO"
            } else if (sumArea != matrixRowAndColumn.size() - row + 1) {
                txtAnswer.setText(R.string.no);
                return;
                // Если места под кирпичи не равны сумме кирпичей, то ответ будет "NO"
            } else if (fillPlace != sumBricks) {
                txtAnswer.setText(R.string.no);
                return;
            }

            // получение результата от метода insertBricksInEmptySpace()
            for (int i = 0; i < arrLineRowAndColumn.size(); i++) {
                resultMethodInsert += arrLineRowAndColumn.get(i);
            }

            //При условии, что результат метода insertBricksInEmptySpace() будер = 0,
            // в TextView выведится "YES", в любом другом случае выведится "NO"
            if (resultMethodInsert != 0) {
                txtAnswer.setText(R.string.no);
                return;
            } else txtAnswer.setText(R.string.yes);

            // для упрощения кода, перехватываю исключения
            // если места под ряд или под колонку пустое, срабатывает перехват
        } catch (NumberFormatException e) {
            txtAnswer.setText(R.string.number_format_exception);
        } catch (Exception e) {
            txtAnswer.setText(R.string.exception);
        }
    }

    /**
     * Метод инициализации виджетов
     */

    private void initWidgets() {
        // инициализирую виджеты
        edtRow = (EditText) findViewById(R.id.edt_row);
        edtColumn = (EditText) findViewById(R.id.edt_column);
        edtOneBricks = (EditText) findViewById(R.id.edt_one_bricks);
        edtTwoBricks = (EditText) findViewById(R.id.edt_two_bricks);
        edtThreeBricks = (EditText) findViewById(R.id.edt_three_bricks);
        edtRowAndColumnEnter = (EditText) findViewById(R.id.edt_row_and_column_enter);

        btnCheck = (Button) findViewById(R.id.btn_check);

        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
    }
}

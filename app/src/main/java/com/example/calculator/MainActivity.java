package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView zero;
    TextView dot;
    TextView delete;
    TextView plus;
    TextView minus;
    TextView times;
    TextView divide;
    TextView equals;
    TextView result;

    String strNum = "";
    String strOperator = "";

    double first;
    double second;

    boolean operatorClicked = false;
    boolean secondNumExist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (TextView)findViewById(R.id.one);
        two = (TextView)findViewById(R.id.two);
        three = (TextView)findViewById(R.id.three);
        four = (TextView)findViewById(R.id.four);
        five = (TextView)findViewById(R.id.five);
        six = (TextView)findViewById(R.id.six);
        seven = (TextView)findViewById(R.id.seven);
        eight = (TextView)findViewById(R.id.eight);
        nine = (TextView)findViewById(R.id.nine);
        zero = (TextView)findViewById(R.id.zero);
        dot = (TextView)findViewById(R.id.dot);
        delete = (TextView)findViewById(R.id.delete);

        plus = (TextView)findViewById(R.id.plus);
        minus = (TextView)findViewById(R.id.minus);
        times = (TextView)findViewById(R.id.times);
        divide = (TextView)findViewById(R.id.divide);
        equals = (TextView)findViewById(R.id.equals);
        result = (TextView)findViewById(R.id.result);


        View.OnClickListener number = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v = (TextView)view;
                if (result.getText().toString().equals("0")){
                    strNum = v.getText().toString();
                }
                else if (strNum.length() <= 10){
                    strNum = strNum + v.getText().toString();
                }
                result.setText(strNum);

                if (operatorClicked == false){
                    first = Double.parseDouble(strNum);
                }
                else if (operatorClicked == true){
                    second = Double.parseDouble(strNum);
                    secondNumExist = true;
                }
            }
        };
        one.setOnClickListener(number);
        two.setOnClickListener(number);
        three.setOnClickListener(number);
        four.setOnClickListener(number);
        five.setOnClickListener(number);
        six.setOnClickListener(number);
        seven.setOnClickListener(number);
        eight.setOnClickListener(number);
        nine.setOnClickListener(number);
        zero.setOnClickListener(number);
        dot.setOnClickListener(number);

        View.OnClickListener back = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                strNum = "";
//                if (secondNumExist){
//                    secondNumExist = false;
//                    second = 0;
//                    result.setText(0);
//                }
//                else {
//
//                }
                if (strNum.length() == 1){
                    strNum = "0";
                }
                else {
                    strNum = strNum.substring(0, strNum.length() - 1);
                }
                result.setText(strNum);

                if (operatorClicked == false){
                    first = Double.parseDouble(strNum);
                }
                if (operatorClicked == true){
                    second = Double.parseDouble(strNum);
                    secondNumExist = true;
                }
            }
        };
        delete.setOnClickListener(back);

        final View.OnClickListener operator = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClicked = true;
                TextView v = (TextView)view;
                strNum = "";

                if (secondNumExist){
                    String strAnswer = calculation();
                    result.setText(strAnswer);
                    first = Double.parseDouble(strAnswer);
                    secondNumExist = false;
                }

                plus.setTextColor(Color.rgb(105, 106, 103));
                minus.setTextColor(Color.rgb(105, 106, 103));
                times.setTextColor(Color.rgb(105, 106, 103));
                divide.setTextColor(Color.rgb(105, 106, 103));
                v.setTextColor(Color.rgb(226,154, 59));
                strOperator = v.getText().toString();
            }
        };
        plus.setOnClickListener(operator);
        minus.setOnClickListener(operator);
        times.setOnClickListener(operator);
        divide.setOnClickListener(operator);

        View.OnClickListener equal = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClicked = false;
                secondNumExist = false;
                strNum = "";
                plus.setTextColor(Color.rgb(105, 106, 103));
                minus.setTextColor(Color.rgb(105, 106, 103));
                times.setTextColor(Color.rgb(105, 106, 103));
                divide.setTextColor(Color.rgb(105, 106, 103));
                String strAnswer = calculation();
                result.setText(strAnswer);
                if (strAnswer.equals("Error")){
                    first = 0;
                }
                else{
                    first = Double.parseDouble(strAnswer);
                }
                strOperator = "";
            }
        };
        equals.setOnClickListener(equal);
    }

    private String calculation() {
        double answer;
        if (strOperator.equals("")){
            answer = first;
        }
        else if (strOperator.equals("+")){
            answer = first + second;
        }
        else if (strOperator.equals("-")){
            answer = first - second;
        }
        else if (strOperator.equals("×")){
            answer = first * second;
        }
        else {
            if (second == 0){
                return "Error";
            }
            answer = first / second;
        }

        String strAnswer = String.valueOf(answer);
        if (strAnswer.substring(strAnswer.length() - 2).equals(".0")){
            strAnswer = strAnswer.substring(0, strAnswer.length() - 2);
        }
        if (strAnswer.length() > 10){
            if (strAnswer.contains("E")){
                strAnswer = strAnswer.substring(0, 11 - strAnswer.substring(strAnswer.indexOf("E")).length()) + strAnswer.substring(strAnswer.indexOf("E"));
            }
            else {
                strAnswer = strAnswer.substring(0, 11);
            }
        }
        return strAnswer;
    }
}
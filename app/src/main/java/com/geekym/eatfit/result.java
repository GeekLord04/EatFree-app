package com.geekym.eatfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class result extends AppCompatActivity {
    TextView bmiresult;
    TextView status,question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bmiresult = (TextView) findViewById(R.id.bmiresult);
        question = (TextView) findViewById(R.id.qstn5);

        Float bmi = getIntent().getExtras().getFloat("bmires");
        String bmiset = String.format("%.2f",String.valueOf(bmi));

        bmiresult.setText("Your BMI is - "+ bmiset);

        status = (TextView) findViewById(R.id.status);
        if (bmi<18.5){
            status.setText("Status - Underweight");
        }
        else if (bmi>= 18.5 && bmi<= 24.9){
            status.setText("Status - Normal weight");
        }
        else if (bmi>24.9 && bmi<30){
            status.setText("Status - Overweight");
            question.setText("Want some food suggestion for your high BMI?");
        }
        else{
            status.setText("Status - Obese");
            question.setText("Want some food suggestion for your low BMI?");
        }
    }
}
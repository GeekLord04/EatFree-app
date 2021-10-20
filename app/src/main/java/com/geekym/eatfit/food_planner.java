package com.geekym.eatfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class food_planner extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText heightbmi;
    EditText weightbmi;
    String heightold;
    String weightold;
    Float height;
    Float weight;
    Float newbmi;
    Button calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_planner);

        Spinner diseases = findViewById(R.id.diseases);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.arraydiseases,R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diseases.setAdapter(adapter);

        heightbmi = findViewById(R.id.heightbmi);
        weightbmi = findViewById(R.id.weightbmi);
        calc = findViewById(R.id.calc);
//        heightold = heightbmi.getText().toString();
//        height = Float.parseFloat(heightold);
//        weightold = weightbmi.getText().toString();
//        weight = Float.parseFloat(weightold);

        // on click listener function
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightold = heightbmi.getText().toString();
//                height = Float.parseFloat(heightold);
                weightold = weightbmi.getText().toString();
//                weight = Float.parseFloat(weightold);

                if (TextUtils.isEmpty(heightold)){
                    heightbmi.setError("Enter the height");
                    heightbmi.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(weightold)){
                    weightbmi.setError("Enter the weight");
                    weightbmi.requestFocus();
                    return;
                }
                else{
                    height = Float.parseFloat(heightold);
                    weight = Float.parseFloat(weightold);
                    Float heightmul = (height*height);
                    newbmi = (Float) (weight/heightmul);
                    Intent intent = new Intent(food_planner.this,result.class);
                    intent.putExtra("bmires",newbmi);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    private float bmicalculate(float weightcalc, float heightcalc){
//        return (float) (weightcalc/heightcalc*heightcalc);
//    }

}
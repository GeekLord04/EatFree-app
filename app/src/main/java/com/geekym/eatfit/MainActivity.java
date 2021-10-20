package com.geekym.eatfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    RadioGroup toggle;
    RadioButton login,singup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addfragment(new Fragment_Login());

        toggle = findViewById(R.id.toggle);
        login = findViewById(R.id.loginbutton);
        singup = findViewById(R.id.signupbutton);

        toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.signupbutton){
                    replacefragment(new Fragment_Signup());
//                    Animatoo.animateSlideLeft(MainActivity.this);

                }
                else if (checkedId == R.id.loginbutton){
                    replacefragment(new Fragment_Login());
//                    Animatoo.animateFade(getApplicationContext());

                }
            }
        });


}

    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    private void addfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction newfragmentTransaction = fragmentManager.beginTransaction();
        newfragmentTransaction.add(R.id.framelayout,fragment);
        newfragmentTransaction.commit();
    }
    }
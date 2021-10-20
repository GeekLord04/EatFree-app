package com.geekym.eatfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Fragment_Login extends Fragment {

    private FirebaseAuth Auth;
    private ProgressBar progressBar;

    View view;
    EditText emaillogin,passwordlogin;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Auth = FirebaseAuth.getInstance();

        emaillogin = (EditText) getView().findViewById(R.id.emaillogin);
        passwordlogin = (EditText) getView().findViewById(R.id.passwordlogin);
        login = (Button) getView().findViewById(R.id.newloginbutton);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emaillogin.getText().toString().trim();
                String password = passwordlogin.getText().toString().trim();
                if (email.isEmpty()){
                    emaillogin.setError("Field can't be Empty");
                    emaillogin.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emaillogin.setError("Please enter a valid email address");
                    emaillogin.requestFocus();
                    return;
                }
                else if (password.isEmpty()){
                    passwordlogin.setError("Field can't be Empty");
                    passwordlogin.requestFocus();
                    return;
                }
                else if (password.length()<5){
                    passwordlogin.setError("Password must be atleast 5 characters");
                    passwordlogin.requestFocus();
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);

                    Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getActivity(),food_planner.class));
                                Animatoo.animateFade(getContext());
                                getActivity().finishAffinity();
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Failed to Login! Please check your credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}
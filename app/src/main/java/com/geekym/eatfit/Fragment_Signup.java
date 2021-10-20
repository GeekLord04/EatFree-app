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
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment_Signup extends Fragment {

    private FirebaseAuth mAuth;

    View view;
    EditText email;
    EditText pass;
    EditText confirmpass,name;
    Button signup;
    String passnew,confirmpassnew,emailnew;
    String namenew;
    Boolean check;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mAuth = FirebaseAuth.getInstance();

        email = (EditText) getView().findViewById(R.id.newemail);
        pass = (EditText) getView().findViewById(R.id.newpassword);
        confirmpass = (EditText) getView().findViewById(R.id.confirmpassword);
        signup = (Button) getView().findViewById(R.id.newsignupbutton);
        name = (EditText) getView().findViewById(R.id.name);
        ProgressBar progressBar = getView().findViewById(R.id.progressBar);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailnew = email.getText().toString().trim();
                namenew = name.getText().toString().trim();

                mAuth.fetchSignInMethodsForEmail(emailnew)
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                check = task.getResult().getSignInMethods().isEmpty();
                                if (check){
                                    email.setError("Email already exists");
                                    email.requestFocus();
                                    return;
                                }
                            }
                        });

                 if (namenew.isEmpty()){
                    name.setError("Field can't be Empty");
                    email.requestFocus();
                    return;
                }
                if(emailnew.isEmpty()) {
                    email.setError("Field can't be Empty");
                    email.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailnew).matches()){
                    email.setError("Please enter a valid email address");
                    email.requestFocus();
                    return;
                }
                passnew = pass.getText().toString().trim();
                confirmpassnew = confirmpass.getText().toString().trim();
                if (passnew.isEmpty()){
                    pass.setError("Field can't be Empty");
                    pass.requestFocus();
                    return;
                }
                else if (confirmpassnew.isEmpty()){
                    confirmpass.setError("Field can't be Empty");
                    confirmpass.requestFocus();
                    return;
                }
                else if (passnew.length()<5){
                    pass.setError("Password must be at least 5 characters");
                    pass.requestFocus();
                    return;
                }
                else if (!passnew.equals(confirmpassnew)){
                    confirmpass.setError("Password did not matched");
                    confirmpass.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(emailnew,passnew)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()){
                                        Users users = new Users(namenew,emailnew);

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                    Intent intent = new Intent(getActivity(),food_planner.class);
                                                    startActivity(intent);
                                                    Animatoo.animateFade(getContext());
                                                    getActivity().finishAffinity();
                                                }
                                                else{
                                                    Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

//                progressBar.setVisibility(View.VISIBLE);
//                mAuth.createUserWithEmailAndPassword(emailnew,passnew)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                if (task.isSuccessful()){
//                                    Users users = new Users(namenew,emailnew);
//
//                                    FirebaseDatabase.getInstance().getReference("Users")
//                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
//                                                progressBar.setVisibility(View.GONE);
//                                                Intent intent = new Intent(getActivity(),food_planner.class);
//                                                startActivity(intent);
//                                                getActivity().finishAffinity();
//                                            }
//                                            else{
//                                                Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
//                                                progressBar.setVisibility(View.GONE);
//                                            }
//                                        }
//                                    });
//                                }
//                                else {
//                                    Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
//                                    progressBar.setVisibility(View.GONE);
//                                }
//                            }
//                        });
            }

        });
    }



//    private void validateEmail() {
//        emailnew = email.getText().toString().trim();
//        namenew = name.getText().toString().trim();
//        if(emailnew.isEmpty()) {
//            email.setError("Field can't be Empty");
//            email.requestFocus();
//            return;
//        }
//        else if (namenew.isEmpty()){
//            name.setError("Field can't be Empty");
//            email.requestFocus();
//            return;
//        }
//        else if (!Patterns.EMAIL_ADDRESS.matcher(emailnew).matches()){
//            email.setError("Please enter a valid email address");
//            email.requestFocus();
//            return;
//        }
//    }

//    private void validatePass() {
//        passnew = pass.getText().toString().trim();
//        confirmpassnew = confirmpass.getText().toString().trim();
//        if (passnew.isEmpty()){
//            pass.setError("Field can't be Empty");
//            pass.requestFocus();
//            return;
//        }
//        else if (confirmpassnew.isEmpty()){
//            confirmpass.setError("Field can't be Empty");
//            confirmpass.requestFocus();
//            return;
//        }
//        else if (passnew.length()<5){
//            pass.setError("Password must be at least 5 characters");
//            pass.requestFocus();
//            return;
//        }
//        else if (!passnew.equals(confirmpassnew)){
//            confirmpass.setError("Password did not matched");
//            confirmpass.requestFocus();
//            return;
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                view = inflater.inflate(R.layout.fragment_new_sign_up, container, false);
        return view;
    }
}
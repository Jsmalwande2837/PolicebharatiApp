package com.solutionner.policebharatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.txt_SignUp)
    public void GoToRegistration() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }
}

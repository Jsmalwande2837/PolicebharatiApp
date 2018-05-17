package com.solutionner.policebharatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.edtName)
    EditText edtName;
    @InjectView(R.id.BtnRegister)
    Button BtnRegister;

    @InjectView(R.id.relativeProfile)
    RelativeLayout relativeProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.BtnRegister)
    public void Register() {
        startActivity(new Intent(RegisterActivity.this, OTPActivity.class));
        finish();
    }

    @OnClick(R.id.relativeProfile)
    public void Profile(){
        startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
        finish();
    }
}

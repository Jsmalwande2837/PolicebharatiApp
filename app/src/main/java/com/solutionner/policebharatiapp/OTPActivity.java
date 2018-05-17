package com.solutionner.policebharatiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.BtnConfirm)
    public void main() {
        startActivity(new Intent(this, BuyActivity.class));
        finish();
    }
}

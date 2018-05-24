package com.solutionner.policebharatiapp.otp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.buy.BuyActivity;

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

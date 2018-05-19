package com.solutionner.policebharatiapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @InjectView(R.id.textviewtry)
    TextView textviewtry;
    @InjectView(R.id.textviewRight)
    TextView textviewRight;
    @InjectView(R.id.textviewWrong)
    TextView textviewWrong;
    @InjectView(R.id.textviewUnsolve)
    TextView textviewUnsolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.inject(this);

    }


}

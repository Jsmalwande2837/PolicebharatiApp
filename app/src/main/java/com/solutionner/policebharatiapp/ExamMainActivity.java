package com.solutionner.policebharatiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ExamMainActivity extends AppCompatActivity {

    @InjectView(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_main);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btnSubmit)
    public void Submit() {
        startActivity(new Intent(this, ExamResultActivity.class));
    }
}

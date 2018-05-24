package com.solutionner.policebharatiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.result.ResultActivity;

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
        startActivity(new Intent(this, ResultActivity.class));
    }

    @OnClick(R.id.btnNext)
    public void Next() {
        startActivity(new Intent(this, RecordActivity.class));
    }


}

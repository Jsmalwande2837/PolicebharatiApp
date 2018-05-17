package com.solutionner.policebharatiapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.txtheading)
    TextView txtheading;
    @InjectView(R.id.txtCongrats)
    TextView txtCongrats;
    @InjectView(R.id.textviewtry)
    TextView textviewtry;
    @InjectView(R.id.textviewRight)
    TextView textviewRight;
    @InjectView(R.id.textviewWrong)
    TextView textviewWrong;
    @InjectView(R.id.textviewUnsolve)
    TextView textviewUnsolve;

    @InjectView(R.id.txtTotalTime)
    TextView txtTotalTime;
    @InjectView(R.id.textviewTotalTime)
    TextView textviewTotalTime;
    @InjectView(R.id.txtEndTime)
    TextView txtEndTime;
    @InjectView(R.id.textviewEndTime)
    TextView textviewEndTime;
    @InjectView(R.id.txtEndDate)
    TextView txtEndDate;
    @InjectView(R.id.textviewEndDate)
    TextView textviewEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        txtheading.setTypeface(tf);
        txtheading.setText("परिणाम");

        txtCongrats.setTypeface(tf);
        txtCongrats.setText("बधाई !!");

        textviewtry.setTypeface(tf);
        textviewtry.setText("प्रयास किया");

        textviewRight.setTypeface(tf);
        textviewRight.setText("सही");

        textviewWrong.setTypeface(tf);
        textviewWrong.setText("गलत");

        textviewUnsolve.setTypeface(tf);
        textviewUnsolve.setText("न सुलझा हुआ");

        txtTotalTime.setTypeface(tf);
        txtTotalTime.setText("परीक्षा की अवधि :");
        textviewTotalTime.setTypeface(tf);
        textviewTotalTime.setText("2 घंटा");

        txtEndTime.setTypeface(tf);
        txtEndTime.setText("परीक्षा समाप्ति समय");
        textviewEndTime.setTypeface(tf);
        textviewEndTime.setText("2 घंटा");


        txtEndDate.setTypeface(tf);
        txtEndDate.setText("परीक्षा  की तिथि");
        textviewEndDate.setTypeface(tf);
        textviewEndDate.setText("2 घंटा");
    }
}

package com.solutionner.policebharatiapp.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.solutionner.policebharatiapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExamHistoryActivity extends AppCompatActivity {

    @InjectView(R.id.txtheading)
    TextView txtheading;
    @InjectView(R.id.txtDate)
    TextView txtDate;
    @InjectView(R.id.txtFullDate)
    TextView txtFullDate;
    @InjectView(R.id.txtTime)
    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_history);
        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        txtheading.setTypeface(tf);
        txtheading.setText("परीक्षा का इतिहास");

        txtDate.setTypeface(tf);
        txtDate.setText("अगस्त 2017");

        txtFullDate.setTypeface(tf);
        txtFullDate.setText("12 अगस्त 2017");

        txtTime.setTypeface(tf);
        txtTime.setText("02:29:00");

    }
}

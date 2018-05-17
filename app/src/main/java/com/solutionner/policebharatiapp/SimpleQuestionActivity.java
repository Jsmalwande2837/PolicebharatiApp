package com.solutionner.policebharatiapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimpleQuestionActivity extends AppCompatActivity {

    @InjectView(R.id.txtView)
    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_question);
        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        txtView.setTypeface(tf);
        txtView.setText("सामान्य प्रश्न");
    }
}

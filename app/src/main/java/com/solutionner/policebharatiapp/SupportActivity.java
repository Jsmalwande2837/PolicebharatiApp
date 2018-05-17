package com.solutionner.policebharatiapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SupportActivity extends AppCompatActivity {
    @InjectView(R.id.txtSupport)
    TextView txtSupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        txtSupport.setTypeface(tf);
        txtSupport.setText("समर्थन");

    }
}

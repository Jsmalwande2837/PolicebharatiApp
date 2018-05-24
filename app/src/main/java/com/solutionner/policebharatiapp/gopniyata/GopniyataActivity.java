package com.solutionner.policebharatiapp.gopniyata;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.solutionner.policebharatiapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GopniyataActivity extends AppCompatActivity {

    @InjectView(R.id.textStudy)
    TextView textStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gopniyata);

        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        textStudy.setTypeface(tf);
        textStudy.setText("गोपनीयता नीति");

    }
}

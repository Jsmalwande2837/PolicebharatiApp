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

public class AdhyayanSamagriActivity extends AppCompatActivity {

    @InjectView(R.id.txtView)
    TextView txtView;
    @InjectView(R.id.btnSamnyaGyan)
    Button btnSamnyaGyan;
    @InjectView(R.id.btnVertmanPrasang)
    Button btnVertmanPrasang;
    @InjectView(R.id.btnHistory)
    Button btnHistory;
    @InjectView(R.id.btnGeography)
    Button btnGeography;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhyayan_samagri);
        ButterKnife.inject(this);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
        txtView.setTypeface(tf);
        txtView.setText("अध्ययन सामग्री");

        btnSamnyaGyan.setTypeface(tf);
        btnSamnyaGyan.setText("सामान्य ज्ञान");

        btnVertmanPrasang.setTypeface(tf);
        btnVertmanPrasang.setText("वर्तमान प्रसंग");

        btnHistory.setTypeface(tf);
        btnHistory.setText("इतिहास");

        btnGeography.setTypeface(tf);
        btnGeography.setText("भूगोल");
    }

    @OnClick(R.id.btnSamnyaGyan)
    public void btnGeneralKnowledge() {

        startActivity(new Intent(this, GenaralKnowledgeActivity.class));
    }

    @OnClick(R.id.btnGeography)
    public void btnGeography() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @OnClick(R.id.btnVertmanPrasang)
    public void btnPresent() {
        startActivity(new Intent(this, SimpleQuestionActivity.class));
    }

    @OnClick(R.id.btnHistory)
    public void history() {
        startActivity(new Intent(this, ResultActivity.class));
    }
}

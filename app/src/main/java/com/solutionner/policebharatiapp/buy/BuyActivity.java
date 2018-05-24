package com.solutionner.policebharatiapp.buy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.aboutus.AboutUsActivity;
import com.solutionner.policebharatiapp.adhyayansamgri.AdhyayanSamagriActivity;
import com.solutionner.policebharatiapp.term.TermAndCondActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BuyActivity extends AppCompatActivity {

    @InjectView(R.id.txtHamburger)
    TextView txtHamburger;
    @InjectView(R.id.relDrawerLayout)
    RelativeLayout relDrawerLayout;
    @InjectView(R.id.main)
    RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.txtHamburger)
    public void ShowDrawer() {
        relDrawerLayout.setVisibility(View.VISIBLE);
        overridePendingTransition(R.anim.left_out, R.anim.right_in);
    }

    @OnClick(R.id.txtBackArrow)
    public void CloseDrawer() {
        relDrawerLayout.setVisibility(View.GONE);

    }

    @OnClick(R.id.main)
    public void MainClick() {
        relDrawerLayout.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnBuyNow)
    public void Buy() {
        startActivity(new Intent(this, AdhyayanSamagriActivity.class));
    }


    @OnClick(R.id.relTemAndCond)
    public void TermAndCondition() {
        startActivity(new Intent(this, TermAndCondActivity.class));
    }

    @OnClick(R.id.relAboutUs)
    public void AboutUs() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }
}

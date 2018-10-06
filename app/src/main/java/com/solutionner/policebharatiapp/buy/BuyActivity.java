package com.solutionner.policebharatiapp.buy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.aboutus.AboutUsActivity;
import com.solutionner.policebharatiapp.adhyayansamgri.AdhyayanSamagriActivity;
import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.login.LoginActivity;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.register.RegistrationActivity;
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
    @InjectView(R.id.relativeProfile)
    RelativeLayout relativeProfile;
    @InjectView(R.id.relLogout)
    RelativeLayout relLogout;

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

    @OnClick(R.id.relativeProfile)
    public void Profile() {
        startActivity(new Intent(BuyActivity.this, ProfileActivity.class));
    }

    @OnClick(R.id.relLogout)
    public void Logout() {
        ShowAlert(BuyActivity.this);
    }

    private void ShowAlert(BuyActivity buyActivity) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(buyActivity)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(BuyActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        ExamApplication.onSaveLoginDetail("", "", "", "", "", "");
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog dialog = alertDialog.create();
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        dialog.show();
    }
}

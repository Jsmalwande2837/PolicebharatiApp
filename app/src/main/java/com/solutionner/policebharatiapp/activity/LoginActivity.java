package com.solutionner.policebharatiapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.solutionner.policebharatiapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.txt_SignUp)
    public void GoToRegistration() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.txtForgotPass)
    public void ForgotPassword() {
        dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_for_forgotpassword);
        dialog.show();

        final EditText edtMobileNo = (EditText) dialog.findViewById(R.id.edtMobileNo);
        final Button textViewCancel = (Button) dialog.findViewById(R.id.textViewCancel);
        final Button textViewOk = (Button) dialog.findViewById(R.id.textViewOk);

        dialog.setCancelable(false);

        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textViewOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMobileNo.getText().length() < 1) {
                    edtMobileNo.setError("Enter Mobile no");
                } else {
                    String mobileNo = edtMobileNo.getText().toString();
                    dialog.dismiss();
                }
            }
        });
    }


}

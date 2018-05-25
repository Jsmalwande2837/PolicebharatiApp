package com.solutionner.policebharatiapp.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.register.RegistrationActivity;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.AlertDialogs;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.PrintUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private Dialog dialog;
    AlertDialogs mAlert;
    private LoginServiceProvider loginServiceProvider;

    @InjectView(R.id.edtMobile)
    EditText edtMobile;
    @InjectView(R.id.edtPassword)
    EditText edtPassword;
    @InjectView(R.id.BtnLogin)
    Button BtnLogin;
    private String getPhone;
    private String getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initObjects();
    }

    private void initObjects() {
        mAlert = AlertDialogs.getInstance();
        loginServiceProvider = new LoginServiceProvider(this);
    }


    @OnClick(R.id.txt_SignUp)
    public void GoToRegistration() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        finish();
    }


    @OnClick(R.id.BtnLogin)
    public void loginUser() {

        String Mobile = edtMobile.getText().toString();
        String Password = edtPassword.getText().toString();

        if (edtMobile.getText().length() < 10) {
            edtMobile.setError("Please enter valid phone number");
        } else if (Password.length() < 4) {
            edtPassword.setError("Please enter 4 digit password");
        } else {

            getPhone = edtMobile.getText().toString();
            getPassword = edtPassword.getText().toString();

            CallLoginApi(getPhone, getPassword);
        }

    }


    private void CallLoginApi(String getPhone, String getPassword) {
        mAlert.onShowProgressDialog(LoginActivity.this, true);
        loginServiceProvider.CallLogin(getPhone, getPassword, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((LoginModel) serviceResponse).getStatus());
                    String message = ((LoginModel) serviceResponse).getMessage();
                    ArrayList<LoginModel.Data> mLoginData = ((LoginModel) serviceResponse).getData();
                    if (Status == 200) {

                        mAlert.onShowProgressDialog(LoginActivity.this, false);

                    } else {
                        mAlert.onShowToastNotification(LoginActivity.this, message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAlert.onShowProgressDialog(LoginActivity.this, false);
                }
            }

            @Override
            public <T> void onFailure(T apiErrorModel, T extras) {
                try {

                    if (apiErrorModel != null) {
                        PrintUtil.showToast(LoginActivity.this, ((BaseServiceResponseModel) apiErrorModel).getMessage());
                    } else {
                        PrintUtil.showNetworkAvailableToast(LoginActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.showNetworkAvailableToast(LoginActivity.this);
                } finally {
                    mAlert.onShowProgressDialog(LoginActivity.this, false);
                }
            }
        });
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
                    //CallForgotApi(mobileNo);
                    dialog.dismiss();
                }
            }
        });
    }

}

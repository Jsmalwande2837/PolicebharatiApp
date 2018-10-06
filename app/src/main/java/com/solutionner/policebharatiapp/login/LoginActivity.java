package com.solutionner.policebharatiapp.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.solutionner.policebharatiapp.MainActivity;
import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.buy.BuyActivity;
import com.solutionner.policebharatiapp.forgotpassword.ForgotPasswordModel;
import com.solutionner.policebharatiapp.forgotpassword.ForgotPasswordServiceProvider;
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
    private ForgotPasswordServiceProvider forgotPasswordServiceProvider;
    @InjectView(R.id.edtMobile)
    EditText edtMobile;
    @InjectView(R.id.edtPassword)
    EditText edtPassword;
    @InjectView(R.id.BtnLogin)
    Button BtnLogin;
    @InjectView(R.id.txt_new_member)
    TextView txt_new_member;
    @InjectView(R.id.txt_SignUp)
    TextView txt_SignUp;
    @InjectView(R.id.txtForgotPass)
    TextView txtForgotPass;
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
        mAlert.firebaseAnalytics(this.getClass().getSimpleName());
        loginServiceProvider = new LoginServiceProvider(this);
        forgotPasswordServiceProvider = new ForgotPasswordServiceProvider(this);

        //for font
        edtMobile.setTypeface(ExamApplication.getEnglishFont());
        edtPassword.setTypeface(ExamApplication.getEnglishFont());
        BtnLogin.setTypeface(ExamApplication.getEnglishFont());
        txt_new_member.setTypeface(ExamApplication.getEnglishFont());
        txt_SignUp.setTypeface(ExamApplication.getEnglishFont());
        txtForgotPass.setTypeface(ExamApplication.getEnglishFont());

    }


    @OnClick(R.id.txt_SignUp)
    public void GoToRegistration() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
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
                    ArrayList<LoginModel.User_data> mLoginData = ((LoginModel) serviceResponse).getUser_data();

                    if (Status == 200) {
                        mAlert.onShowToastNotification(LoginActivity.this, message);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        mAlert.onShowProgressDialog(LoginActivity.this, false);
                        ExamApplication.onSaveLoginDetail(String.valueOf(mLoginData.get(0).getUserId()), mLoginData.get(0).getName(), mLoginData.get(0).getMobile(),
                                mLoginData.get(0).getCity(), mLoginData.get(0).getAddress(), mLoginData.get(0).getPassword());

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
                    CallForgotApi(mobileNo, dialog);
                    dialog.dismiss();
                }
            }
        });
    }

    private void CallForgotApi(String mobileNo, final Dialog dialog) {
        mAlert.onShowProgressDialog(LoginActivity.this, true);
        forgotPasswordServiceProvider.GetProfile(mobileNo, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((ForgotPasswordModel) serviceResponse).getStatus());
                    String message = ((ForgotPasswordModel) serviceResponse).getMessage();
                    // ArrayList<ForgotPasswordModel.Data> mLoginData = ((ForgotPasswordModel) serviceResponse).getData();
                    if (Status == 200) {
                        dialog.dismiss();
                        mAlert.onShowToastNotification(LoginActivity.this, message);
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
}
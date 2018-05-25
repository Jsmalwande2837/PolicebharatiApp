package com.solutionner.policebharatiapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.login.LoginModel;
import com.solutionner.policebharatiapp.otp.OTPActivity;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.AlertDialogs;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.PrintUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    @InjectView(R.id.edtName)
    EditText edtName;
    @InjectView(R.id.edtMobile)
    EditText edtMobile;
    @InjectView(R.id.edtCity)
    EditText edtCity;
    @InjectView(R.id.edtAddress)
    EditText edtAddress;
    @InjectView(R.id.edtPassword)
    EditText edtPassword;
    @InjectView(R.id.BtnRegister)
    Button BtnRegister;

    @InjectView(R.id.relativeProfile)
    RelativeLayout relativeProfile;
    AlertDialogs mAlert;
    RegistrationServiceProvider registrationServiceProvider;
    private String getName, getMobile, getCity, getAddress, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        mAlert = AlertDialogs.getInstance();
        registrationServiceProvider = new RegistrationServiceProvider(this);
        edtName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    @OnClick(R.id.BtnRegister)
    public void Register() {

        getName = edtName.getText().toString();
        getCity = edtCity.getText().toString();
        getAddress = edtAddress.getText().toString();
        getPassword = edtPassword.getText().toString();


        if (getName.length() < 0) {
            edtName.setError("Please enter username");
        } else if (edtMobile.getText().length() < 10) {
            edtMobile.setError("Please enter valid phone number");
        } else if (getCity.length() < 1) {
            edtCity.setError("Please enter city");
        } else if (getAddress.length() < 1) {
            edtAddress.setError("Please enter address");
        } else if (getPassword.length() < 4) {
            edtPassword.setError("Please enter 4 digit password");
        } else {

            getMobile = edtMobile.getText().toString();

//            getName = edtName.getText().toString();
//            getMobile = edtMobile.getText().toString();
//            getCity = edtCity.getText().toString();
//            getAddress = edtAddress.getText().toString();
//            getPassword = edtPassword.getText().toString();

            CallRegistrationApi(getName, getMobile, getCity, getAddress, getPassword);
        }
    }

    private void CallRegistrationApi(String getName, String getMobile, String getCity, String getAddress, String getPassword) {
        mAlert.onShowProgressDialog(RegistrationActivity.this, true);
        registrationServiceProvider.CallRegistration(getName, getMobile, getCity, getAddress, getPassword, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((RegistrationModel) serviceResponse).getStatus());
                    String message = ((RegistrationModel) serviceResponse).getMessage();
                    ArrayList<RegistrationModel.Data> mLoginData = ((RegistrationModel) serviceResponse).getData();
                    if (Status == 200) {

                        startActivity(new Intent(RegistrationActivity.this, OTPActivity.class));
                        finish();
                        mAlert.onShowProgressDialog(RegistrationActivity.this, false);

                    } else {
                        mAlert.onShowToastNotification(RegistrationActivity.this, message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAlert.onShowProgressDialog(RegistrationActivity.this, false);
                }
            }

            @Override
            public <T> void onFailure(T apiErrorModel, T extras) {
                try {

                    if (apiErrorModel != null) {
                        PrintUtil.showToast(RegistrationActivity.this, ((BaseServiceResponseModel) apiErrorModel).getMessage());
                    } else {
                        PrintUtil.showNetworkAvailableToast(RegistrationActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.showNetworkAvailableToast(RegistrationActivity.this);
                } finally {
                    mAlert.onShowProgressDialog(RegistrationActivity.this, false);
                }
            }
        });
    }


    @OnClick(R.id.relativeProfile)
    public void Profile() {
        startActivity(new Intent(RegistrationActivity.this, ProfileActivity.class));
        finish();
    }
}

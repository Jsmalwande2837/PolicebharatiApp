package com.solutionner.policebharatiapp.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.widget.EditText;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.application.PoliceBharatiApplication;
import com.solutionner.policebharatiapp.otp.OTPActivity;
import com.solutionner.policebharatiapp.profile.getprofile.GetProfileModel;
import com.solutionner.policebharatiapp.profile.getprofile.GetProfileServiceProvider;
import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileModel;
import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileServiceProvider;
import com.solutionner.policebharatiapp.register.RegistrationModel;
import com.solutionner.policebharatiapp.register.RegistrationServiceProvider;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.AlertDialogs;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.PrintUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

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
    @InjectView(R.id.btnEdit)
    EditText btnEdit;

    AlertDialogs mAlert;
    UpdateProfileServiceProvider updateProfileServiceProvider;
    GetProfileServiceProvider getProfileServiceProvider;
    private String getName, getMobile, getCity, getAddress, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.inject(this);
        initObjects();
    }

    private void initObjects() {
        mAlert = AlertDialogs.getInstance();
        updateProfileServiceProvider = new UpdateProfileServiceProvider(this);
        getProfileServiceProvider = new GetProfileServiceProvider(this);

        edtName.setEnabled(false);
        edtMobile.setEnabled(false);
        edtCity.setEnabled(false);
        edtAddress.setEnabled(false);
        edtPassword.setEnabled(false);

        //Call Get USer Api
        CallGetUserDataApi(PoliceBharatiApplication.onGetUserId());
    }

    private void CallGetUserDataApi(String userId) {
        mAlert.onShowProgressDialog(ProfileActivity.this, true);
        getProfileServiceProvider.GetProfile(userId, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((GetProfileModel) serviceResponse).getStatus());
                    String message = ((GetProfileModel) serviceResponse).getMessage();
                    ArrayList<GetProfileModel.Data> mLoginData = ((GetProfileModel) serviceResponse).getData();
                    if (Status == 200) {
                    } else {
                        mAlert.onShowToastNotification(ProfileActivity.this, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAlert.onShowProgressDialog(ProfileActivity.this, false);
                }
            }

            @Override
            public <T> void onFailure(T apiErrorModel, T extras) {
                try {

                    if (apiErrorModel != null) {
                        PrintUtil.showToast(ProfileActivity.this, ((BaseServiceResponseModel) apiErrorModel).getMessage());
                    } else {
                        PrintUtil.showNetworkAvailableToast(ProfileActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.showNetworkAvailableToast(ProfileActivity.this);
                } finally {
                    mAlert.onShowProgressDialog(ProfileActivity.this, false);
                }
            }
        });
    }

    @OnClick(R.id.btnEdit)
    public void Edit() {

        String getEditText = btnEdit.getText().toString();

        if (getEditText.equals("Edit")) {
            btnEdit.setText("Save");
            edtName.setEnabled(true);
            edtMobile.setEnabled(false);
            edtCity.setEnabled(true);
            edtAddress.setEnabled(true);
            edtPassword.setEnabled(true);
        } else if (getEditText.equals("Save")) {

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

//                getName = edtName.getText().toString();
//                getMobile = edtMobile.getText().toString();
//                getCity = edtCity.getText().toString();
//                getAddress = edtAddress.getText().toString();
//                getPassword = edtPassword.getText().toString();

                CallUpdateApi(getName, getMobile, getCity, getAddress, getPassword);
            }
        }
    }

    private void CallUpdateApi(String getName, String getMobile, String getCity, String
            getAddress, String getPassword) {
        mAlert.onShowProgressDialog(ProfileActivity.this, true);
        updateProfileServiceProvider.CallUpdate(getName, getMobile, getCity, getAddress, getPassword, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((UpdateProfileModel) serviceResponse).getStatus());
                    String message = ((UpdateProfileModel) serviceResponse).getMessage();
                    ArrayList<UpdateProfileModel.Data> mLoginData = ((UpdateProfileModel) serviceResponse).getData();
                    if (Status == 200) {

                        edtName.setEnabled(false);
                        edtMobile.setEnabled(false);
                        edtCity.setEnabled(false);
                        edtAddress.setEnabled(false);
                        edtPassword.setEnabled(false);

                        mAlert.onShowProgressDialog(ProfileActivity.this, false);

                    } else {
                        mAlert.onShowToastNotification(ProfileActivity.this, message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAlert.onShowProgressDialog(ProfileActivity.this, false);
                }
            }

            @Override
            public <T> void onFailure(T apiErrorModel, T extras) {
                try {

                    if (apiErrorModel != null) {
                        PrintUtil.showToast(ProfileActivity.this, ((BaseServiceResponseModel) apiErrorModel).getMessage());
                    } else {
                        PrintUtil.showNetworkAvailableToast(ProfileActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.showNetworkAvailableToast(ProfileActivity.this);
                } finally {
                    mAlert.onShowProgressDialog(ProfileActivity.this, false);
                }
            }
        });
    }

}

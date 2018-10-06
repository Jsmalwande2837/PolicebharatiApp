package com.solutionner.policebharatiapp.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.profile.getprofile.GetProfileModel;
import com.solutionner.policebharatiapp.profile.getprofile.GetProfileServiceProvider;
import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileModel;
import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileServiceProvider;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.AlertDialogs;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.PrintUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @InjectView(R.id.custom_toolbar)
    Toolbar custom_toolbar;
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
    Button btnEdit;

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
        setSupportActionBar(custom_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        custom_toolbar.setContentInsetStartWithNavigation(0);
    }

    private void initObjects() {
        mAlert = AlertDialogs.getInstance();
        mAlert.firebaseAnalytics(this.getClass().getSimpleName());
        updateProfileServiceProvider = new UpdateProfileServiceProvider(this);
        getProfileServiceProvider = new GetProfileServiceProvider(this);

        edtName.setEnabled(false);
        edtMobile.setEnabled(false);
        edtCity.setEnabled(false);
        edtAddress.setEnabled(false);
        edtPassword.setEnabled(false);

        //Call Get User Api
        CallGetUserDataApi(ExamApplication.onGetUserId());
    }

    private void CallGetUserDataApi(String userId) {
        mAlert.onShowProgressDialog(ProfileActivity.this, true);
        getProfileServiceProvider.GetProfile(userId, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((GetProfileModel) serviceResponse).getStatus());
                    String message = ((GetProfileModel) serviceResponse).getMessage();
                    ArrayList<GetProfileModel.User_data> mGetProfileData = ((GetProfileModel) serviceResponse).getUser_data();
                    if (Status == 200) {
                        //mAlert.onShowToastNotification(ProfileActivity.this, message);
                        edtName.setText(mGetProfileData.get(0).getName());
                        edtMobile.setText(mGetProfileData.get(0).getMobile());
                        edtCity.setText(mGetProfileData.get(0).getCity());
                        edtAddress.setText(mGetProfileData.get(0).getAddress());
                        edtPassword.setText(mGetProfileData.get(0).getPassword());

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

        if (getEditText.equalsIgnoreCase("Edit")) {
            btnEdit.setText("Save");
            edtName.setEnabled(true);
            edtMobile.setEnabled(false);
            edtCity.setEnabled(true);
            edtAddress.setEnabled(true);
            edtPassword.setEnabled(true);
        } else if (getEditText.equalsIgnoreCase("Save")) {

            if (edtName.getText().length() < 1) {
                edtName.setError("Please enter username");
            } else if (edtMobile.getText().length() < 10) {
                edtMobile.setError("Please enter valid phone number");
            } else if (edtCity.getText().length() < 1) {
                edtCity.setError("Please enter city");
            } else if (edtAddress.getText().length() < 1) {
                edtAddress.setError("Please enter address");
            } else if (edtPassword.getText().length() < 4) {
                edtPassword.setError("Please enter 4 digit password");
            } else {
                getMobile = edtMobile.getText().toString();
                getName = edtName.getText().toString();
                getCity = edtCity.getText().toString();
                getAddress = edtAddress.getText().toString();
                getPassword = edtPassword.getText().toString();

                CallUpdateApi(ExamApplication.onGetUserId(),getName,getCity, getAddress, getPassword);
            }
        }
    }

    private void CallUpdateApi(String getUserId, String getName, String getCity, String getAddress, String getPassword) {
        mAlert.onShowProgressDialog(ProfileActivity.this, true);
        updateProfileServiceProvider.CallUpdate(getUserId, getName, getCity, getAddress, getPassword, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((UpdateProfileModel) serviceResponse).getStatus());
                    String message = ((UpdateProfileModel) serviceResponse).getMessage();
                    ArrayList<UpdateProfileModel.User_data> mUpdateData = ((UpdateProfileModel) serviceResponse).getUser_data();
                    if (Status == 200) {
                        ExamApplication.onSaveLoginDetail(String.valueOf(mUpdateData.get(0).getUserId()), mUpdateData.get(0).getName(), mUpdateData.get(0).getMobile(),
                                mUpdateData.get(0).getCity(), mUpdateData.get(0).getAddress(), mUpdateData.get(0).getPassword());

                        edtName.setText(mUpdateData.get(0).getName());
                        edtMobile.setText(mUpdateData.get(0).getMobile());
                        edtCity.setText(mUpdateData.get(0).getCity());
                        edtAddress.setText(mUpdateData.get(0).getAddress());
                        edtPassword.setText(mUpdateData.get(0).getPassword());

                        edtName.setEnabled(false);
                        edtMobile.setEnabled(false);
                        edtCity.setEnabled(false);
                        edtAddress.setEnabled(false);
                        edtPassword.setEnabled(false);
                        btnEdit.setText("Edit");

                        mAlert.onShowToastNotification(ProfileActivity.this, message);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                ProfileActivity.this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

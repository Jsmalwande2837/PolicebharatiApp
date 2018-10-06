package com.solutionner.policebharatiapp.register;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.solutionner.policebharatiapp.MainActivity;
import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.buy.BuyActivity;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.AlertDialogs;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.PrintUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

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
    @InjectView(R.id.BtnRegister)
    Button BtnRegister;

    @InjectView(R.id.nestscrollmain)
    NestedScrollView nestscrollmain;
    @InjectView(R.id.relOtp)
    RelativeLayout relOtp;

    @InjectView(R.id.edtOtp1)
    EditText edtOtp1;
    @InjectView(R.id.edtOtp2)
    EditText edtOtp2;
    @InjectView(R.id.edtOtp3)
    EditText edtOtp3;
    @InjectView(R.id.edtOtp4)
    EditText edtOtp4;
    @InjectView(R.id.edtOtp5)
    EditText edtOtp5;
    @InjectView(R.id.edtOtp6)
    EditText edtOtp6;

    AlertDialogs mAlert;

    private static final String TAG = "PhoneAuth";
    private String generatedOtp = "";

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private FirebaseAuth firebaseAuth;

    RegistrationServiceProvider registrationServiceProvider;
    private String getName, getMobile, getCity, getAddress, getPassword;
    private String fullOtp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        init();
        setSupportActionBar(custom_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        custom_toolbar.setContentInsetStartWithNavigation(0);
    }

    private void init() {
        mAlert = AlertDialogs.getInstance();
        mAlert.firebaseAnalytics(this.getClass().getSimpleName());
        firebaseAuth = FirebaseAuth.getInstance();
        registrationServiceProvider = new RegistrationServiceProvider(this);

        //for font
        edtName.setTypeface(ExamApplication.getEnglishFont());
        edtPassword.setTypeface(ExamApplication.getEnglishFont());
        edtCity.setTypeface(ExamApplication.getEnglishFont());
        edtAddress.setTypeface(ExamApplication.getEnglishFont());
        edtPassword.setTypeface(ExamApplication.getEnglishFont());
        BtnRegister.setTypeface(ExamApplication.getEnglishFont());

        //edtName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});


        edtOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count > 0) {
                    edtOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    edtOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    edtOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    edtOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    edtOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.BtnRegister)
    public void Register() {

        getName = edtName.getText().toString();
        getCity = edtCity.getText().toString();
        getAddress = edtAddress.getText().toString();
        getPassword = edtPassword.getText().toString();


        if (edtName.getText().length() < 4) {
            edtName.setError("Please enter username");
        } else if (edtMobile.getText().length() < 10) {
            edtMobile.setError("Please enter valid phone number");
        } else if (getCity.length() < 3) {
            edtCity.setError("Please enter city");
        } else if (getAddress.length() < 3) {
            edtAddress.setError("Please enter address");
        } else if (getPassword.length() < 4) {
            edtPassword.setError("Please enter 4 digit password");
        } else {

            getMobile = edtMobile.getText().toString();

            getName = edtName.getText().toString();
            getMobile = edtMobile.getText().toString();
            getCity = edtCity.getText().toString();
            getAddress = edtAddress.getText().toString();
            getPassword = edtPassword.getText().toString();

            CallRegistrationApi(getName, getMobile, getCity, getAddress, getPassword);
        }
    }

    private void sendCode(String getMobile) {

        setUpVerificatonCallbacks();

        if (getMobile.contains("+")) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    getMobile, 60, java.util.concurrent.TimeUnit.SECONDS,
                    this, verificationCallbacks);
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + getMobile,
                    60,
                    java.util.concurrent.TimeUnit.SECONDS,
                    this, verificationCallbacks);
        }

    }

    private void setUpVerificatonCallbacks() {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //Toast.makeText(RegistrationActivity.this, "Invalid Credentials: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Invalid Credentials: " + e.getLocalizedMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(RegistrationActivity.this, "Sms Quota Exceeded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                phoneVerificationId = verificationId;
                Toast.makeText(RegistrationActivity.this, "Verification code has been send on your number", Toast.LENGTH_SHORT).show();
                nestscrollmain.setVisibility(View.GONE);
                relOtp.setVisibility(View.VISIBLE);
            }
        };
    }

    private void CallRegistrationApi(String getName, final String getMobile, String getCity, String getAddress, String getPassword) {
        mAlert.onShowProgressDialog(RegistrationActivity.this, true);
        registrationServiceProvider.CallRegistration(getName, getMobile, getCity, getAddress, getPassword, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = (((RegistrationModel) serviceResponse).getStatus());
                    String message = ((RegistrationModel) serviceResponse).getMessage();
                    //ArrayList<RegistrationModel.Data> mLoginData = ((RegistrationModel) serviceResponse).getData();

                    RegistrationModel.Data mLoginDatas = ((RegistrationModel) serviceResponse).getData();

                    if (Status == 200) {
                        sendCode(mLoginDatas.getMobile());
                        ExamApplication.onSaveLoginDetail(String.valueOf(mLoginDatas.getUserId()), mLoginDatas.getName(), mLoginDatas.getMobile(),
                                mLoginDatas.getCity(), mLoginDatas.getAddress(), mLoginDatas.getPassword());
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

    @OnClick(R.id.btnConfirm)
    public void Confirm() {

        String otp1 = edtOtp1.getText().toString();
        String otp2 = edtOtp2.getText().toString();
        String otp3 = edtOtp3.getText().toString();
        String otp4 = edtOtp4.getText().toString();
        String otp5 = edtOtp5.getText().toString();
        String otp6 = edtOtp6.getText().toString();

        fullOtp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

        try {
            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(phoneVerificationId, fullOtp);
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            if (!fullOtp.equals("")) {
                                Toast.makeText(RegistrationActivity.this, "OTP Match", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                //Toast.makeText(RegistrationActivity.this, "Invalid Code Verification", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.BtnCancel)
    public void Cancel() {
        nestscrollmain.setVisibility(View.VISIBLE);
        relOtp.setVisibility(View.GONE);
    }

    @OnClick(R.id.relSend)
    public void Resend() {

        setUpVerificatonCallbacks();

        if (getMobile.contains("+")) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(getMobile, 60,
                    java.util.concurrent.TimeUnit.SECONDS, this, verificationCallbacks);
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber("+" + getMobile, 60,
                    java.util.concurrent.TimeUnit.SECONDS, this, verificationCallbacks);
        }
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                Toast.makeText(context, "broadcast receiver work", Toast.LENGTH_SHORT).show();
                Log.e("message", "" + message);

                String code = parseCode(message);
                Log.e("code", "" + code);

                generatedOtp = code;
                String first = generatedOtp.substring(0, 1);
                String second = generatedOtp.substring(1, 2);
                String third = generatedOtp.substring(2, 3);
                String four = generatedOtp.substring(3, 4);
                String five = generatedOtp.substring(4, 5);
                String six = generatedOtp.substring(5, 6);
                edtOtp1.setText(first);
                edtOtp2.setText(second);
                edtOtp3.setText(third);
                edtOtp4.setText(four);
                edtOtp5.setText(five);
                edtOtp6.setText(six);
            }
        }
    };

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{6}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                RegistrationActivity.this.overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

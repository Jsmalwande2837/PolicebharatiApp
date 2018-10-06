package com.solutionner.policebharatiapp.login;

import android.content.Context;

import com.solutionner.policebharatiapp.register.RegistrationModel;
import com.solutionner.policebharatiapp.register.RegistrationService;
import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.APIServiceFactory;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jitesh on 3/15/18.
 */

public class LoginServiceProvider {
    private final LoginService loginService;

    public LoginServiceProvider(Context context) {
        loginService = APIServiceFactory.createService(LoginService.class, context);
    }

    public void CallLogin(String mobile, String password, final APICallback apiCallback) {
        Call<LoginModel> call = null;
        call = loginService.loginApi(mobile, password);
        String url = call.request().url().toString();

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 200) {
                    apiCallback.onSuccess(response.body());
                } else if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 404) {
                    apiCallback.onSuccess(response.body());
                } else {
                    BaseServiceResponseModel model = ErrorUtils.parseError(response);
                    apiCallback.onFailure(model, response.errorBody());
                    // apiCallback.onFailure(response.body(), response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
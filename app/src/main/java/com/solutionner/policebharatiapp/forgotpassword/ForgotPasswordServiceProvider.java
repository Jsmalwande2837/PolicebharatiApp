package com.solutionner.policebharatiapp.forgotpassword;

import android.content.Context;

import com.solutionner.policebharatiapp.profile.getprofile.GetProfileModel;
import com.solutionner.policebharatiapp.profile.getprofile.GetProfileService;
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

public class ForgotPasswordServiceProvider {
    private final ForgotPasswordService forgotPasswordService;

    public ForgotPasswordServiceProvider(Context context) {
        forgotPasswordService = APIServiceFactory.createService(ForgotPasswordService.class, context);
    }

    public void GetProfile(String name, String mobile, final APICallback apiCallback) {
        Call<ForgotPasswordModel> call = null;
        call = forgotPasswordService.ForgotPasswordApi(name, mobile);
        String url = call.request().url().toString();

        call.enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 200) {
                    apiCallback.onSuccess(response.body());
                } else if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 500) {
                    apiCallback.onSuccess(response.body());
                } else {
                    BaseServiceResponseModel model = ErrorUtils.parseError(response);
                    apiCallback.onFailure(model, response.errorBody());
                    // apiCallback.onFailure(response.body(), response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
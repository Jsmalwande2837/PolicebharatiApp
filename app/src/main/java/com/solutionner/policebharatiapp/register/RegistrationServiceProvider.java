package com.solutionner.policebharatiapp.register;

import android.content.Context;

import com.solutionner.policebharatiapp.submitrecord.SubmitRecordModel;
import com.solutionner.policebharatiapp.submitrecord.SubmitRecordService;
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

public class RegistrationServiceProvider {
    private final RegistrationService registrationService;

    public RegistrationServiceProvider(Context context) {
        registrationService = APIServiceFactory.createService(RegistrationService.class, context);
    }

    public void CallRegistration(String name, String mobile, String city, String address, String password, final APICallback apiCallback) {
        Call<RegistrationModel> call = null;
        call = registrationService.registration(name, mobile, city, address, password);
        String url = call.request().url().toString();

        call.enqueue(new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {
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
            public void onFailure(Call<RegistrationModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
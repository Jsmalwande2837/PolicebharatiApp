package com.solutionner.policebharatiapp.profile.updateprofile;

import android.content.Context;

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

public class UpdateProfileServiceProvider {
    private final UpdateProfileService updateProfileService;

    public UpdateProfileServiceProvider(Context context) {
        updateProfileService = APIServiceFactory.createService(UpdateProfileService.class, context);
    }

    public void CallUpdate(String name, String mobile, String city, String address, String password, final APICallback apiCallback) {
        Call<UpdateProfileModel> call = null;
        call = updateProfileService.UpdateApi(name, mobile, city, address, password);
        String url = call.request().url().toString();

        call.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {
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
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
package com.solutionner.policebharatiapp.profile.getprofile;

import android.content.Context;

import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileModel;
import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileService;
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

public class GetProfileServiceProvider {
    private final GetProfileService getProfileService;

    public GetProfileServiceProvider(Context context) {
        getProfileService = APIServiceFactory.createService(GetProfileService.class, context);
    }

    public void GetProfile(String userId, final APICallback apiCallback) {
        Call<GetProfileModel> call = null;
        call = getProfileService.GetProfileApi(userId);
        String url = call.request().url().toString();

        call.enqueue(new Callback<GetProfileModel>() {
            @Override
            public void onResponse(Call<GetProfileModel> call, Response<GetProfileModel> response) {
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
            public void onFailure(Call<GetProfileModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
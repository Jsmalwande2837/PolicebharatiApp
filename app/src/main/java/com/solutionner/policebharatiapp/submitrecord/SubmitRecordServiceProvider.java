package com.solutionner.policebharatiapp.submitrecord;

import android.content.Context;

import com.solutionner.policebharatiapp.utils.APICallback;
import com.solutionner.policebharatiapp.utils.APIServiceFactory;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;
import com.solutionner.policebharatiapp.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shivraj on 3/15/18.
 */

public class SubmitRecordServiceProvider {
    private final SubmitRecordService submitRecordService;

    public SubmitRecordServiceProvider(Context context) {
        submitRecordService = APIServiceFactory.createService(SubmitRecordService.class, context);
    }

    public void submitRecord(String ratings,String comments,String feedback_by, final APICallback apiCallback) {
        Call<SubmitRecordModel> call = null;
        call = submitRecordService.submitRecord(ratings,comments,feedback_by);
        String url = call.request().url().toString();

        call.enqueue(new Callback<SubmitRecordModel>() {
            @Override
            public void onResponse(Call<SubmitRecordModel> call, Response<SubmitRecordModel> response) {
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
            public void onFailure(Call<SubmitRecordModel> call, Throwable t) {
                apiCallback.onFailure(null, null);
            }
        });
    }
}
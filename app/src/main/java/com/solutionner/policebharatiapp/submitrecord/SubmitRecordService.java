package com.solutionner.policebharatiapp.submitrecord;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shivraj on 3/15/18.
 */

public interface SubmitRecordService {
    @POST("submitRatings")
    @FormUrlEncoded
    Call<SubmitRecordModel> submitRecord(@Field("ratings") String ratings,
                                         @Field("comments") String comments,
                                         @Field("feedback_by") String feedback_by);
}

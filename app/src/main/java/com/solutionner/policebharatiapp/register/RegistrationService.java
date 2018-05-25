package com.solutionner.policebharatiapp.register;

import com.solutionner.policebharatiapp.submitrecord.SubmitRecordModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jitesh on 3/15/18.
 */

public interface RegistrationService {
    @POST("submitRatings")
    @FormUrlEncoded
    Call<RegistrationModel> registration(@Field("Name") String Name,
                                         @Field("Mobile") String Mobile,
                                         @Field("City") String City,
                                         @Field("Address") String Address,
                                         @Field("Password") String Password);
}

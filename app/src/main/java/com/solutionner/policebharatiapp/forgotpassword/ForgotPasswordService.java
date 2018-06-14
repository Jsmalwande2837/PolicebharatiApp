package com.solutionner.policebharatiapp.forgotpassword;

import com.solutionner.policebharatiapp.profile.getprofile.GetProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jitesh on 3/15/18.
 */

public interface ForgotPasswordService {
    @POST("forgot_password.php")
    @FormUrlEncoded
    Call<ForgotPasswordModel> ForgotPasswordApi(@Field("Mobile") String Mobile);
}

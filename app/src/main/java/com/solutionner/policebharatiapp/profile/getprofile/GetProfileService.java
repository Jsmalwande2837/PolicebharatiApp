package com.solutionner.policebharatiapp.profile.getprofile;

import com.solutionner.policebharatiapp.profile.updateprofile.UpdateProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jitesh on 3/15/18.
 */

public interface GetProfileService {
    @POST("fetch_user_profile.php")
    @FormUrlEncoded
    Call<GetProfileModel> GetProfileApi(@Field("UserId") String UserId);
}

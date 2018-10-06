package com.solutionner.policebharatiapp.profile.updateprofile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jitesh on 3/15/18.
 */

public interface UpdateProfileService {
    @POST("updateprofile.php")
    @FormUrlEncoded
    Call<UpdateProfileModel> UpdateApi(@Field("UserId") String UserId,
                                       @Field("Name") String Name,
                                       @Field("City") String City,
                                       @Field("Address") String Address,
                                       @Field("Password") String Password);
}

package com.solutionner.policebharatiapp.login;

import com.solutionner.policebharatiapp.register.RegistrationModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Jitesh on 3/15/18.
 */

public interface LoginService {
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> loginApi(@Field("Mobile") String Mobile,
                              @Field("Password") String Password);
}

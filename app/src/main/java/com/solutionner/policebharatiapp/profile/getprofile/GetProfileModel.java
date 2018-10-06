package com.solutionner.policebharatiapp.profile.getprofile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jitesh on 3/15/18.
 */

public class GetProfileModel extends BaseServiceResponseModel {


    @Expose
    @SerializedName("user_data")
    private ArrayList<User_data> user_data;

    public ArrayList<User_data> getUser_data() {
        return user_data;
    }

    public void setUser_data(ArrayList<User_data> user_data) {
        this.user_data = user_data;
    }

    public static class User_data {
        @Expose
        @SerializedName("Password")
        private String Password;
        @Expose
        @SerializedName("Address")
        private String Address;
        @Expose
        @SerializedName("City")
        private String City;
        @Expose
        @SerializedName("Mobile")
        private String Mobile;
        @Expose
        @SerializedName("Name")
        private String Name;
        @Expose
        @SerializedName("UserId")
        private String UserId;

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }
    }
}
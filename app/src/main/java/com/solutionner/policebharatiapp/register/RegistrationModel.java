package com.solutionner.policebharatiapp.register;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.solutionner.policebharatiapp.utils.BaseServiceResponseModel;

/**
 * Created by Jitesh on 3/15/18.
 */

public class RegistrationModel extends BaseServiceResponseModel {


    @Expose
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @Expose
        @SerializedName("UserId")
        private int UserId;
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

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int userId) {
            UserId = userId;
        }

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
    }
}
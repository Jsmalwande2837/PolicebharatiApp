package com.solutionner.policebharatiapp.sqlitedatabase;

public class AddHospitalModel {
    public int id;
    public String hospitalName;
    public String hospitalUsername;
    public String hospitalMobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalUsername() {
        return hospitalUsername;
    }

    public void setHospitalUsername(String hospitalUsername) {
        this.hospitalUsername = hospitalUsername;
    }

    public String getHospitalMobile() {
        return hospitalMobile;
    }

    public void setHospitalMobile(String hospitalMobile) {
        this.hospitalMobile = hospitalMobile;
    }

    @Override
    public String toString() {
        return hospitalName;
    }
}

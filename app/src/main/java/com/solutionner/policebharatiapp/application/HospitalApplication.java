package com.solutionner.policebharatiapp.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.multidex.MultiDexApplication;

import com.android.volley.toolbox.ImageLoader;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.solutionner.policebharatiapp.R;

import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;


/**
 * Created by USER1 on 12/12/2017.
 */

@ReportsCrashes(mailTo = "malwandejitesh@gmail.com",
        customReportContent = {ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT},
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)

public class HospitalApplication extends MultiDexApplication {

    private static Context mContext;
    private static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        //ACRA.init(this);
        mContext = getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }


    public static Context getContext() {
        return mContext;
    }


    public static void onSetStarForFirstScreen(float q1, float q2, float q3, float q4, float q5, String edtFirst) {
        SharedPreferences.Editor lEditor = mSharedPreferences.edit();
        lEditor.putFloat("Q1", q1);
        lEditor.putFloat("Q2", q2);
        lEditor.putFloat("Q3", q3);
        lEditor.putFloat("Q4", q4);
        lEditor.putFloat("Q5", q5);
        lEditor.putString("edtFirst", edtFirst);
        lEditor.commit();
    }

    public static void onSetStarForSecondScreen(float q6, float q7, float q8, float q9, float q10, String edtSecond) {
        SharedPreferences.Editor lEditor = mSharedPreferences.edit();
        lEditor.putFloat("Q6", q6);
        lEditor.putFloat("Q7", q7);
        lEditor.putFloat("Q8", q8);
        lEditor.putFloat("Q9", q9);
        lEditor.putFloat("Q10", q10);
        lEditor.putString("edtSecond", edtSecond);
        lEditor.commit();
    }

    public static void onSetStarForThirdScreen(float q11, float q12, float q13, float q14, float q15, String edtThird) {
        SharedPreferences.Editor lEditor = mSharedPreferences.edit();
        lEditor.putFloat("Q11", q11);
        lEditor.putFloat("Q12", q12);
        lEditor.putFloat("Q13", q13);
        lEditor.putFloat("Q14", q14);
        lEditor.putFloat("Q15", q15);
        lEditor.putString("edtThird", edtThird);
        lEditor.commit();
    }

    public static void onSetStarForFourthScreen(float q16, float q17, float q18, String edtFour) {
        SharedPreferences.Editor lEditor = mSharedPreferences.edit();
        lEditor.putFloat("Q16", q16);
        lEditor.putFloat("Q17", q17);
        lEditor.putFloat("Q18", q18);
        lEditor.putString("edtFour", edtFour);
        lEditor.commit();
    }

    public static void onSetStarForFifthScreen(float q19, float q20, float q21, float q22) {
        SharedPreferences.Editor lEditor = mSharedPreferences.edit();

        lEditor.putFloat("Q19", q19);
        lEditor.putFloat("Q20", q20);
        lEditor.putFloat("Q21", q21);
        lEditor.putFloat("Q22", q22);

        lEditor.commit();
    }

    public static Float onGetQ1Star() {
        return mSharedPreferences.getFloat("Q1", 0);
    }

    public static Float onGetQ2Star() {
        return mSharedPreferences.getFloat("Q2", 0);
    }

    public static Float onGetQ3Star() {
        return mSharedPreferences.getFloat("Q3", 0);
    }

    public static Float onGetQ4Star() {
        return mSharedPreferences.getFloat("Q4", 0);
    }

    public static Float onGetQ5Star() {
        return mSharedPreferences.getFloat("Q5", 0);
    }

    public static Float onGetQ6Star() {
        return mSharedPreferences.getFloat("Q6", 0);
    }

    public static Float onGetQ7Star() {
        return mSharedPreferences.getFloat("Q7", 0);
    }

    public static Float onGetQ8Star() {
        return mSharedPreferences.getFloat("Q8", 0);
    }

    public static Float onGetQ9Star() {
        return mSharedPreferences.getFloat("Q9", 0);
    }

    public static Float onGetQ10Star() {
        return mSharedPreferences.getFloat("Q10", 0);
    }

    public static Float onGetQ11Star() {
        return mSharedPreferences.getFloat("Q11", 0);
    }

    public static Float onGetQ12Star() {
        return mSharedPreferences.getFloat("Q12", 0);
    }

    public static Float onGetQ13Star() {
        return mSharedPreferences.getFloat("Q13", 0);
    }

    public static Float onGetQ14Star() {
        return mSharedPreferences.getFloat("Q14", 0);
    }

    public static Float onGetQ15Star() {
        return mSharedPreferences.getFloat("Q15", 0);
    }

    public static Float onGetQ16Star() {
        return mSharedPreferences.getFloat("Q16", 0);
    }

    public static Float onGetQ17Star() {
        return mSharedPreferences.getFloat("Q17", 0);
    }

    public static Float onGetQ18Star() {
        return mSharedPreferences.getFloat("Q18", 0);
    }

    public static Float onGetQ19Star() {
        return mSharedPreferences.getFloat("Q19", 0);
    }

    public static Float onGetQ20Star() {
        return mSharedPreferences.getFloat("Q20", 0);
    }

    public static Float onGetQ21Star() {
        return mSharedPreferences.getFloat("Q21", 0);
    }

    public static Float onGetQ22Star() {
        return mSharedPreferences.getFloat("Q22", 0);
    }

    public static String onGetFirstEdtValue() {
        return mSharedPreferences.getString("edtFirst", "");
    }

    public static String onGetSecondEdtValue() {
        return mSharedPreferences.getString("edtSecond", "");
    }

    public static String onGetThirdEdtValue() {
        return mSharedPreferences.getString("edtThird", "");
    }

    public static String onGetFourEdtValue() {
        return mSharedPreferences.getString("edtFour", "");
    }

    public static void onSaveFeedbackData(String feedbackby, String hosptalId) {

        SharedPreferences.Editor edt = mSharedPreferences.edit();

        edt.putString("feedbackby", feedbackby);
        edt.putString("hospitalId", hosptalId);
        edt.commit();
    }

    public static String onGetFeedbackBy() {
        return mSharedPreferences.getString("feedbackby", "");
    }

    public static String onGetHospitalId() {
        return mSharedPreferences.getString("hospitalId", "");
    }
}
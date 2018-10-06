package com.solutionner.policebharatiapp.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.solutionner.policebharatiapp.R;

import org.acra.ACRA;
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

public class ExamApplication extends MultiDexApplication {

    private static Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static Typeface english, marathi;

    @Override
    public void onCreate() {
        super.onCreate();
        //ACRA.init(this);
        mContext = getApplicationContext();
        mSharedPreferences = mContext.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        english = Typeface.createFromAsset(getAssets(), "fonts/nunito_sans_regular.ttf");
        marathi = Typeface.createFromAsset(getAssets(), "fonts/marathi_font_file.ttf");
    }

    public static Context getContext() {
        return mContext;
    }


    public static void onSaveLoginDetail(String id, String name, String mobile, String city, String address, String password) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString("ID", id);
        editor.putString("NAME", name);
        editor.putString("MOBILE", mobile);
        editor.putString("CITY", city);
        editor.putString("ADDRESS", address);
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public static String onGetUserId() {
        return mSharedPreferences.getString("ID", "");
    }

    public static String onGetName() {
        return mSharedPreferences.getString("NAME", "");
    }

    public static String onGetMobile() {
        return mSharedPreferences.getString("MOBILE", "");
    }

    public static String onGetCity() {
        return mSharedPreferences.getString("CITY", "");
    }

    public static String onGetAddress() {
        return mSharedPreferences.getString("ADDRESS", "");
    }

    public static String onGetPassword() {
        return mSharedPreferences.getString("PASSWORD", "");
    }


    public static Typeface getEnglishFont() {
        return english;
    }

    public static Typeface getMarathiFont() {
        return marathi;
    }
}
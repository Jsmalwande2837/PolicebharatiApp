package com.solutionner.policebharatiapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.register.RegistrationActivity;

import butterknife.OnClick;

public class AlertDialogs {

    private ProgressDialog mDialog;
    public static AlertDialogs mInstance;

    public static AlertDialogs getInstance() {
        if (mInstance == null) {
            mInstance = new AlertDialogs();
        }
        return mInstance;
    }

    public void onShowProgressDialog(Activity activity, boolean isShow) {

        try {
            if (isShow) {
                mDialog = ProgressDialog.show(activity, "", "Loading...", true);
                mDialog.show();
            } else {
                if (mDialog.isShowing())
                    mDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }


    public void onShowToastNotification(Activity activity, String msg) {
        Toast ltoast = Toast.makeText(activity, msg, Toast.LENGTH_LONG);
        ltoast.show();
    }

    public void onHideKeyBoard(Activity mActivity) {
        final InputMethodManager imm = (InputMethodManager) mActivity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
    }

    public void firebaseAnalytics(String activityName) {
        FirebaseCrash.logcat(Log.ERROR, activityName, "firebase crash analytics");
        Throwable ex = null;
        FirebaseCrash.report(ex);
    }

    public void showAlert(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure to exit the app?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finishAffinity();
                        activity.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

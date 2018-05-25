package com.solutionner.policebharatiapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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
}

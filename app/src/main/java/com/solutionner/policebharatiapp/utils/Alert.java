package com.solutionner.policebharatiapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Alert {
    public static Alert mInstance;

    public static Alert getInstance() {
        if (mInstance == null) {
            mInstance = new Alert();
        }
        return mInstance;
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

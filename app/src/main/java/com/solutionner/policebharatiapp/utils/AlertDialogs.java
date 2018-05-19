//package com.solutionner.policebharatiapp.utils;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.IntentSender;
//import android.location.Address;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Toast;
//
//import java.util.List;
//
//public class AlertDialogs {
//
//    private ProgressDialog mDialog;
//    public static AlertDialogs mInstance;
//    final static int REQUEST_LOCATION = 199;
//    private String mAddress;
//    List<Address> addresses;
//
//    public static AlertDialogs getInstance() {
//        if (mInstance == null) {
//            mInstance = new AlertDialogs();
//        }
//        return mInstance;
//    }
//
//    public void onShowProgressDialog(Activity activity, boolean isShow) {
//
//        try {
//            if (isShow) {
//                mDialog = ProgressDialog.show(activity, "", "Loading...", true);
//                mDialog.show();
//            } else {
//                if (mDialog.isShowing())
//                    mDialog.dismiss();
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//
//    public void onShowToastNotification(Activity activity, String msg) {
//        Toast ltoast = Toast.makeText(activity, msg, Toast.LENGTH_LONG);
//        ltoast.show();
//    }
//
//    public void onHideKeyBoard(Activity mActivity) {
//        final InputMethodManager imm = (InputMethodManager) mActivity
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
//    }
//
//    //Alert dialog for call admin
//    public void BuyerAlert(final Activity activity) {
//
//    }
//
////    //For checking gps on or not
////    public void gpschecAlert(Activity activity) {
////        // Todo Location Already on  ... start
////        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
////
////        if (!hasGPSDevice(activity)) {
////            Toast.makeText(activity, "Gps not Supported", Toast.LENGTH_SHORT).show();
////        }
////
////        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(activity)) {
////            Log.e("Location", "Gps not enabled");
////            //enableLoc(activity);
////
////            GPSLocation gps = new GPSLocation(activity);
////            // check if GPS enabled
////            if (!gps.canGetLocation()) {
////                gps.showSettingsAlert();
////            }
////        } else {
////            Log.e("Location", "Gps enabled");
////            //getLocation(activity);
////        }
////    }
//
//
//    private boolean hasGPSDevice(Context context) {
//        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        if (mgr == null)
//            return false;
//        final List<String> providers = mgr.getAllProviders();
//        if (providers == null)
//            return false;
//        return providers.contains(LocationManager.GPS_PROVIDER);
//    }
//
////    public void showAlert(final Activity activity) {
////        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
////        builder.setMessage("Are you sure to exit the app?")
////                .setCancelable(false)
////                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        activity.finishAffinity();
////                        activity.finish();
////                    }
////                })
////                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.cancel();
////                    }
////                });
////        AlertDialog alertDialog = builder.create();
////        alertDialog.show();
////    }
//
//}
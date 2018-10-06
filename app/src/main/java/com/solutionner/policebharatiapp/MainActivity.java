package com.solutionner.policebharatiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.fragment.BlankFragment;
import com.solutionner.policebharatiapp.fragment.HomeFragment;
import com.solutionner.policebharatiapp.login.LoginActivity;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.utils.AlertDialogs;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BlankFragment.OnFragmentInteractionListener {

    AlertDialogs mAlert;
    AlertDialogs alertDialogs = new AlertDialogs();
    public TextView txtEmailId;
    public TextView txtUserName;
    public CircleImageView circularUserPic;
    public LinearLayout layoutHeader;
    public DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


        layoutHeader = header.findViewById(R.id.layoutHeader);
        circularUserPic = header.findViewById(R.id.circularUserPic);
        txtUserName = header.findViewById(R.id.txtUserName);
        txtEmailId = header.findViewById(R.id.txtEmailId);

        txtUserName.setText(ExamApplication.onGetName());
        txtEmailId.setText(ExamApplication.onGetMobile());

        layoutHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        });
        mAlert = AlertDialogs.getInstance();
        onFragmentTrans(new BlankFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //CallGetDriverProfile(TranspooApplication.onGetDriverId());
    }

    /*private void CallGetDriverProfile(String uid) {
        mAlert.onShowProgressDialog(this, false);
        mGetProfileServiceProvider.callGetProfile(uid, new APICallback() {
            @Override
            public <T> void onSuccess(T serviceResponse) {
                try {
                    int Status = ((GetProfileModel) serviceResponse).getStatus();
                    String message = ((GetProfileModel) serviceResponse).getMessage();
                    ArrayList<GetProfileModel.Data> mGetProfileArray = ((GetProfileModel) serviceResponse).data;
                    if (Status == 200) {
                        txtHeaderUsername.setText(mGetProfileArray.get(0).getFirst_name());
                        txtHeaderMobile.setText(mGetProfileArray.get(0).getPhone());
                    } else {
                        mAlert.onShowToastNotification(MainActivity.this, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mAlert.onShowProgressDialog(MainActivity.this, false);
                }
            }

            @Override
            public <T> void onFailure(T apiErrorModel, T extras) {
                try {

                    if (apiErrorModel != null) {
                        PrintUtil.showToast(MainActivity.this, ((BaseServiceResponseModel) apiErrorModel).getMessage());
                    } else {
                        PrintUtil.showNetworkAvailableToast(MainActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.showNetworkAvailableToast(MainActivity.this);
                } finally {
                    mAlert.onShowProgressDialog(MainActivity.this, false);
                }
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            alertDialogs.showAlert(MainActivity.this);
        }
    }


    /*public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        String title = null;

        //initializing the fragment object which is selected
        switch (itemId) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                onFragmentTrans(new BlankFragment());
                break;

            case R.id.nav_about_us:
                //startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
                break;

            case R.id.nav_share_app:
                OnShare();
                break;

            case R.id.nav_logout:
                showLogoutAlert();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Playstore Application link");
        startActivity(Intent.createChooser(intent, "Share App with Friends!"));
    }

    private void onFragmentTrans(BlankFragment framgent) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home, framgent).commit();
    }

    public void onFragmentInteraction(Uri uri) {
    }

    public void showLogoutAlert() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        ExamApplication.onSaveLoginDetail("", "", "", "", "", "");
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
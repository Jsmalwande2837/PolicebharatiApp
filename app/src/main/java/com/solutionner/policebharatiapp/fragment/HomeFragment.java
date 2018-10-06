package com.solutionner.policebharatiapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solutionner.policebharatiapp.R;
import com.solutionner.policebharatiapp.aboutus.AboutUsActivity;
import com.solutionner.policebharatiapp.adhyayansamgri.AdhyayanSamagriActivity;
import com.solutionner.policebharatiapp.application.ExamApplication;
import com.solutionner.policebharatiapp.login.LoginActivity;
import com.solutionner.policebharatiapp.profile.ProfileActivity;
import com.solutionner.policebharatiapp.term.TermAndCondActivity;
import com.solutionner.policebharatiapp.utils.AlertDialogs;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class HomeFragment extends Fragment {
    @InjectView(R.id.txtHamburger)
    TextView txtHamburger;
    @InjectView(R.id.relDrawerLayout)
    RelativeLayout relDrawerLayout;
    @InjectView(R.id.main)
    RelativeLayout main;
    @InjectView(R.id.relativeProfile)
    RelativeLayout relativeProfile;
    @InjectView(R.id.relLogout)
    RelativeLayout relLogout;

    AlertDialogs mAlert;
    private String mParam1;
    private String mParam2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_exam, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    private void init() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

   /* private void onFragmentTrans(android.support.v4.app.Fragment framgent) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_home, framgent).commit();
    }
*/
    @OnClick(R.id.txtHamburger)
    public void ShowDrawer() {
        relDrawerLayout.setVisibility(View.VISIBLE);
        getActivity().overridePendingTransition(R.anim.left_out, R.anim.right_in);
    }

    @OnClick(R.id.txtBackArrow)
    public void CloseDrawer() {
        relDrawerLayout.setVisibility(View.GONE);

    }

    @OnClick(R.id.main)
    public void MainClick() {
        relDrawerLayout.setVisibility(View.GONE);

    }

    @OnClick(R.id.btnBuyNow)
    public void Buy() {
        startActivity(new Intent(getActivity(), AdhyayanSamagriActivity.class));
    }


    @OnClick(R.id.relTemAndCond)
    public void TermAndCondition() {
        startActivity(new Intent(getActivity(), TermAndCondActivity.class));
    }

    @OnClick(R.id.relAboutUs)
    public void AboutUs() {
        startActivity(new Intent(getActivity(), AboutUsActivity.class));
    }

    @OnClick(R.id.relativeProfile)
    public void Profile() {
        startActivity(new Intent(getActivity(), ProfileActivity.class));
    }

    @OnClick(R.id.relLogout)
    public void Logout() {
        ShowAlert(getActivity());
    }

    private void ShowAlert(final Activity mContext) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        ExamApplication.onSaveLoginDetail("", "", "", "", "", "");
                        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog dialog = alertDialog.create();
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        dialog.show();
    }
}
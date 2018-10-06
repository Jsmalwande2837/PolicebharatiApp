package com.solutionner.policebharatiapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class BlankFragment extends Fragment {

    AlertDialogs mAlert;
    private String mParam1;
    private String mParam2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.btnBuyNow)
    public void Buy() {
        startActivity(new Intent(getActivity(), AdhyayanSamagriActivity.class));
    }

}
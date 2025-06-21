package com.SAAH.javaprojecttolearn;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    private static final String ARG_USER_NAME = "user_name";

    public String userName;

    public TextView tvUserName;
    public CheckBox checkboxConfirm;
    public Button btnContinue;

    public interface OnFragment3InteractionListener {
        void onCheckboxChanged(boolean isChecked);
    }

    private OnFragment3InteractionListener callback;

    public Fragment3() {

    }


    public static Fragment3 newInstance(String userName) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3InteractionListener) {
            callback = (OnFragment3InteractionListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnFragment3InteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_USER_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tvUserName = view.findViewById(R.id.tv_user_name);
        checkboxConfirm = view.findViewById(R.id.checkbox_confirm);
        btnContinue = view.findViewById(R.id.btn_continue);

        tvUserName.setText("Welcome, " + userName);

        btnContinue.setEnabled(false);
        btnContinue.setText("Continue");

        checkboxConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (callback != null) {
                callback.onCheckboxChanged(isChecked);
            }
            btnContinue.setEnabled(isChecked);
            btnContinue.setText(isChecked ? "Finish" : "Continue");
        });
        btnContinue.setOnClickListener(v -> {
            if (checkboxConfirm.isChecked()) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}


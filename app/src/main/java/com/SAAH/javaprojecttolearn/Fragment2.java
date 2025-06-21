package com.SAAH.javaprojecttolearn;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.SAAH.javaprojecttolearn.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private OnFragment2InteractionListener callback;

    public interface OnFragment2InteractionListener {
        void onContinueClicked(String name, String email, String gender, String ageOrUniversity);
    }

    public Fragment2() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2InteractionListener) {
            callback = (OnFragment2InteractionListener) context;
        } else {
            throw new RuntimeException(context
                    + " must implement OnFragment2InteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);

        String[] genders = {"Male", "Female", "Other"};
        android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                genders
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerGender.setAdapter(adapter);

        binding.btnContinue.setOnClickListener(v -> {
            String name = binding.etName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String gender = binding.spinnerGender.getSelectedItem().toString();
            String ageOrUniversity = binding.etAgeOrUniversity.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            callback.onContinueClicked(name, email, gender, ageOrUniversity);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

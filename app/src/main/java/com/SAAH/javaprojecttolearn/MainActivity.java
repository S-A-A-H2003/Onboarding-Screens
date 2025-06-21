package com.SAAH.javaprojecttolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        Fragment2.OnFragment2InteractionListener,
        Fragment3.OnFragment3InteractionListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            new Handler(Looper.getMainLooper()).post(() -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Fragment1())
                        .commit();
            });

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Fragment2())
                        .commit();
            }, 3000);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new Fragment2())
                    .commit();
        }

        @Override
        public void onContinueClicked(String name, String email, String gender, String age) {

            Fragment3 fragment3 = Fragment3.newInstance(name);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment3)
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void onCheckboxChanged(boolean isChecked) {
            Toast.makeText(this, "Checkbox is " + (isChecked ? "checked" : "unchecked"), Toast.LENGTH_SHORT).show();
        }
}


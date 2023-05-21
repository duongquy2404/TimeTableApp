package com.duongnq.timetableapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.duongnq.timetableapp.R;
import com.duongnq.timetableapp.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    public static final String
            KEY_SEVEN_DAYS_SETTING = "sevendays";
    public static final String KEY_SCHOOL_WEBSITE_SETTING = "schoolwebsite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
package com.bakribrahim.newsapp.UI;

import android.os.Bundle;

import com.bakribrahim.newsapp.R;

import androidx.preference.PreferenceFragmentCompat;

public class settingfragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting, rootKey);
    }
}
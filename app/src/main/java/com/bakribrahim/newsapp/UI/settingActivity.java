package com.bakribrahim.newsapp.UI;

import android.os.Bundle;

import com.bakribrahim.newsapp.R;
import com.bakribrahim.newsapp.UI.settingfragment;

import androidx.appcompat.app.AppCompatActivity;

public class settingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contaier, new settingfragment())
                .commit();
    }

    @Override
    public void onBackPressed() {

        setResult(RESULT_OK);

        super.onBackPressed();
    }
}

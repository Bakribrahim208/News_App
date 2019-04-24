package com.bakribrahim.newsapp.viewModels;

import android.app.Application;

import com.bakribrahim.newsapp.utils.networkStates;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class newsViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;
com.bakribrahim.newsapp.utils.networkStates networkStates;

    public newsViewModelFactory(Application application, String param , networkStates networkStates) {
        mApplication = application;
        mParam = param;
        this.networkStates = networkStates;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        return (T) new newsViewModel(mParam , networkStates);
    }
}
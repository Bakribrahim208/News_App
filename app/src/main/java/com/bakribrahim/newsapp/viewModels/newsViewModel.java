package com.bakribrahim.newsapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;
import com.bakribrahim.newsapp.Models.article;
import com.bakribrahim.newsapp.utils.networkStates;
import com.bakribrahim.newsapp.utils.newsrepo;

public class newsViewModel extends ViewModel {

    com.bakribrahim.newsapp.utils.newsrepo newsrepo ;
    LiveData<PagedList<article>> newspagelist;
    String country ;
com.bakribrahim.newsapp.utils.networkStates networkStates;
    public newsViewModel(String country , networkStates networkStates) {
          this.country =country;
          this.networkStates = networkStates;
        inilize (country , networkStates);
    }
        public void inilize (String country , networkStates networkStates){
        newsrepo =new newsrepo(country , networkStates);
        newspagelist=newsrepo.getNewspagelist();
        }
    public LiveData<PagedList<article>> getNewspagelist() {
        return newspagelist;
    }


}

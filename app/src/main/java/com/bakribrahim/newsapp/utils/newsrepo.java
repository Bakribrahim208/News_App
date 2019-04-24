package com.bakribrahim.newsapp.utils;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import com.bakribrahim.newsapp.Models.article;
import com.bakribrahim.newsapp.networking.paging.newsDataSource;
import com.bakribrahim.newsapp.networking.paging.newsDatasourceFectory;

public class newsrepo  {

    LiveData<PagedList<article>> newspagelist;
    LiveData<PageKeyedDataSource<String, article>> liveDataSource;
    networkStates networkStates;
    public newsrepo(String country , networkStates networkStates) {
        newsDatasourceFectory dataSourceFactory = new newsDatasourceFectory(country , networkStates);
        //getting the live data source from data source factory
        if (dataSourceFactory.getItemLiveDataSource()==null)
        {

        }
        else
        {
            liveDataSource = dataSourceFactory.getItemLiveDataSource();
            PagedList.Config pagedListConfig =
                    (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(newsDataSource.PAGE_SIZE).build();
            //Building the paged list
            newspagelist = (new LivePagedListBuilder(dataSourceFactory, pagedListConfig))
                    .build();
        }

    }

    public LiveData<PagedList<article>> getNewspagelist() {
        return newspagelist;
    }
}

package com.bakribrahim.newsapp.networking.paging;

import com.bakribrahim.newsapp.utils.networkStates;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.bakribrahim.newsapp.Models.article;

public class newsDatasourceFectory extends DataSource.Factory  {

    private MutableLiveData<PageKeyedDataSource<String, article>> itemDataSource
            = new MutableLiveData<>();
    newsDataSource newsdatasource ;
     String country ;
    private networkStates networkStates;

    public newsDatasourceFectory(String country  ,  networkStates networkStates) {
        this.country = country;
        this.networkStates = networkStates;
    }

    @Override
    public DataSource create() {
        //getting our data source object
        newsDataSource newsdatasource = new newsDataSource(country, networkStates);

        //posting the datasource to get the values
        itemDataSource.postValue(newsdatasource);

        //returning the datasource
        return newsdatasource;
    }



    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<String, article>> getItemLiveDataSource() {
        return itemDataSource;
    }


}

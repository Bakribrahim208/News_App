package com.bakribrahim.newsapp.networking.paging;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.bakribrahim.newsapp.Models.NewsFeed;
import com.bakribrahim.newsapp.Models.article;
import com.bakribrahim.newsapp.utils.networkStates;
import com.bakribrahim.newsapp.networking.Api;
import com.bakribrahim.newsapp.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class newsDataSource extends PageKeyedDataSource<String, article> {

    public static final String  newsDataSource_TAG = "newsDataSource_TAG";

    //the size of a page that we want
    public static final int PAGE_SIZE = 5;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    //we need to fetch from google-news
    private static final String SITE_NAME = "top-headlines";

    private  static final String API_KEY="";

    private static  String country ;

    private final Api newsService;
  private networkStates networkStates;
    public newsDataSource(String country, networkStates networkStates) {
        newsService= RetrofitClient.getInstance().getApi();
         this.country =country;
         this.networkStates = networkStates;
     }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, article> callback) {
          Call<NewsFeed> callBack =newsService.getnews(FIRST_PAGE,PAGE_SIZE,API_KEY,country);
        callBack.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        networkStates.sucess_connection_();
                        callback.onResult(response.body().articles,null, String.valueOf(FIRST_PAGE+1));
                    }
                    Log.e("newsDataSource_TAG","loadInitial>>> "+response.body().articles);
                 } else {
                    Log.e("API CALL", response.message());
                 }
            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                 Log.e(newsDataSource_TAG,"onFailure >>> "+t.getMessage()+"\n"+ t.getCause());
                  networkStates.error_when_open();
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, article> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<String, article> callback) {
        final AtomicInteger page = new AtomicInteger(0);
        try {
            page.set(Integer.parseInt(params.key));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


        Call<NewsFeed> callBack =newsService.getnews(page.get()+1,PAGE_SIZE,API_KEY,country);
        callBack.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                if (response.isSuccessful()) {
                    networkStates.sucess_connection_();
                    callback.onResult(response.body().articles, String.valueOf(page.get()+1));
                    Log.e(newsDataSource_TAG,"loadAfter >>> "+response.body().articles);
                } else {
                    Log.e(newsDataSource_TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                Log.e(newsDataSource_TAG,"loadAfter >>> "+t.getMessage());
                networkStates.error_when_after();

             }
        });




    }
}

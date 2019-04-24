package com.bakribrahim.newsapp.networking;

 import com.bakribrahim.newsapp.Models.NewsFeed;
 import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("top-headlines")
    Call<NewsFeed>
    getnews(@Query("page") int page,
               @Query("pagesize") int pagesize,
                @Query("apiKey") String apiKey
                ,@Query("country") String country );
}
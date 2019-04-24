package com.bakribrahim.newsapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeed {
    @SerializedName("articles")
    public List<com.bakribrahim.newsapp.Models.article> articles;
    @SerializedName("status")
    public  String status;

    public NewsFeed(List<com.bakribrahim.newsapp.Models.article> articles, String status, long totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    @SerializedName("totalResults")
    public  long totalResults;

    public List<com.bakribrahim.newsapp.Models.article> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public long getTotalResults() {
        return totalResults;
    }
}

package com.bakribrahim.newsapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import com.bakribrahim.newsapp.Models.source;

public class article implements Serializable {


 @SerializedName("source")
    public com.bakribrahim.newsapp.Models.source source;

   public article(com.bakribrahim.newsapp.Models.source source, String author, String titile, String description, String url, String urlToImage, String publishedAt, String content) {
      this.source = source;
      this.author = author;
      this.titile = titile;
      this.description = description;
      this.url = url;
      this.urlToImage = urlToImage;
      this.publishedAt = publishedAt;
      this.content = content;
   }

   public com.bakribrahim.newsapp.Models.source getSource() {
      return source;
   }

   public String getAuthor() {
      return author;
   }

   public String getTitile() {
      return titile;
   }

   public String getDescription() {
      return description;
   }

   public String getUrl() {
      return url;
   }

   public String getUrlToImage() {
      return urlToImage;
   }

   public String getPublishedAt() {
      return publishedAt;
   }

   public String getContent() {
      return content;
   }

   @SerializedName("author")
    public   String author ;

    @SerializedName("title")
    public   String titile;

    @SerializedName("description")
    public String description;

    @SerializedName("url")
    public  String url ;

    @SerializedName("urlToImage")
    public  String urlToImage;

    @SerializedName("publishedAt")
    public  String publishedAt;

    @SerializedName("content")
    public String content;
}

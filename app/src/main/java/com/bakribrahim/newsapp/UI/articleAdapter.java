package com.bakribrahim.newsapp.UI;

import android.app.Activity;
import android.app.ActivityOptions;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakribrahim.newsapp.utils.CustomClickListener;
import com.bakribrahim.newsapp.R;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bakribrahim.newsapp.Models.article;
import com.bakribrahim.newsapp.databinding.NewsItemBinding;

public class articleAdapter  extends PagedListAdapter<article, articleAdapter.ItemViewHolder>
        {

    private Activity activity;
     public articleAdapter(Activity mCtx ) {
        super(DIFF_CALLBACK);
        this.activity = mCtx;
     }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        return new ItemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        article item = getItem(position);
        Glide.with(activity)
                .load(item.getUrlToImage())
                .error(new ColorDrawable(Color.BLACK))
                .placeholder(R.drawable.newsplaceholder).into(holder.newsItemBinding.articleImage);

        String []date_time =item.getPublishedAt().split("T");


        holder.newsItemBinding.setArticl(item);
        holder.newsItemBinding.setDateArray(date_time);
        holder.newsItemBinding.setClickListener(holder);
        Typeface MMedium = Typeface.createFromAsset(activity.getAssets(), "fonts/MMedium.ttf");
        Typeface MLight = Typeface.createFromAsset(activity.getAssets(), "fonts/MLight.ttf");

        holder.newsItemBinding.articleTitle.setTypeface(MMedium);
        holder.newsItemBinding.articleTrailtext.setTypeface(MLight);



    }

    private static DiffUtil.ItemCallback<article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<article>() {
                @Override
                public boolean areItemsTheSame(article oldItem, article newItem) {
                    Log.e("adapterconversion",oldItem.source.getId()+newItem.source.getId()+"\n");
                    return oldItem.source.getId() == newItem.source.getId();
                }

                @Override
                public boolean areContentsTheSame(article oldItem, article newItem) {
                    return oldItem.equals(newItem);
                }
            };


     class ItemViewHolder extends RecyclerView.ViewHolder  implements CustomClickListener {

        NewsItemBinding newsItemBinding;
        public ItemViewHolder(NewsItemBinding itemView) {
            super(itemView.getRoot());
            newsItemBinding=itemView;

        }

         @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
         @Override
         public void cardClicked(article article) {
             Pair[] pair = new Pair[3];
             pair[0] = new Pair<View, String>(newsItemBinding.articleImage, "logo");
             pair[1] = new Pair<View, String>(newsItemBinding.articleTitle, "text");
             pair[2] = new Pair<View, String>(newsItemBinding.articleSection, "date");

             ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, pair);
             Intent i = new Intent(activity, details_activity.class);
             i.putExtra("selected_artical",article);
             activity.startActivity(i, options.toBundle());
         }
     }
}
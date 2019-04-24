package com.bakribrahim.newsapp.UI;

import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bakribrahim.newsapp.Models.article;
import com.bakribrahim.newsapp.R;
import com.bakribrahim.newsapp.databinding.ActivityMainBinding;
import com.bakribrahim.newsapp.utils.networkStates;
import com.bakribrahim.newsapp.viewModels.newsViewModel;
import com.bakribrahim.newsapp.viewModels.newsViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  , networkStates {
    RecyclerView recyclerView;
     SwipeRefreshLayout swiperefreshlayout;
    ImageView birdImageView;
    AnimationDrawable frameAnimation;


    ActivityMainBinding binding ;
      articleAdapter adapter;
    newsViewModel itemViewModel;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, com.bakribrahim.newsapp.R.layout.activity_main);
         birdImageView =binding.imageView;
        frameAnimation = (AnimationDrawable) birdImageView.getBackground();
         recyclerView=binding.recyclerView;
        swiperefreshlayout=binding.swiperrefresh;
        swiperefreshlayout.setOnRefreshListener(MainActivity.this);
        swiperefreshlayout.setRefreshing(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemViewModel = ViewModelProviders.of(this,new newsViewModelFactory(this.getApplication(),getDataFromPreferance(),this)
        ).get(newsViewModel.class);
        SetViewmodel(getDataFromPreferance());
     }


    public void SetViewmodel(String country)
    {
        adapter = new articleAdapter(MainActivity.this );

        itemViewModel.inilize(country ,MainActivity.this);
        //observing the itemPagedList from view model
        itemViewModel.getNewspagelist().observe(this, new Observer<PagedList<article>>() {
            @Override
            public void onChanged(@Nullable PagedList<article> items) {
                //in case of any changes
                //submitting the items to adapter
                     adapter.submitList(items);

            }
        });

        //setting the adapter
        binding.setMyAdapte(adapter);
    }

    public String getDataFromPreferance(){
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String country = sharedPreferences.getString("list_preference_1", "eg");

        return  country;
    }
     @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1010 )
        {
            Intent intent = getIntent();
            finish();
            startActivity(intent);


        }
        else
            Toast.makeText(this, "Eroro="+requestCode , Toast.LENGTH_SHORT).show();

    }
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.bakribrahim.newsapp.R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

         if (id == com.bakribrahim.newsapp.R.id.action_settings){
            try{
            Intent intent =new Intent(MainActivity.this, settingActivity.class);
            startActivityForResult(intent,1010);


            }catch (Exception e){
                Toast.makeText(this, "Hmm.. Sorry, \nCannot be share", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRefresh() {
        adapter=null;
        binding.setMyAdapte(adapter);
        itemViewModel.getNewspagelist().removeObservers(MainActivity.this);
          SetViewmodel(getDataFromPreferance());
       swiperefreshlayout.setRefreshing(false);
    }


    @Override
    public void sucess_connection_() {

        frameAnimation.stop();
        binding.mainlayout.setVisibility(View.VISIBLE);
        binding.errorlayout.setVisibility(View.INVISIBLE);
        swiperefreshlayout.setRefreshing(false);
    }

    @Override
    public void error_when_open() {
       binding.mainlayout.setVisibility(View.INVISIBLE);
       binding.errorlayout.setVisibility(View.VISIBLE);
        swiperefreshlayout.setRefreshing(false);
        frameAnimation.start();
     }

    @Override
    public void error_when_after() {
         show_snakBar();
    }
    public void show_snakBar() {
         Snackbar snackbar = Snackbar
                .make(binding.rootlayout, R.string.txt_no_connect_description, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                          swiperefreshlayout.setRefreshing(true);
                         MainActivity.this.onRefresh();
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        sbView.setBackgroundResource(R.color.colorlight);
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();
    }


    public void try_button_click(View view) {
        swiperefreshlayout.setRefreshing(true);
        MainActivity.this.onRefresh();
    }
}

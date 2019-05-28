package com.example.ejercicioretrofit_jesus_perezmontes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import com.example.ejercicioretrofit_jesus_perezmontes.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Result> data;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        RecyclerView recyclerView = findViewById(R.id.mi_recicler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        data = new ArrayList<>();
        adapter = new MainAdapter(this, (ArrayList<com.example.ejercicioretrofit_jesus_perezmontes.Result>) data);
        recyclerView.setAdapter(adapter);
        loadJSON();
    }
    private void loadJSON(){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);
        Call<RyMFeed> call = restClient.getData();

        call.enqueue(new Callback<RyMFeed>() {
            @Override
            public void onResponse(Call<RyMFeed> call, Response<RyMFeed> response) {
                switch (response.code()) {
                    case 200:
                        RyMFeed feed = response.body();
                        Log.e("retrofitExample", ""+feed.getResults().toString());
                        data.addAll(feed.getResults());
                        adapter.notifyDataSetChanged();
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<RyMFeed> call, Throwable t) {
                Log.e("retrofitExample", t.toString());
            }
        });
    }



}

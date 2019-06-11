package br.com.alessanderleite.petsapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import br.com.alessanderleite.petsapp.api.ApiClient;
import br.com.alessanderleite.petsapp.api.ApiInterface;
import br.com.alessanderleite.petsapp.model.Pets;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Pets> petsList;
    ApiInterface apiInterface;
    //Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
    } //finish onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void getPets() {

        Call<List<Pets>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Pets>>() {
            @Override
            public void onResponse(Call<List<Pets>> call, Response<List<Pets>> response) {
                progressBar.setVisibility(View.GONE);
                petsList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(petsList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pets>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPets();
    }
}

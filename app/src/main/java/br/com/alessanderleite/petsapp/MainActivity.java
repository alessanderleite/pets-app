package br.com.alessanderleite.petsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadJson();
    }

    private void loadJson() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Pets>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Pets>>() {
            @Override
            public void onResponse(Call<List<Pets>> call, Response<List<Pets>> response) {
                petsList = response.body();
                getRecylerView();
            }

            @Override
            public void onFailure(Call<List<Pets>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "rp :" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRecylerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(petsList, this);
        recyclerView.setAdapter(adapter);
    }
}

package br.com.alessanderleite.petsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import br.com.alessanderleite.petsapp.api.ApiClient;
import br.com.alessanderleite.petsapp.api.ApiInterface;
import br.com.alessanderleite.petsapp.model.Pet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    private EditText mName, mSpecies, mBreed;

    private String name, species, breed;
    private int id;

    private Menu action;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mName = findViewById(R.id.name);
        mSpecies = findViewById(R.id.species);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        species = intent.getStringExtra("species");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            getSupportActionBar().setTitle("Edit " + name.toString());

            mName.setText(name);
            mSpecies.setText(species);

        } else  {
            getSupportActionBar().setTitle("Add a Pet");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0) {

            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);
        }
        return true;
    }

    private void postData(final String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String name = mName.getText().toString().trim();
        String species = mSpecies.getText().toString().trim();
        String breed = mBreed.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Pet> call = apiInterface.insertPet(key, name, species, breed);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                progressDialog.dismiss();

                Log.i(EditorActivity.class.getSimpleName(), response.body());

                response.body();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

            }
        });

    }

    void readMode() {

        mName.setFocusableInTouchMode(false);
        mSpecies.setFocusableInTouchMode(false);
        mBreed.setFocusableInTouchMode(false);

        mName.setFocusable(false);
        mSpecies.setFocusable(false);
        mBreed.setFocusable(false);
    }
}

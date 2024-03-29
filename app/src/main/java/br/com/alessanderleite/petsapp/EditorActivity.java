package br.com.alessanderleite.petsapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

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
        mBreed = findViewById(R.id.breed);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        species = intent.getStringExtra("species");
        breed = intent.getStringExtra("breed");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
            getSupportActionBar().setTitle("Edit " + name.toString());

            mName.setText(name);
            mSpecies.setText(species);
            mBreed.setText(breed);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;

            case R.id.menu_edit:
                //Edit
            case R.id.menu_save:
                //Save

                if (id == 0) {
                    if (TextUtils.isEmpty(mName.getText().toString()) ||
                            TextUtils.isEmpty(mSpecies.getText().toString()) ||
                            TextUtils.isEmpty(mBreed.getText())) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Please complete the field!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {

//                        postData("insert");
                        postData();
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();
                    }

                }

                return true;
        }
        return true;
    }

    private void postData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String name = mName.getText().toString().trim();
        String species = mSpecies.getText().toString().trim();
        String breed = mBreed.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Pet> call = apiInterface.insertPet(name, species, breed);

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                progressDialog.dismiss();

                Log.i(EditorActivity.class.getSimpleName(), response.toString());

//                response.body();
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(EditorActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
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

package br.com.alessanderleite.petsapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import br.com.alessanderleite.petsapp.api.ApiInterface;

public class EditorActivity extends AppCompatActivity {

    private EditText mName, mSpecies;

    private String name, species;
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
}

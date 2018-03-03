package com.example.niklas.medienlister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Tabelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabelle);
        getSupportActionBar().setTitle("Tabelle");
        getSupportActionBar().setTitle("Bearbeiten");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent menu = new Intent(this, Menu.class);
            startActivity(menu);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.niklas.medienlister;

import android.content.Intent;
import android.graphics.Point;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class Bearbeiten extends AppCompatActivity implements View.OnClickListener {
    public static String titel;
    public static String erscheinungsjahr;
    public static String laufzeit;
    public static String fsk;
    public static String server;
    public static boolean first = false;

    public static Button speichern;
    public static TextInputEditText name;
    public static TextInputEditText jahr;
    public static TextInputEditText zeit;
    public static Spinner fskspinner;
    public static Spinner serverspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bearbeiten);
        getSupportActionBar().setTitle("Bearbeiten");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int Measuredwidth = 0;
        int Measuredheight = 0;
        Point size = new Point();
        WindowManager w = getWindowManager();

        Display d = w.getDefaultDisplay();
        Measuredwidth = d.getWidth();
        Measuredheight = d.getHeight();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(Measuredwidth/dm.xdpi,2);
        double y = Math.pow(Measuredheight/dm.ydpi,2);


        System.out.println("------------------" + y);
        System.out.println("------------------" + x);

        if(y < x){
            TextView t = findViewById(R.id.titelfeld);
            t.setTextSize((float) (x* 1.2));
        }


      fskspinner = findViewById(R.id.fsk_spinner);
      ArrayAdapter<CharSequence> fsk_adapter = ArrayAdapter.createFromResource(this, R.array.fsk_werte, android.R.layout.simple_spinner_dropdown_item);
      fsk_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      fskspinner.setAdapter(fsk_adapter);

      serverspinner = findViewById(R.id.server_spinner);
      ArrayAdapter<CharSequence> server_adapter = ArrayAdapter.createFromResource(this, R.array.server_werte, android.R.layout.simple_spinner_dropdown_item);
      server_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      serverspinner.setAdapter(server_adapter);
      speichern = (Button) findViewById(R.id.speicherbutton);
      speichern.setOnClickListener(this);

      name = (TextInputEditText) findViewById(R.id.titel1);
      jahr = (TextInputEditText) findViewById(R.id.erscheinungsjahr1);
      zeit = (TextInputEditText) findViewById(R.id.laufzeit1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent menu = new Intent(this, Menu.class);
            startActivity(menu);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (name.getText().toString().equals(null) || name.getText().toString().equals("")) {
        } else {
            titel = name.getText().toString();
            erscheinungsjahr = jahr.getText().toString();
            laufzeit = zeit.getText().toString();
            fsk = (String) fskspinner.getSelectedItem();
            server = (String) serverspinner.getSelectedItem();
            if (titel.contains("€")) {
                titel = titel.replaceAll("€", "Euro");
            }

            Menu.dvdv.dvdEinfuegen(new DVD(titel, erscheinungsjahr, laufzeit, fsk, server));
            Thread sicherung = new Speichern();
            sicherung.start();

            name.setText("");
            jahr.setText("");
            zeit.setText("");
            fskspinner.setSelection(0);
            serverspinner.setSelection(0);
            name.requestFocus();
        }
    }
}



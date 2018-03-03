package com.example.niklas.medienlister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;


public class Menu extends AppCompatActivity implements View.OnClickListener {

    public static DVDVerwaltung dvdv;
    Button bearbeitenbutton;
    Button tabellenbutton;
    Button suchenbutton;
    String pfad = "T";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bearbeitenbutton = (Button)findViewById(R.id.bearbeitenbutton);
        bearbeitenbutton.setOnClickListener(this);

        tabellenbutton = (Button)findViewById(R.id.tabellenbutton);
        tabellenbutton.setOnClickListener(this);

        suchenbutton = (Button)findViewById(R.id.suchbutton);
        suchenbutton.setOnClickListener(this);


        Thread a = new Anfang();
        a.start();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bearbeitenbutton:
                Intent bearbeiten = new Intent(this, Bearbeiten.class);
                startActivity(bearbeiten);
                this.finish();
                break;

            case R.id.tabellenbutton:
                Intent tabelle = new Intent(this, Tabelle.class);
                this.finish();
                break;

            case R.id.suchbutton:
                Intent suche = new Intent(this, Suche.class);
                this.finish();
                break;
        }
    }
}

package com.example.niklas.medienlister;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class DVDVerwaltung {

    public String pfad = "T";
    public int maxAnz;
    public int naechste;
    public String dvd;
    public String lister;
    public int counter;
    public int zahl;
    public DVD[] dvds;
    public String titel;
    public String erscheinungsjahr;
    public String laufzeit;
    public String fsk;
    public String server;
    String user = "admin";
    String pass = "1234";

    NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, pass);

    public DVDVerwaltung(int maxAnz) {
        this.maxAnz = maxAnz;
        dvds = new DVD[maxAnz];
        naechste = 0;
    }


    public void dvdAufrufen() {
        try {
            String urlL = "smb://192.168.178.34//public//Lister//liste.txt";
            SmbFile Lfile = new SmbFile(urlL, auth);
            BufferedReader br = new BufferedReader(new InputStreamReader(new SmbFileInputStream(Lfile)));

            String test;
            while ((test = br.readLine()) != null) {

                String[] array = test.split("€");
                titel = array[0];
                erscheinungsjahr = array[1];
                laufzeit = array[2];
                fsk = array[3];
                server = array[4];
                dvdEinfuegen(new DVD(titel, erscheinungsjahr, laufzeit, fsk, server));
            }
            br.close();
        } catch (Exception e1) {
        }
    }

    public boolean dvdEinfuegen(DVD d) {
        if (naechste < maxAnz) {
            dvds[naechste] = d;
            naechste++;
            return true;
        } else {
            return false;
        }

    }

    public void dvdSortieren() {
        boolean running;
        do {
            running = false;

            for (int i = naechste; i >= 0; i--) {

                for (int j = 0; j < i - 1; j++) {

                    if (dvds[(j)].titel.toUpperCase().compareTo(dvds[j + 1].titel.toUpperCase()) > 0) {

                        DVD temp = dvds[j];

                        dvds[j] = dvds[j + 1];

                        dvds[j + 1] = temp;

                    }
                }
            }
        } while (running);

    }

    public void dvdCounter() {
        try {
            String urlC = "smb://192.168.178.34//public//Lister//counter.txt";
            SmbFile Cfile = new SmbFile(urlC, auth);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new SmbFileOutputStream(Cfile)));
            String zwischenzahl = Integer.toString(naechste);
            bw.write(zwischenzahl);
            bw.close();
        } catch (IOException e) {
            System.out.println("");
        }
    }

    public void dvdCounterAufrufen() {
        try {
            String urlC = "smb://192.168.178.34//public//Lister//counter.txt";
            SmbFile Cfile = new SmbFile(urlC, auth);
            BufferedReader br = new BufferedReader(new InputStreamReader(new SmbFileInputStream(Cfile)));

            lister = br.readLine();
            counter = Integer.parseInt(lister);

            br.close();

        } catch (Exception e1) {
            System.out.println("");
        }
    }


    public void dvdSpeichern() {

        try {
            String urlC = "smb://192.168.178.34//public//Lister//counter.txt";
            SmbFile Cfile = new SmbFile(urlC, auth);
            BufferedReader br = new BufferedReader(new InputStreamReader(new SmbFileInputStream(Cfile)));

            lister = br.readLine();
            counter = Integer.parseInt(lister);
            zahl = counter - 1;

            br.close();

        } catch (Exception e1) {
            System.out.println("");
        }

        for (int i = zahl; i <= naechste; i++) {
            if (dvds[i] != null) {
                try {
                    titel = Bearbeiten.titel;
                    erscheinungsjahr = Bearbeiten.erscheinungsjahr;
                    laufzeit = Bearbeiten.laufzeit;
                    fsk = Bearbeiten.fsk;
                    server = Bearbeiten.server;

                    String urlL = "smb://192.168.178.34//public//Lister//liste.txt";
                    SmbFile Lfile = new SmbFile(urlL, auth);

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new SmbFileOutputStream(Lfile, true)));
                    bw.write(dvds[i].titel + "€" + dvds[i].erscheinungsjahr + "€" + dvds[i].laufzeit + "€" + dvds[i].fsk
                            + "€" + dvds[i].server + "\r\n");

                    bw.close();
                } catch (Exception e1) {
                    System.out.println("");
                }
            }
        }

    }

}

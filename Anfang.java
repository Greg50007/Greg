package com.example.niklas.medienlister;

import java.net.MalformedURLException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 * Created by Niklas on 15.02.2018.
 */


public class Anfang extends Thread {

    public void run() {

        Menu.dvdv = new DVDVerwaltung(1000000);
        Menu.dvdv.dvdAufrufen();
        Menu.dvdv.dvdCounterAufrufen();
        Menu.dvdv.dvdSortieren();

        Anfang.interrupted();

    }
}
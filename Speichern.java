package com.example.niklas.medienlister;

/**
 * Created by Niklas on 22.02.2018.
 */

public class Speichern extends Thread {
    @Override
    public void run() {
        Menu.dvdv.dvdCounter();
        Menu.dvdv.dvdSpeichern();
        Menu.dvdv.dvdSortieren();

        Speichern.interrupted();
    }
}

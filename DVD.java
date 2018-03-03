package com.example.niklas.medienlister;

public class DVD {
    public String titel;
    public String erscheinungsjahr;
    public String laufzeit;
    public String fsk;
    public String server;

    public DVD(String titel, String erscheinungsjahr, String laufzeit, String fsk, String server) {
        this.titel = titel;
        this.erscheinungsjahr = erscheinungsjahr;
        this.laufzeit = laufzeit;
        this.fsk = fsk;
        this.server = server;
    }
}

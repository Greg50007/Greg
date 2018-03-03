package com.example.niklas.medienlister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Niklas on 25.02.2018.
 */

public class AutoErgänzung {

    public static void ergänzung() throws Exception {
        System.out.println("ergänzung");
        String suche = String.valueOf(Bearbeiten.name.getText());
        String j1 = "";
        String a1 = "";
        String z1 = "";
        URL url;

        try {
            url = new URL(
                    "https://de.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles="
                            + suche.replace(" ", "+") + "&rvsection=0");

            Reader is = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(is);
            for (String s; (s = in.readLine()) != null;) {
                if (s.contains("|") && s.contains("jahr") || s.contains("|") && s.contains("Erscheinungsjahr")
                        || s.contains("|") && s.contains("EJ") || s.contains("|") && s.contains("PJ")) {
                    if (s.contains("20") || s.contains("19")) {
                        String j2 = "";
                        String[] array = s.split("=");
                        j1 = array[1].replace(" ", "");
                        j1 = j1.substring(j1.length() - 4);

                        if (!j1.contains("") || !j1.contains(" ") || !j1.contains(null)) {
                            if (j1.contains("0") || j1.contains("1") || j1.contains("2") || j1.contains("3")
                                    || j1.contains("4") || j1.contains("5") || j1.contains("6") || j1.contains("7")
                                    || j1.contains("8") || j1.contains("9")) {
                                j2 = j1.replace(" ", "");
                                Bearbeiten.jahr.setText(j2);
                            }
                        }
                    }
                }

                if (s.contains("LÃ¤nge") || s.contains("LEN") || s.contains("Minuten")) {
                    String[] array = s.split("=");
                    String z2 = "";
                    z1 = array[1];
                    z1 = z1.replaceAll(" ", "");
                    if (z1.length() > 2) {
                        z1 = z1.substring(0, 3);
                        String[] array2 = z1.split("");
                        for (int i = 0; i < 3; i++) {
                            if (array2[i].equals("0") || array2[i].equals("1") || array2[i].equals("2")
                                    || array2[i].equals("3") || array2[i].equals("4") || array2[i].equals("5")
                                    || array2[i].equals("6") || array2[i].equals("7") || array2[i].equals("8")
                                    || array2[i].equals("9")) {
                                z2 = z2 + array2[i];
                            }
                        }

                        Bearbeiten.zeit.setText(z2);

                    } else {
                        Bearbeiten.zeit.setText(z1);

                    }
                    if (Bearbeiten.zeit.getText().equals("") || Bearbeiten.zeit.getText().equals(" ")
                            || Bearbeiten.zeit.getText().equals(null)) {

                        String[] array3 = s.split("Minuten");
                        String z3 = array3[0];
                        z3 = z3.substring(z3.length() -4);
                        String[] array4 = z3.split("");
                        String z4 = "";
                        for (int i = 0; i < 3; i++) {
                            if (array4[i].equals("0") || array4[i].equals("1") || array4[i].equals("2")
                                    || array4[i].equals("3") || array4[i].equals("4") || array4[i].equals("5")
                                    || array4[i].equals("6") || array4[i].equals("7") || array4[i].equals("8")
                                    || array4[i].equals("9")) {
                                z4 = z4 + array4[i];
                            }
                        }
                        Bearbeiten.zeit.setText(z4);

                    }

                }

                if (s.contains("FSK")) {
                    String[] array = s.split("=");
                    a1 = array[1].replace(" ", "");
                    String[] array2 = a1.split("\\{");
                    String a2 = array2[0];

                    if (a2.contains("0")) {
                        Bearbeiten.fskspinner.setSelection(0);
                    } else if (a2.contains("12")) {
                        Bearbeiten.fskspinner.setSelection(2);
                    } else if (a2.contains("16")) {
                        Bearbeiten.fskspinner.setSelection(3);
                    } else if (a2.contains("18")) {
                        Bearbeiten.fskspinner.setSelection(4);
                    } else if (a2.contains("6")) {
                        Bearbeiten.fskspinner.setSelection(1);
                    }
                    Bearbeiten.serverspinner.requestFocus();

                }
            }

            in.close();
        } catch (MalformedURLException e1) {
            System.out.println("MalformedURLException: " + e1);
        } catch (IOException e1) {
            System.out.println("IOException: " + e1);
        }

        if (Bearbeiten.jahr.getText().equals("")) {
            korrigieren();
        }

    }

    public static void fehler() throws Exception {
        System.out.println("fehler");
        String suche = String.valueOf(Bearbeiten.name.getText());
        String j1 = "";
        String a1 = "";
        String z1 = "";
        URL url;

        try {
            url = new URL(
                    "https://de.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles="
                            + suche.replace(" ", "+") + "&rvsection=0");

            Reader is = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(is);

            for (String s; (s = in.readLine()) != null;) {
                if (s.contains("|") && s.contains("jahr") || s.contains("|")
                        && s.contains("Erscheinungsjahr") || s.contains("|")
                        && s.contains("EJ") || s.contains("|")
                        && s.contains("PJ")) {
                    if (s.contains("20") || s.contains("19")) {
                        String j2 = "";
                        String[] array = s.split("=");
                        j1 = array[1].replace(" ", "");
                        j1 = j1.substring(j1.length() - 4);

                        if (!j1.contains("") || !j1.contains(" ")
                                || !j1.contains(null)) {
                            if (j1.contains("0") || j1.contains("1")
                                    || j1.contains("2") || j1.contains("3")
                                    || j1.contains("4") || j1.contains("5")
                                    || j1.contains("6") || j1.contains("7")
                                    || j1.contains("8") || j1.contains("9")) {
                                j2 = j1.replace(" ", "");
                                Bearbeiten.jahr.setText(j2);
                            }
                        }
                    }
                }

                if (s.contains("LÃ¤nge") || s.contains("LEN")
                        || s.contains("Minuten")) {
                    String[] array = s.split("=");
                    String z2 = "";
                    z1 = array[1];
                    z1 = z1.replaceAll(" ", "");
                    if (z1.length() > 2) {
                        z1 = z1.substring(0, 3);
                        String[] array2 = z1.split("");
                        for (int i = 0; i < 3; i++) {
                            if (array2[i].equals("0") || array2[i].equals("1")
                                    || array2[i].equals("2")
                                    || array2[i].equals("3")
                                    || array2[i].equals("4")
                                    || array2[i].equals("5")
                                    || array2[i].equals("6")
                                    || array2[i].equals("7")
                                    || array2[i].equals("8")
                                    || array2[i].equals("9")) {
                                z2 = z2 + array2[i];
                            }
                        }

                        Bearbeiten.zeit.setText(z2);

                    } else {
                        Bearbeiten.zeit.setText(z1);

                    }
                    if (Bearbeiten.zeit.getText().equals("")
                            || Bearbeiten.zeit.getText().equals(" ")
                            || Bearbeiten.zeit.getText().equals(null)) {

                        String[] array3 = s.split("Minuten");
                        String z3 = array3[0];
                        z3 = z3.substring(z3.length() - 4);
                        String[] array4 = z3.split("");
                        String z4 = "";
                        for (int i = 0; i < 3; i++) {
                            if (array4[i].equals("0") || array4[i].equals("1")
                                    || array4[i].equals("2")
                                    || array4[i].equals("3")
                                    || array4[i].equals("4")
                                    || array4[i].equals("5")
                                    || array4[i].equals("6")
                                    || array4[i].equals("7")
                                    || array4[i].equals("8")
                                    || array4[i].equals("9")) {
                                z4 = z4 + array4[i];
                            }
                        }
                        Bearbeiten.zeit.setText(z4);

                    }

                }

                if (s.contains("FSK")) {
                    String[] array = s.split("=");
                    a1 = array[1].replace(" ", "");
                    String[] array2 = a1.split("\\{");
                    String a2 = array2[0];

                    if (a2.contains("0")) {
                        Bearbeiten.fskspinner.setSelection(0);
                    } else if (a2.contains("12")) {
                        Bearbeiten.fskspinner.setSelection(2);
                    } else if (a2.contains("16")) {
                        Bearbeiten.fskspinner.setSelection(3);
                    } else if (a2.contains("18")) {
                        Bearbeiten.fskspinner.setSelection(4);
                    } else if (a2.contains("6")) {
                        Bearbeiten.fskspinner.setSelection(1);
                    }
                    Bearbeiten.serverspinner.requestFocus();

                }

            }

            in.close();
        } catch (MalformedURLException e1) {
            System.out.println("MalformedURLException: " + e1);
        } catch (IOException e1) {
            System.out.println("IOException: " + e1);
        }

        if (Bearbeiten.jahr.getText().equals("")) {
            korrigieren();
        }

    }

    public static void korrigieren() throws Exception {
        System.out.println("korrigieren");
        String suche = Bearbeiten.name.getText() + " (Film)";
        String j1 = "";
        String a1 = "";
        String z1 = "";
        URL url;

        try {
            url = new URL(
                    "https://de.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=xmlfm&titles="
                            + suche.replace(" ", "+") + "&rvsection=0");

            Reader is = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(is);
            for (String s; (s = in.readLine()) != null;) {
                if (s.contains("|") && s.contains("jahr") || s.contains("|") && s.contains("Erscheinungsjahr")
                        || s.contains("|") && s.contains("EJ") || s.contains("|") && s.contains("PJ")) {
                    if (s.contains("20") || s.contains("19")) {
                        String j2 = "";
                        String[] array = s.split("=");
                        j1 = array[1].replace(" ", "");
                        j1 = j1.substring(j1.length() - 4);

                        if (!j1.contains("") || !j1.contains(" ") || !j1.contains(null)) {
                            if (j1.contains("0") || j1.contains("1") || j1.contains("2") || j1.contains("3")
                                    || j1.contains("4") || j1.contains("5") || j1.contains("6") || j1.contains("7")
                                    || j1.contains("8") || j1.contains("9")) {
                                j2 = j1.replace(" ", "");
                                Bearbeiten.jahr.setText(j2);
                            }
                        }
                    }
                }
                if (s.contains("LÃ¤nge") || s.contains("LEN") || s.contains("Minuten")) {
                    String[] array = s.split("=");
                    String z2 = "";
                    z1 = array[1];
                    z1 = z1.replaceAll(" ", "");
                    if (z1.length() > 2) {
                        z1 = z1.substring(0, 3);
                        String[] array2 = z1.split("");
                        for (int i = 0; i < 3; i++) {
                            if (array2[i].equals("0") || array2[i].equals("1") || array2[i].equals("2")
                                    || array2[i].equals("3") || array2[i].equals("4") || array2[i].equals("5")
                                    || array2[i].equals("6") || array2[i].equals("7") || array2[i].equals("8")
                                    || array2[i].equals("9")) {
                                z2 = z2 + array2[i];
                            }
                        }

                        Bearbeiten.zeit.setText(z2);

                    } else {
                        Bearbeiten.zeit.setText(z1);

                    }
                    if (Bearbeiten.zeit.getText().equals("") || Bearbeiten.zeit.getText().equals(" ")
                            || Bearbeiten.zeit.getText().equals(null)) {

                        String[] array3 = s.split("Minuten");
                        String z3 = array3[0];
                        z3 = z3.substring(z3.length() -4);
                        String[] array4 = z3.split("");
                        String z4 = "";
                        for (int i = 0; i < 3; i++) {
                            if (array4[i].equals("0") || array4[i].equals("1") || array4[i].equals("2")
                                    || array4[i].equals("3") || array4[i].equals("4") || array4[i].equals("5")
                                    || array4[i].equals("6") || array4[i].equals("7") || array4[i].equals("8")
                                    || array4[i].equals("9")) {
                                z4 = z4 + array4[i];
                            }
                        }
                        Bearbeiten.zeit.setText(z4);

                    }

                }

                if (s.contains("FSK")) {
                    String[] array = s.split("=");
                    a1 = array[1].replace(" ", "");
                    String[] array2 = a1.split("\\{");
                    String a2 = array2[0];

                    if (a2.contains("0")) {
                        Bearbeiten.fskspinner.setSelection(0);
                    } else if (a2.contains("12")) {
                        Bearbeiten.fskspinner.setSelection(2);
                    } else if (a2.contains("16")) {
                        Bearbeiten.fskspinner.setSelection(3);
                    } else if (a2.contains("18")) {
                        Bearbeiten.fskspinner.setSelection(4);
                    } else if (a2.contains("6")) {
                        Bearbeiten.fskspinner.setSelection(1);
                    }
                    Bearbeiten.serverspinner.requestFocus();

                }
            }

            in.close();
        } catch (MalformedURLException e1) {
            System.out.println("MalformedURLException: " + e1);
        } catch (IOException e1) {
            System.out.println("IOException: " + e1);
        }

    }
}

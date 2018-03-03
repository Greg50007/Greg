package com.example.niklas.medienlister;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Niklas on 25.02.2018.
 */

public class Rechtschreibkorrektur {
    public static String ziel = "";
    public static String suchString = "";

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
			/* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }


    public static void rechtschreibkorrektur() throws Exception{

        suchString = String.valueOf(Bearbeiten.name.getText());

        String url = "http://ofdbgw.geeksphere.de/search/" + suchString.replace(" ", "+");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        NodeList nodeList = null;
        nodeList = (NodeList) xpath.compile("/ofdbgw/resultat/eintrag").evaluate(doc, XPathConstants.NODESET);

        List<String> titelList = new ArrayList<>();
        List<Double> werte = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                titelList.add(e.getElementsByTagName("titel").item(0).getTextContent());
            }
        }

        for (int i = 0; i < titelList.size(); i++) {
            werte.add(similarity(suchString, titelList.get(i)));
            String v = titelList.get(i) + "€" + similarity(suchString, titelList.get(i));
            titelList.set(i, v);
        }
        Collections.sort(werte);

        for (int i = 0; i < titelList.size(); i++) {
            if (titelList.get(i).contains(werte.get(werte.size() - 1).toString())) {
                ziel = titelList.get(i);
            }
        }

        if(ziel == null || ziel == ""){
            Bearbeiten.name.setText(suchString);
            suchString = null;
            //AutoErgänzung.ergänzung();
        } else{
            String[] array = ziel.split("€");
            ziel = array[0];
            Bearbeiten.name.setText(ziel);
            ziel = null;
            titelList = null;
            werte = null;
            //AutoErgänzung.ergänzung();
        }






    }
}

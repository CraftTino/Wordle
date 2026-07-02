package java.de.ct;

import java.util.ArrayList;

public class Wordle {

    public ArrayList<String> dict = new ArrayList<>(); //Deklarierung und Initialisierung der Wörterliste
    public Wordle(){
        ListenErsteller.sorter(dict); //Erstellt die Liste aller erlaubten Wörter (max 5 Buchstaben, keine Zahlen)
        System.out.println(dict.size());

    }





    //Hilsmethoden
    public static boolean parser(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}

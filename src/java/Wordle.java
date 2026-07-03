import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Wordle {

    // Liste aller erlaubten Wörter
    public ArrayList<String> dict = new ArrayList<>();

    public Wordle() {
        //Chancen, die der Spieler zum erraten bekommt
        int eingaben = 5;

        //Erstellung der Liste möglicher Wörter
        ListenErsteller.sorter(dict);
        System.out.println("Anzahl Wörter: " + dict.size());

        String zielWort = getZielWort();
        //Scanner zu überprüfung
        Scanner scanner = new Scanner(System.in);
        String temp = "";

        System.out.println("------ W O R D L E ------ \n Finde das fünfstellige englische Wort");
//Eingabe und überprüfung und Ausgabe
        for (; eingaben > 0; eingaben--) {

            if (!temp.equals(zielWort)) {
                System.out.print(" Noch " + eingaben + " Versuche ");
                temp = eingabeAufforderung(scanner); //Input und Zwischenspeicher
            } else {
                break;
            }

            char[] ergebnis = new char[5];     // G = grün, Y = gelb, X = grau
            boolean[] benutzt = new boolean[5];

// 1. Richtige Position (grün)
            for (int i = 0; i < 5; i++) {
                if (temp.charAt(i) == zielWort.charAt(i)) {
                    ergebnis[i] = 'G';
                    benutzt[i] = true;
                }
            }

// 2. Richtiger Buchstabe, falsche Position (gelb)
            for (int i = 0; i < 5; i++) {
                if (ergebnis[i] == 'G') {
                    continue;
                }

                for (int j = 0; j < 5; j++) {
                    if (!benutzt[j] && temp.charAt(i) == zielWort.charAt(j)) {
                        ergebnis[i] = 'Y'; //Buchstabe an anderer Stelle
                        benutzt[j] = true;
                        break;
                    }
                }

                if (ergebnis[i] == '\0') {
                    ergebnis[i] = 'X'; // Buchstabe kommt nicht vor
                }
            }


// Ausgabe der einzelnen Buchstaben
            for (int i = 0; i < 5; i++) {
                switch (ergebnis[i]) {
                    case 'G':
                        System.out.print("\u001B[48;5;120m " + "\u001B[30m" + temp.charAt(i) + "\u001B[30m " + "\u001B[0m "); //Schwarze Schrift auf grünem Hintergrund
                        break;
                    case 'Y':
                        System.out.print("\u001B[103m " + "\u001B[30m" + temp.charAt(i) + "\u001B[103m " + "\u001B[0m ");    //Schwarze Farbe auf gelbem Hintergrund
                        break;
                    case 'X':
                        System.out.print("\u001B[100m " + "\u001B[30m" + temp.charAt(i) + "\u001B[100m " + "\u001B[0m ");    //Schwarze Farbe auf grauem Hintergrund
                }
            }
        }

//Glückwunsch wenn gewonnen
        if(temp.equals(zielWort)){
            System.out.print("Glückwunsch");
        } else{
            if(eingaben == 0){
                System.out.println("Pech gehabt. Das Wort wäre " + zielWort + " gewesen");
            }
        }
//Programm beenden
        scanner.close();
    }


    /**
     * Eingabeaufforderung (Bittet um Eingabe und übergibt diese) Enthält Längenüberprüfung und Eintragsüberprüfung
     */
    public String eingabeAufforderung(Scanner scan) {
        String eingabe;
        System.out.println("\n gib dein Wort ein");
        while (true) {
            eingabe = scan.nextLine().toLowerCase();

            if (eingabe.length() != 5 || !eingabe.matches("[a-zA-ZäöüÄÖÜß]{5}")) {
                System.out.println("\u001B[31m" +"Fehler: falsche Länge oder unerlaubte Symbole \u001B[0m");
                System.out.println("gib dein Wort ein");
            } else {
                if(!dict.contains(eingabe)){
                    System.out.println("\u001B[31m" + "Fehler: Das Wort ist nicht im Wörterbuch \u001B[0m");
                    System.out.println("gib dein Wort ein");
                }
                else {
                    break;
                }
            }


        }
        return eingabe;
    }


    /**
     * Wählt ein zufälliges Wort aus
     */
    public String getZielWort() {
        String zielWort = dict.get(ThreadLocalRandom.current().nextInt(dict.size()));
        return zielWort;
    }

}
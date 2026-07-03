import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Wordle {
    private int eingaben;


    // Liste aller erlaubten Wörter
    public ArrayList<String> dict = new ArrayList<>();

    public Wordle() {
        eingaben = 5;

        ListenErsteller.sorter(dict);
        System.out.println("Anzahl Wörter: " + dict.size());
        String zielWort = getZielWort();

        //Eingabe und überprüfung
        while(eingabeAufforderung() != zielWort && eingaben>0){

        }
    }


    /**
     * Eingabeaufforderung (Bittet um Eingabe und übergibt diese)
     */
    public String eingabeAufforderung(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("gib dein Wort ein");
        String eingabe = scanner.nextLine();
        scanner.close();
        return eingabe;
    }


    /**
     * Wählt ein zufälliges Wort aus
     */
    public String getZielWort() {
        String zielWort = dict.get(ThreadLocalRandom.current().nextInt(dict.size()));
        System.out.println(zielWort);
        return zielWort;
    }

    /**
     * Prüft, ob der String eine Zahl ist.
     */
    public static boolean parser(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
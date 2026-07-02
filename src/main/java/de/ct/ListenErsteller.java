package java.de.ct;

import java.io.*;
import java.util.ArrayList;

public class ListenErsteller {





    public static void sorter(ArrayList speicherListe){ //Erstellung der Liste aller möglichen Wörter
        FileInputStream fis;
        try {
            fis = new FileInputStream("resources/dictionary.txt"); //Access the dictionary
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while(true){
            try {
                if (!((line = br.readLine()) != null && line.length() == 5 && Wordle.parser(line))) break; //Auswählen von Wörtern, die 5-Stellige Strings ohne Zahlen sind
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //process the line
            speicherListe.add(line.toLowerCase());
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

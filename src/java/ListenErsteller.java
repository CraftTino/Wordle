import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListenErsteller {

    public static void sorter(ArrayList<String> speicherListe) {

        String[] dateien = {
                "src/resources/words_part_1.txt",
                "src/resources/words_part_2.txt",
                "src/resources/words_part_3.txt"
        };

        for (String datei : dateien) {

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(datei)))) {

                String line;

                while ((line = br.readLine()) != null) {

                    line = line.trim().toLowerCase();

                    // Nur Wörter mit genau 5 Buchstaben zulassen
                    if (!line.matches("[a-zäöüß]{5}")) {
                        continue;
                    }

                    speicherListe.add(line);

                }

            } catch (IOException e) {
                System.err.println("Fehler beim Lesen von: " + datei);
                e.printStackTrace();
            }
        }
    }
}
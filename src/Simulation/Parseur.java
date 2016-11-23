package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @autor Ludovic BURG
 */
public class Parseur {

    private static void nextLine (FileInputStream reader) throws IOException {
        while (reader.read() != '\n') {}
    }

    private static void openParanthesis (FileInputStream reader) throws IOException {
        while (reader.read() != '(') {}
    }

    private static void closeParanthesis (FileInputStream reader) throws IOException {
        while (reader.read() != ')') {}
    }

    private static void startAnalyse(FileInputStream reader) throws IOException {
        while (reader.read() != ';') {}
    }

    private static void findBatiment(FileInputStream reader, String batiment) throws IOException {
        char c;
        String s = "";
        boolean trouve = false;
        do {
            c = (char)reader.read();
            if (s.equals(batiment)) {
                trouve = true;
            } else if (c != '(') {
                s = s+c;
            } else {
                s = "";
                nextLine(reader);
            }

        } while (c != ';' && !trouve);
    }

    private static void findEtage(FileInputStream reader, String etage) throws IOException {
        char c;
        String s = "";
        boolean trouve = false;
        boolean termine = false;
        do {
            c = (char)reader.read();
            if (!termine) {
                if (s.equals(etage)) {
                    trouve = true;
                } else if (c == '\n') {
                    termine = true;
                } else if (c != '(') {
                    s = s+c;
                } else {
                    s = "";
                    closeParanthesis(reader);
                }
            }
        } while (c != ';' && !trouve);
    }


    /**
     *
     *  Recherche tous les batiment dans un fichier de config
     *
     *  @param nomFichier String : Le fichier de config qui contiens les info sur l'emplacement des capteurs
     *
     *  @return ArrayList<String> : Le tableai qui contiens tous les batiments concerné
     */
    public static ArrayList<String> getBatiments (String nomFichier) {
        ArrayList<String> res = new ArrayList<>();
        char c;
        String s = "";

        try {
            FileInputStream reader = new FileInputStream(nomFichier);
            startAnalyse(reader);

            do {
                c = (char) reader.read();
                if (c != '(') {
                    s =  s+c;
                } else {
                    res.add(s);
                    s = "";
                    nextLine(reader);
                }
            } while (c != ';');
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *  Recherche tous les étages diponible pour un batiment dans un fichier de config
     *
     *  @param nomFichier String : Le fichier de config qui contiens les info sur l'emplacement des capteurs
     *  @param batiment String   : Le label du batiement
     *
     *  @return ArrayList<String> : Le tableau qui contiens tous les etages concerné
     */
    public static ArrayList<String> getEtagesForBatoment (String nomFichier, String batiment) {
        ArrayList<String> res = new ArrayList<>();
        char c;
        String s = "";

        try {
            FileInputStream reader = new FileInputStream(nomFichier);
            c = (char) reader.read();
            startAnalyse(reader);
            findBatiment(reader, batiment);
            while (c != '\n') {
                c = (char) reader.read();
                if (c != '(') {
                    s = s + c;
                } else {
                    res.add(s);
                    s = "";
                    closeParanthesis(reader);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }


    /**
     *  Recherche toutes les salles diponible pour un étage d'un batiment dans un fichier de config
     *
     *
     *  @param nomFichier String : Le fichier de config qui contiens les info sur l'emplacement des capteurs
     *  @param batiment String   : Le label du batiement
     *  @param etage String      : Le label de l'étage
     *
     *  @return ArrayList<String> : Le tableau qui contiens toutes les salles concerné
     */
    public static ArrayList<String> getSallesForEtageAndBatoment (String nomFichier, String batiment, String etage) {
        ArrayList<String> res = new ArrayList<>();
        char c;
        String s = "";

        try {
            FileInputStream reader = new FileInputStream(nomFichier);
            c = (char) reader.read();
            startAnalyse(reader);
            findBatiment(reader, batiment);
            findEtage(reader, etage);
            while (c != ')') {
                c = (char) reader.read();
                if (c != '.' && c != ')') {
                    s = s + c;
                } else {
                    res.add(s);
                    s = "";
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    //main de test
    static public void main (String [] args) {
        ArrayList<String> a = Parseur.getBatiments("position_capteur.txt");
        System.out.println(a);
        a = Parseur.getEtagesForBatoment("position_capteur.txt", "U3");
        System.out.println(a);
        a = Parseur.getSallesForEtageAndBatoment("position_capteur.txt", "U3", "3iem etage");
        System.out.println(a);

    }

}

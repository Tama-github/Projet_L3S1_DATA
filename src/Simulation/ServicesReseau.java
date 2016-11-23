package Simulation;

import java.io.*;
import java.net.Socket;

/**
 * @autor Ludovic BURG
 */
public class ServicesReseau {
    /**
     * Socket de communication
     */
    private Socket socket;

    /**
     * Buffer de lecture du socket
     */
    private BufferedReader reader;

    /**
     * Buffer d'écriture du socket
     */
    private BufferedWriter writer;

    /**
     * Decris l'etat de la connexion
     */
    private boolean estConnecte = false;

    /**
     * Constructeur d'un service réseau
     */
    public ServicesReseau () {}

    /**
     * Connexion vers le serveur
     *
     * @param ip String : l'ip du serveur avec lequel on veux communiquer
     * @param port int  : Le port du serveur en question
     */
    public void connexion (String ip, int port) {
        if (this.estConnecte) this.deconnexion();
        try {
            this.socket = new Socket(ip, port);
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.estConnecte = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deconnexion au serveur et fermeture du socket
     */
    public void deconnexion () {
        if (this.estConnecte) {
            try {
                this.socket.close();
                this.estConnecte = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Envoie un message sur le socket
     *
     * @param m String : le message a envoyer <b>ATTENTION : il doit ce terminer par '\n'</b>
     * @throws IOException
     */
    public void envoyer (String m) throws IOException {
        if (this.estConnecte) {
            this.writer.write(m);
            this.writer.flush();
        }
    }

    /**
     * methode blocante qui attend la reception d'un message sur le socket
     *
     * @return String : si on est connecte, on retourne le prochain message du socket, si non on retourne "pas connecte"
     * @throws IOException
     */
    public String recevoir () throws IOException {
        if (this.estConnecte)
            return this.reader.readLine();
        else
            return "pas connecte";
    }

    public static void main (String [] args) {
        ServicesReseau sr = new ServicesReseau();
        sr.connexion("127.0.0.1", 43567);
        try {
            sr.envoyer("test"+'\n');
            Thread.sleep(4000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sr.deconnexion();
    }

}

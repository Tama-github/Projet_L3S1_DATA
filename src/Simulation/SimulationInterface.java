package Simulation;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by msi on 23/11/2016.
 */
public class SimulationInterface {

    /*
     *  Infos du capteur
     */
    private String id;
    private String type;
    private Localisation localisation;
    private int valeurMin;
    private int valeurMax;

    private ServicesReseau servicesReseau;
    private String ip;
    private int port;
    private int valeurEnvoie;

    private ParametresCapteur parametresCapteur;
    private FenetreConnexionIP fenetreConnexionIP;
    private FenetreGestionEnvoie fenetreGestionEnvoie;
    private EnvoieThread envoieThread;

    public SimulationInterface () {
        /* Gestionnaire des services reseau */
        this.servicesReseau = new ServicesReseau();

        /* fenetre de creation du capteur */
        this.parametresCapteur = new ParametresCapteur();

        /* creation de la fenetre de connexion IP */
        this.fenetreConnexionIP = new FenetreConnexionIP();
        this.fenetreConnexionIP.setVisible(false);

        /* creation de la fenetre de gestion d'envoie */
        this.fenetreGestionEnvoie = new FenetreGestionEnvoie();
        this.fenetreGestionEnvoie.setVisible(false);
        this.fenetreGestionEnvoie.getDeconnexion().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                callbackDeconnexion();
            }
        });

        this.fenetreGestionEnvoie.getEnvoie().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                envoieThread = new EnvoieThread(servicesReseau,
                        (int) fenetreGestionEnvoie.getFrequanceEnvoie().getValue(),
                        fenetreGestionEnvoie.isAlea(),
                        (int) fenetreGestionEnvoie.getValeur().getValue(),
                        valeurMin,
                        valeurMax);
                fenetreGestionEnvoie.getEnvoie().setText("rafraichir");
            }
        });


    }

    public String connexionCapteur () throws IOException {
        if (!this.servicesReseau.estConnecte()) {
            this.servicesReseau.connexion(this.ip, this.port);
            this.servicesReseau.envoyer("ConnexionCapteur;" + this.id + ";" + this.type + ";" + this.localisation.getStringForConnexion() + "\n");
        } else {
            throw new IOException();
        }
        return this.servicesReseau.recevoir();
    }

    public String deconnexionCapteur () throws IOException {
        String res = "DeconnexionKO";
        if (this.servicesReseau.estConnecte()) {
            this.servicesReseau.envoyer("DeconnexionCapteur;" + this.id + "\n");
            res = this.servicesReseau.recevoir();
            if (res.equals("DeconnexionOK")) {
                this.servicesReseau.deconnexion();
            }
        }
        return res;
    }

    public void callbackDeconnexion () {
        try {
            deconnexionCapteur();
        } catch (IOException e) {
            /*ne rien faire*/
        } finally {
            this.envoieThread.setRunning(false);
            this.fenetreGestionEnvoie.setVisible(false);
            this.fenetreGestionEnvoie.getEnvoie().setText("Envoie des donnees");
            this.fenetreConnexionIP.setVisible(true);
        }
    }


    public static void main (String [] args) {
        SimulationInterface simulationInterface = new SimulationInterface();
    }
}

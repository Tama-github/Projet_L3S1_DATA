package Simulation;

import javax.swing.*;
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
    /* fenetre gestion connexion */
    private FenetreGestionEnvoie fenetreGestionEnvoie;


    public SimulationInterface () {
        /* Gestionnaire des services reseau */
        this.servicesReseau = new ServicesReseau();

        /* fenetre de creation du capteur */
        this.parametresCapteur = new ParametresCapteur();


        /* creation de la fenetre de gestion d'envoie */
        this.fenetreGestionEnvoie = new FenetreGestionEnvoie();
        this.fenetreGestionEnvoie.setVisible(false);

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

    public void envoieDonneeCapteur () throws IOException {
        if (this.fenetreGestionEnvoie.isAlea()) {
            this.servicesReseau.envoyer("ValeurCapteur;" + (Math.random()*(this.valeurMax-this.valeurMin) + this.valeurMin) + "\n");
        } else {
            this.servicesReseau.envoyer("ValeurCapteur;" + this.valeurEnvoie + "\n");
        }
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



    public static void main (String [] args) {
        SimulationInterface simulationInterface = new SimulationInterface();
    }
}

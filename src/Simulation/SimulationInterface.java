package Simulation;

import javax.swing.*;

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
    /* fenetre creation capteur */
    /* fenetre gestion connexion */
    private FenetreGestionEnvoie fenetreGestionEnvoie;


    public SimulationInterface () {

        /* creation de la fenetre de gestion d'envoie */
        this.fenetreGestionEnvoie = new FenetreGestionEnvoie();
        this.fenetreGestionEnvoie.setVisible(false);

    }
}

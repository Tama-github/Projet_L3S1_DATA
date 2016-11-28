package Simulation;

import org.w3c.dom.html.HTMLOptGroupElement;

import javax.swing.*;
import java.awt.*;

/**
 * Created by msi on 28/11/2016.
 */
public class FenetreGestionEnvoie extends JFrame {
    private ButtonGroup typeGeneration;
    private JSpinner valeur;
    private JSpinner frequanceEnvoie;
    private JButton envoie;
    private JButton deconnexion;

    public FenetreGestionEnvoie () {
        super("Gestion d'envoie");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        GridLayout grid = new GridLayout(7,1);
        JPanel container = new JPanel();
        container.setLayout(grid);

        /* Boutons radios */
        this.typeGeneration = new ButtonGroup();
        JRadioButton alea = new JRadioButton("Generation aleatoire");
        JRadioButton fixe = new JRadioButton("Generation d'une valeur fixe");
        this.typeGeneration.add(alea);
        this.typeGeneration.add(fixe);
        container.add(alea);
        container.add(fixe);

        /* entre de la valeur du capteur */
        this.valeur = new JSpinner();
        this.valeur.enableInputMethods(false);
        container.add(this.valeur);

        /* Frequence d'envoie */
        JLabel freq = new JLabel("Frequance d'envoie");
        this.frequanceEnvoie = new JSpinner();
        container.add(freq);
        container.add(this.frequanceEnvoie);

        /* Boutons de controlle */
        this.envoie = new JButton("Envoie des donnees");
        this.deconnexion = new JButton("Deconnexion du capteur");
        container.add(this.envoie);
        container.add(this.deconnexion);

        this.add(container);
        this.pack();
    }

    public static void main (String args[]) {
        FenetreGestionEnvoie fge = new FenetreGestionEnvoie();
        fge.setVisible(true);
    }

}

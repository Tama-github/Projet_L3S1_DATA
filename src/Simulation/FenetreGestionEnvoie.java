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
        GridLayout grid = new GridLayout(4,1);
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(grid);

        GridLayout grid2 = new GridLayout(1, 2);
        JPanel subContainerButton = new JPanel();
        subContainerButton.setLayout(grid2);

        GridLayout grid3 = new GridLayout(1, 2);
        JPanel subContainerFixe = new JPanel();
        subContainerFixe.setLayout(grid3);

        GridLayout grid4 = new GridLayout(1, 2);
        JPanel subContainerFreq = new JPanel();
        subContainerFreq.setLayout(grid4);

        /* Boutons radios */
        this.typeGeneration = new ButtonGroup();
        JRadioButton alea = new JRadioButton("Generation aleatoire");
        JRadioButton fixe = new JRadioButton("Generation d'une valeur fixe");
        this.typeGeneration.add(alea);
        this.typeGeneration.add(fixe);
        mainContainer.add(alea);
        subContainerFixe.add(fixe);

        /* entre de la valeur du capteur */
        this.valeur = new JSpinner();
        this.enableEditValeur(false);
        subContainerFixe.add(this.valeur);

        mainContainer.add(subContainerFixe);

        /* Frequence d'envoie */
        JLabel freq = new JLabel("Frequance d'envoie");
        this.frequanceEnvoie = new JSpinner();
        subContainerFreq.add(freq);
        subContainerFreq.add(this.frequanceEnvoie);

        mainContainer.add(subContainerFreq);


        /* Boutons de controlle */
        this.envoie = new JButton("Envoie des donnees");
        this.deconnexion = new JButton("Deconnexion du capteur");
        subContainerButton.add(this.envoie);
        subContainerButton.add(this.deconnexion);

        mainContainer.add(subContainerButton);

        this.add(mainContainer);
        this.pack();
    }

    void enableEditValeur (boolean b) {
        ((JSpinner.DefaultEditor)this.valeur.getEditor()).getTextField().setEnabled(b);
    }

    public static void main (String args[]) {
        FenetreGestionEnvoie fge = new FenetreGestionEnvoie();
        fge.setVisible(true);
    }

}

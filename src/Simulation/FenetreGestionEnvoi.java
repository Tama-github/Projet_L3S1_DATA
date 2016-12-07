package Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by msi on 28/11/2016.
 */
public class FenetreGestionEnvoi extends JFrame {
    private boolean isAlea = true;
    private JSpinner valeur;
    private JSpinner frequenceEnvoi;
    private JButton envoi;
    private JButton deconnexion;
    private JLabel erreurText;

    public FenetreGestionEnvoi() {
        super("Gestion d'envoi");

        ImageIcon icone = new ImageIcon("icon.png");
        this.setIconImage(icone.getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setResizable(false);
        GridLayout grid = new GridLayout(5,1);
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

        /* Boutons radio */
        ButtonGroup typeGeneration = new ButtonGroup();
        JRadioButton alea = new JRadioButton("Génération aléatoire");
        JRadioButton fixe = new JRadioButton("Génération d'une valeur fixe");
        typeGeneration.add(alea);
        typeGeneration.add(fixe);
        mainContainer.add(alea);
        subContainerFixe.add(fixe);

        alea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isAlea = true;
                enableEditValeur(false);
            }
        });

        fixe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isAlea = false;
                enableEditValeur(true);
            }
        });

        /* entre de la valeur du capteur */
        this.valeur = new JSpinner();
        this.enableEditValeur(false);
        subContainerFixe.add(this.valeur);

        mainContainer.add(subContainerFixe);

        /* Frequence d'envoi */
        JLabel freq = new JLabel("Fréquence d'envoi (ms)");
        this.frequenceEnvoi = new JSpinner();
        subContainerFreq.add(freq);
        subContainerFreq.add(this.frequenceEnvoi);

        mainContainer.add(subContainerFreq);


        /* Boutons de controle */
        this.envoi = new JButton("Envoi des données");
        this.deconnexion = new JButton("Déconnexion du capteur");
        subContainerButton.add(this.envoi);
        subContainerButton.add(this.deconnexion);



        mainContainer.add(subContainerButton);

        this.erreurText = new JLabel("");

        mainContainer.add(this.erreurText);

        this.add(mainContainer);
        this.pack();
    }

    void enableEditValeur (boolean b) {
        ((JSpinner.DefaultEditor)this.valeur.getEditor()).getTextField().setEnabled(b);
    }

    public boolean isAlea() {
        return isAlea;
    }

    public JSpinner getValeur() {
        return valeur;
    }

    public JSpinner getFrequenceEnvoi() {
        return frequenceEnvoi;
    }

    public JButton getEnvoi() {
        return envoi;
    }

    public JButton getDeconnexion() {
        return deconnexion;
    }

    /**
     * Ecris un message d'erreur à l'endroit prévu a cet effet
     * @param msg : le message a ecrire
     */
    public void printErr(String msg) {
        this.erreurText.setForeground(Color.red);
        this.erreurText.setText(msg);
        this.setSize(500,150);
    }

    /**
     * 
     * @return JLabel : l'objet de text d'erreur
     */
    public JLabel getErreurText() {
        return erreurText;
    }

}

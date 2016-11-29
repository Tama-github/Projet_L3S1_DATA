package Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jb on 28/11/16.
 */
public class ParametresCapteur extends JFrame {


    private JFormattedTextField idCapteur = new JFormattedTextField();
    private JFormattedTextField typeDonnees = new JFormattedTextField();
    private JRadioButton interieur = new JRadioButton("Intérieur ");
    private JRadioButton exterieur =  new JRadioButton("Extérieur ");
    private JTextField latitude = new JTextField();
    private JTextField longitude = new JTextField();
    private JComboBox batiment = new JComboBox();
    private JComboBox etage = new JComboBox();
    private JComboBox salle = new JComboBox();
    private JFormattedTextField posRelative = new JFormattedTextField();
    private ButtonGroup intExt = new ButtonGroup();
    private JFormattedTextField min = new JFormattedTextField();
    private JFormattedTextField max = new JFormattedTextField();
    private JButton valider = new JButton("Valider");


    public ParametresCapteur()
    {
        super("Parametres Capteurs");

        JPanel pLocalisation = new JPanel();
        JPanel spLoc = new JPanel();
        JPanel spBatiment = new JPanel();
        JPanel spEtage = new JPanel();
        JPanel spSalle = new JPanel();
        JPanel spPositionRelative = new JPanel();
        JPanel spLongLat = new JPanel();
        JLabel lLocalisation = new JLabel("Localisation");
        JLabel lBatiment = new JLabel("Bâtiment ");
        JLabel lEtage = new JLabel("Étage ");
        JLabel lSalle = new JLabel("Salle ");
        JLabel lLongitude = new JLabel("Longitude ");
        JLabel lLatitude = new JLabel("Latitude ");
        JLabel lPosRelative = new JLabel("Position relative");
        JPanel princParam = new JPanel();
        JPanel pIdCapteur = new JPanel();
        JLabel lidCapteur = new JLabel("Identifiant du capteur");
        JPanel pTypeDonnees = new JPanel();
        JLabel lTypedonnees = new JLabel("Type de donnée");
        JPanel pInterval = new JPanel();
        JLabel lInter1 = new JLabel("Intervalle de ");
        JLabel lInter2 = new JLabel(" à ");
        JPanel pValider = new JPanel();






        this.setTitle("Parametres Capteurs");
        this.setSize(400,350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        princParam.setLayout(new FlowLayout(FlowLayout.LEFT));

        idCapteur.setColumns(20);
        pIdCapteur.add(lidCapteur);
        pIdCapteur.add(idCapteur);

        typeDonnees.setColumns(20);
        pTypeDonnees.add(lTypedonnees);
        pTypeDonnees.add(typeDonnees);

        pLocalisation.setLayout(new BoxLayout(pLocalisation, BoxLayout.Y_AXIS));
        posRelative.setColumns(20);
        latitude.setColumns(3);
        longitude.setColumns(3);
        batiment.setSize(10,10);
        intExt.add(interieur);
        intExt.add(exterieur);
        spBatiment.add(lBatiment);
        spBatiment.add(batiment);
        spEtage.add(lEtage);
        spEtage.add(etage);
        spSalle.add(lSalle);
        spSalle.add(salle);
        spLoc.add(lLocalisation);
        spLoc.add(exterieur);
        spLoc.add(interieur);


        spLoc.setLayout(new FlowLayout(FlowLayout.LEFT));
        spBatiment.setLayout(new FlowLayout(FlowLayout.LEFT));
        spEtage.setLayout(new FlowLayout(FlowLayout.LEFT));
        spSalle.setLayout(new FlowLayout(FlowLayout.LEFT));
        spLongLat.setLayout(new FlowLayout(FlowLayout.LEFT));
        spPositionRelative.setLayout(new FlowLayout(FlowLayout.LEFT));
        valider.setLayout(new FlowLayout(FlowLayout.CENTER));

        spPositionRelative.add(lPosRelative);
        spPositionRelative.add(posRelative);
        spLongLat.add(lLatitude);
        spLongLat.add(latitude);
        spLongLat.add(lLongitude);
        spLongLat.add(longitude);
        pLocalisation.add(spLoc);
        pLocalisation.add(spLongLat);
        pLocalisation.add(spBatiment);
        pLocalisation.add(spEtage);
        pLocalisation.add(spSalle);
        pLocalisation.add(spPositionRelative);

        min.setColumns(3);
        max.setColumns(3);
        pInterval.add(lInter1);
        pInterval.add(min);
        pInterval.add(lInter2);
        pInterval.add(max);

        pValider.add(valider);

        princParam.add(pIdCapteur);
        princParam.add(pTypeDonnees);
        princParam.add(pLocalisation);
        princParam.add(pInterval);
        princParam.add(pValider);


        this.setContentPane(princParam);

        this.setVisible(true);

    }

    static public void main(String[] args)
    {
        ParametresCapteur test = new ParametresCapteur();
    }

    public JFormattedTextField getIdCapteur() {
        return idCapteur;
    }

    public JFormattedTextField getTypeDonnees() {
        return typeDonnees;
    }

    public JRadioButton getInterieur() {
        return interieur;
    }

    public JRadioButton getExterieur() {
        return exterieur;
    }

    public JTextField getLatitude() {
        return latitude;
    }

    public JTextField getLongitude() {
        return longitude;
    }

    public JComboBox getBatiment() {
        return batiment;
    }

    public JComboBox getEtage() {
        return etage;
    }

    public JComboBox getSalle() {
        return salle;
    }

    public JFormattedTextField getPosRelative() {
        return posRelative;
    }

    public ButtonGroup getIntExt() {
        return intExt;
    }

    public JFormattedTextField getMin() {
        return min;
    }

    public JFormattedTextField getMax() {
        return max;
    }

    public JButton getValider() {
        return valider;
    }
}

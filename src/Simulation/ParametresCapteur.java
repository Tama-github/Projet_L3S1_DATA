package Simulation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jb on 28/11/16.
 */
public class ParametresCapteur extends JFrame {

    private JPanel princParam = new JPanel();

    private JPanel pIdCapteur = new JPanel();
    private JLabel lidCapteur = new JLabel("Identifiant du capteur");
    private JFormattedTextField idCapteur = new JFormattedTextField();

    private JPanel pTypeDonnees = new JPanel();
    private JLabel lTypedonnees = new JLabel("Type de donnée");
    private JFormattedTextField typeDonnees = new JFormattedTextField();

    private JPanel pLocalisation = new JPanel();
    private JPanel spLoc = new JPanel();
    private JPanel spBatiment = new JPanel();
    private JPanel spEtage = new JPanel();
    private JPanel spSalle = new JPanel();
    private JPanel spPositionRelative = new JPanel();
    private JPanel spLongLat = new JPanel();
    private JLabel lLocalisation = new JLabel("Localisation");
    private JLabel lBatiment = new JLabel("Bâtiment ");
    private JLabel lEtage = new JLabel("Étage ");
    private JLabel lSalle = new JLabel("Salle ");
    private JLabel lLongitude = new JLabel("Longitude ");
    private JLabel lLatitude = new JLabel("Latitude ");
    private JLabel lPosRelative = new JLabel("Position relative");
    private JRadioButton interieur = new JRadioButton("Intérieur ");
    private JRadioButton exterieur =  new JRadioButton("Extérieur ");
    private JTextField latitude = new JTextField();
    private JTextField longitude = new JTextField();
    private JComboBox batiment = new JComboBox();
    private JComboBox etage = new JComboBox();
    private JComboBox salle = new JComboBox();
    private JFormattedTextField posRelative = new JFormattedTextField();
    private ButtonGroup intExt = new ButtonGroup();

    private JPanel pInterval = new JPanel();
    private JLabel lInter1 = new JLabel("Intervalle de ");
    private JLabel lInter2 = new JLabel(" à ");
    private JFormattedTextField min = new JFormattedTextField();
    private JFormattedTextField max = new JFormattedTextField();

    private JPanel pValider = new JPanel();
    private JButton valider = new JButton("Valider");


    public ParametresCapteur()
    {
        super("Parametres Capteurs");

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


}

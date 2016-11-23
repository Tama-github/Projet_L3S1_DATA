package Simulation;

import javax.swing.*;

/**
 * Created by msi on 23/11/2016.
 */
public class SimulationInterface extends JFrame {

    /*
     *  Infos du capteur
     */
    private String id;
    private String type;
    private Localisation localisation;
    private int valeurMin;
    private int valeurMax;


    public SimulationInterface () {
        super("Interface de simulation");
    }
}

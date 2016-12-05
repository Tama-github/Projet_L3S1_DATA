package Simulation;

/**
 * Created by msi on 23/11/2016.
 */
public class LocalisationInterieur extends Localisation {
    private String batiment;
    private String etage;
    private String salle;
    private String infoSup;

    /**
     * Constructeur
     * @param type
     * @param batiment
     * @param etage
     * @param salle
     * @param infoSup
     * @throws Exception
     */
    public LocalisationInterieur (String type, String batiment, String etage, String salle, String infoSup) throws Exception {
        super(type);
        this.batiment = batiment;
        this.etage = etage;
        this.salle = salle;
        this.infoSup = infoSup;
    }

    @Override
    public String getStringForConnexion() {
        return this.batiment+";"+this.etage+";"+this.salle+";"+this.infoSup;
    }
}

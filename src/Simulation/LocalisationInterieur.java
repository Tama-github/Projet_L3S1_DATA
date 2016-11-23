package Simulation;

/**
 * Created by msi on 23/11/2016.
 */
public class LocalisationInterieur extends Localisation {
    private String batiment;
    private String etage;
    private String salle;
    private String infoSup;

    public LocalisationInterieur (String type) throws Exception {
        super(type);
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public void setInfoSup(String infoSup) {
        this.infoSup = infoSup;
    }
}

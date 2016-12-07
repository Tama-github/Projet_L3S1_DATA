package Simulation;

/**
 * Created by msi on 23/11/2016.
 */
public abstract class Localisation {
    private String type;
    public Localisation (String type) throws Exception{
        if (type.equals("exterieur") || type.equals("interieur")) {
            this.type = type;
        } else {
            throw new Exception("Localisation exception : localisation inconnu.");
        }
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @return String : chaine de caratères a envoyer sur le reseau
     */
    public abstract String getStringForConnexion();
}

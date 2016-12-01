package Simulation;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.acl.LastOwnerException;

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

    private ServicesReseau servicesReseau;
    private String ip;
    private int port;
    private int valeurEnvoie;

    private ParametresCapteur parametresCapteur;
    private FenetreConnexionIP fenetreConnexionIP;
    private FenetreGestionEnvoie fenetreGestionEnvoie;
    private EnvoieThread envoieThread = null;

    public SimulationInterface () {
        /* Gestionnaire des services reseau */
        this.servicesReseau = new ServicesReseau();

        /* fenetre de creation du capteur */
        this.parametresCapteur = new ParametresCapteur();

        /* creation de la fenetre de connexion IP */
        this.fenetreConnexionIP = new FenetreConnexionIP();
        this.fenetreConnexionIP.setVisible(false);

        /* creation de la fenetre de gestion d'envoie */
        this.fenetreGestionEnvoie = new FenetreGestionEnvoie();
        this.fenetreGestionEnvoie.setVisible(false);

        /* Gestion evenement de la fenetre de creatin du capteur */
        this.parametresCapteur.getValider().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent clickValider) {
                super.mouseClicked(clickValider);
                if (parametresCapteur.checkFormulaireParamCapteur()) {
                    parametresCapteur.getErreur().setText("");
                    parametresCapteur.setSize(400, 350);
                    id = parametresCapteur.getIdCapteur().getText();
                    type = parametresCapteur.getType().toString();
                    valeurMin = Integer.parseInt(parametresCapteur.getMin().getText());
                    valeurMax = Integer.parseInt(parametresCapteur.getMax().getText());

                    if (parametresCapteur.isExterieur()) {
                        try {
                            localisation = new LocalisationExterieur("exterieur", Double.parseDouble(parametresCapteur.getLatitude().getText()), Double.parseDouble(parametresCapteur.getLongitude().getText()));
                        } catch (Exception jLang1) {
                            jLang1.printStackTrace();
                        }
                    } else {
                        try {
                            localisation = new LocalisationInterieur("interieur",
                                    parametresCapteur.getBatiment().getSelectedItem().toString(),
                                    parametresCapteur.getEtage().getSelectedItem().toString(),
                                    parametresCapteur.getSalle().getSelectedItem().toString(),
                                    parametresCapteur.getPosRelative().getText());
                        } catch (Exception jLang2) {
                            jLang2.printStackTrace();
                        }
                    }
                    parametresCapteur.setVisible(false);
                    fenetreConnexionIP.setVisible(true);

                }
            }
        });

        /* Gestion d'evenement de la fenetre de connexion */
        this.fenetreConnexionIP.getButtonCancel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fenetreConnexionIP.setVisible(false);
                parametresCapteur.setVisible(true);
            }
        });

        this.fenetreConnexionIP.getButtonConnection().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (fenetreConnexionIP.verifIP(fenetreConnexionIP.getJtfIP().getText()) && (int)fenetreConnexionIP.getJtfPORT().getValue() >= 0) {
                    fenetreConnexionIP.getLabelError().setText("");
                    ip = fenetreConnexionIP.getJtfIP().getText();
                    port = (int)fenetreConnexionIP.getJtfPORT().getValue();

                    try {
                        if(connexionCapteur().equals("ConnexionKO")) {
                            fenetreConnexionIP.printErr("Erreur lors de la connexion au serveur");
                        } else {
                            fenetreConnexionIP.setVisible(false);
                            fenetreGestionEnvoie.setVisible(true);
                        }
                    } catch (IOException e1) {
                        fenetreConnexionIP.printErr("Erreur lors de la connexion au serveur");
                    }
                } else {
                    fenetreConnexionIP.printErr("L'adresse IP et le port doivent etre valide.");
                }
            }
        });

        /* Gestion des evenements de la fenetre d'envoie de donnees */
        this.fenetreGestionEnvoie.getDeconnexion().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                callbackDeconnexion();
            }
        });

        this.fenetreGestionEnvoie.getEnvoie().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (envoieThread != null)
                    envoieThread.interrupt();
                if (((int) fenetreGestionEnvoie.getFrequanceEnvoie().getValue() > 0) &&
                        (fenetreGestionEnvoie.isAlea() || (((int)fenetreGestionEnvoie.getValeur().getValue() > valeurMin) && (int)fenetreGestionEnvoie.getValeur().getValue() < valeurMax)))
                {
                    envoieThread = new EnvoieThread(servicesReseau,
                            (int) fenetreGestionEnvoie.getFrequanceEnvoie().getValue(),
                            fenetreGestionEnvoie.isAlea(),
                            (int) fenetreGestionEnvoie.getValeur().getValue(),
                            valeurMin,
                            valeurMax);

                    envoieThread.start();
                    fenetreGestionEnvoie.getEnvoie().setText("rafraichir");
                    fenetreGestionEnvoie.getErreurText().setText("");
                } else {
                    if (((int) fenetreGestionEnvoie.getFrequanceEnvoie().getValue() <= 0))
                        fenetreGestionEnvoie.printErr("La frequence doit être supérieur a 0.");
                    else
                        fenetreGestionEnvoie.printErr("Valeur incorrecte, intervale : "+ valeurMin + " et " + valeurMax);
                }
            }
        });
    }

    public String connexionCapteur () throws IOException {
        String res = "";
        if (!this.servicesReseau.estConnecte()) {
            this.servicesReseau.connexion(this.ip, this.port);
            this.servicesReseau.envoyer("ConnexionCapteur;" + this.id + ";" + this.type + ";" + this.localisation.getStringForConnexion() + "\n");
        } else {
            throw new IOException();
        }
        res = this.servicesReseau.recevoir();
        if (res.equals("ConnexionKO")) {
            this.servicesReseau.deconnexion();
        }
        return res;
    }

    public String deconnexionCapteur () throws IOException {
        String res = "DeconnexionKO";
        if (this.servicesReseau.estConnecte()) {
            this.servicesReseau.envoyer("DeconnexionCapteur;" + this.id + "\n");
            res = this.servicesReseau.recevoir();
            if (res.equals("DeconnexionOK")) {
                this.servicesReseau.deconnexion();
            } else {
                this.servicesReseau.deconnexion();
            }
        }
        return res;
    }

    public void callbackDeconnexion () {
        try {
            deconnexionCapteur();
        } catch (IOException e) {
            /*ne rien faire*/
        } finally {
            if (this.envoieThread != null && this.envoieThread.isRunning())
                this.envoieThread.setRunning(false);
            this.fenetreGestionEnvoie.setVisible(false);
            this.fenetreGestionEnvoie.getEnvoie().setText("Envoie des donnees");
            this.fenetreConnexionIP.setVisible(true);
        }
    }


    public static void main (String [] args) {
        SimulationInterface simulationInterface = new SimulationInterface();
    }
}

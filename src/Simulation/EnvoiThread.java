package Simulation;


import java.io.IOException;

/**
 * Thread qui gère l'envoi des données au serveur
 * @autor Ludovic BURG
 */
public class EnvoiThread extends Thread implements Runnable {
    private ServicesReseau servicesReseau;
    private int temps;
    private boolean alea;
    private int data;
    private int min;
    private int max;
    private boolean running = true;

    public EnvoiThread(ServicesReseau servicesReseau, int temps, boolean alea, int data, int min, int max) {
        this.servicesReseau = servicesReseau;
        this.temps = temps;
        this.alea = alea;
        this.data = data;
        this.max = max;
        this.min = min;
    }

    @Override
    public void run() {
        try {
            while (this.running) {
                servicesReseau.envoyer(this.getStringPourEnvoi());
                Thread.sleep(this.temps);
            }
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * produit la chaine de caractère a envoyer au server.
     *
     * @return String : si on est connecte, on retourne le prochain message du socket, si non on retourne "pas connecte"
     * @throws IOException
     */
    public String getStringPourEnvoi() throws IOException {
        if (this.alea) {
            return "ValeurCapteur;" + (Math.random()*(this.max-this.min) + this.min) + "\n";
        } else {
            return "ValeurCapteur;" + this.data + "\n";
        }
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public void setAlea(boolean alea) {
        this.alea = alea;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}

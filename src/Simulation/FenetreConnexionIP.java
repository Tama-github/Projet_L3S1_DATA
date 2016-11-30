package Simulation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FenetreConnexionIP extends JFrame {
	private JPanel container = new JPanel();
	private JPanel ligneHaut = new JPanel();
	private JPanel ligneHautG = new JPanel();
	private JPanel ligneHautD = new JPanel();
	private JPanel ligneBas = new JPanel();
	
	private JLabel labelError = new JLabel();

	Pattern ipPattern = Pattern.compile("^([0-9]{1,3}\\.){3}[0-9]{1,3}$");
	private JTextField jtfIP = new JTextField();
	private JLabel label1 = new JLabel("Adresse IP : ");
	
	private JTextField jtfPORT = new JTextField();
	private JLabel label2 = new JLabel("     Port : ");
	
	private JButton buttonConnection = new JButton("Connexion");
	private JButton buttonCancel = new JButton("Annuler");
	
	
	public FenetreConnexionIP(){
		
		this.setTitle("Connexion");
		this.setSize(500, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);


		Font police = new Font("Arial", Font.BOLD, 12);
		jtfIP.setFont(police);
		jtfIP.setPreferredSize(new Dimension(125, 30));	// La taille n'est pas respectée pour raison inconnue...
		jtfIP.setForeground(Color.BLACK);
		
		jtfPORT.setFont(police);
		jtfPORT.setPreferredSize(new Dimension(125, 30));	// La taille n'est pas respectée pour raison inconnue...
		jtfPORT.setForeground(Color.BLACK);
		
		
		ligneHaut.setPreferredSize(new Dimension(125, 30));
		ligneHaut.setLayout(new BoxLayout(ligneHaut, BoxLayout.LINE_AXIS));
		ligneHautG.add(label1);
		ligneHautG.add(jtfIP);
		ligneHautD.add(label2);
		ligneHautD.add(jtfPORT);
		ligneHaut.add(ligneHautG);
		ligneHaut.add(ligneHautD);
		
		
		ligneBas.setLayout(new BoxLayout(ligneBas, BoxLayout.LINE_AXIS));
		ligneBas.add(buttonConnection);
		ligneBas.add(buttonCancel);
	    

	    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
	    container.add(ligneHaut);
	    container.add(ligneBas);
			
	    this.getContentPane().add(container);
	}
	
	
	public JTextField getJtfIP() {
		return this.jtfIP;
	}
	

	public JTextField getJtfPORT() {
		return this.jtfPORT;
	}
	

	public JButton getButtonConnection() {
		return this.buttonConnection;
	}
	

	public JButton getButtonCancel() {
		return this.buttonCancel;
	}
	
	
	public boolean verifIP () {
		// À faire
		return false;
	}


	public void mouseExited(MouseEvent event) {

		//Nous changeons le fond de notre image pour le vert lorsque nous quittons le bouton, avec le fichier fondBouton.png

		/*try {

			img = ImageIO.read(new File("fondBouton.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}*/

	}


	public static void main(String[] args) {
		JFrame a = new FenetreConnexionIP();
		return;
	}
}

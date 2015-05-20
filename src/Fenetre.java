import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



	


public class Fenetre extends JFrame implements ActionListener {
	
	private JTextField tf_nbrAleatoire;
	private JTextField tf_rayonAleatoire;
	private JTextField tf_rayonManuel;
	
	private JLabel lb_menu;
	private JLabel lb_aleatoire;
	private JLabel lb_aleatoireSuite;
	private JLabel lb_manuel; 
	
	private JButton bu_aleatoire;
	private JButton bu_manuel;
	
	
	public static void main(String argv[]) {

		Fenetre fenetreDeMenu = new Fenetre();
	    fenetreDeMenu.setVisible(true);

	  }
	
	
	public Fenetre() {

		// Paramètres généraux de la fenêtre
		this.setTitle("Simulateur de capteurs");
		this.setSize(765,145);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		//Création du panel d'en-tête
		JPanel panelMenu = new JPanel();
		BoxLayout bl = new BoxLayout(panelMenu, BoxLayout.PAGE_AXIS);
		panelMenu.setLayout(bl);
		
		//Label "MENU"
		lb_menu = new JLabel("MENU");
		panelMenu.add(lb_menu);
		
		this.getContentPane().add(panelMenu);
		
		
		//Création du panel du placement aléatoire des points 		
		JPanel panelAleatoire = new JPanel();
		panelAleatoire.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Labels, TextFields et Button
		lb_aleatoire = new JLabel("Placer");
		panelAleatoire.add(lb_aleatoire);
		
		tf_nbrAleatoire = new JTextField();
		panelAleatoire.add(tf_nbrAleatoire);
		tf_nbrAleatoire.setColumns(3);
		
		lb_aleatoireSuite = new JLabel("points aléatoirement avec un rayon de communication de :");
		panelAleatoire.add(lb_aleatoireSuite);
		
		tf_rayonAleatoire = new JTextField();
		panelAleatoire.add(tf_rayonAleatoire);
		tf_rayonAleatoire.setColumns(3);
		
		bu_aleatoire = new JButton("Placer aléatoirement");
		panelAleatoire.add(bu_aleatoire);
		bu_aleatoire.addActionListener(this);
		
		this.getContentPane().add(panelAleatoire);
		
		
		//Création du panel du placement manuel
		JPanel panelManuel = new JPanel();
		panelManuel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lb_manuel = new JLabel("Assigner les points manuellement avec un rayon de communication de :");
		panelManuel.add(lb_manuel);
		
		tf_rayonManuel = new JTextField();
		panelManuel.add(tf_rayonManuel);
		tf_rayonManuel.setColumns(3);
		
		bu_manuel = new JButton("Placer manuellement");
		panelManuel.add(bu_manuel);
		
		this.getContentPane().add(panelManuel);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		int valeur_nbrCapteurs = 0;
		int valeur_rayon = 0;
		
		try 
			{ 
				valeur_nbrCapteurs = Integer.parseInt(tf_nbrAleatoire.getText());
				valeur_rayon = Integer.parseInt(tf_rayonAleatoire.getText());
				Simulation fenetreCapteurs = new Simulation("Simulation", valeur_nbrCapteurs, valeur_rayon);
				fenetreCapteurs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetreCapteurs.setSize(1000,1000);
				
				fenetreCapteurs.setVisible(true);
			}
		catch(NumberFormatException nb){
			
			JOptionPane.showMessageDialog(null, "Veuillez saisir un entier pour le nombre de capteurs et la valeur du rayon !");
		}
		
		
	
			
			
			
	}
}

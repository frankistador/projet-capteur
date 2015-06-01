import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



	


public class Fenetre extends JFrame implements ActionListener {
	
	private JTextField tf_nbrAleatoire;
	private JTextField tf_rayonAleatoire;
	private JTextField tf_rayonManuel;
	private JTextField tf_largeur;
	private JTextField tf_hauteur;
	
	private JLabel lb_menu;
	private JLabel lb_aleatoire;
	private JLabel lb_aleatoireSuite;
	private JLabel lb_redim;
	private JLabel lb_manuel;
	private JLabel lb_x;
	private JLabel lb_y;
	
	private JButton bu_aleatoire;
	private JButton bu_manuel;
	private JButton bu_clean;
	private JButton bu_redim;
	
	private JPanel panelPrincipal = new JPanel();
	
	private int rang_capteur = 0 ;
	
	public static int largeur_panel = 800 ;
	public static int hauteur_panel = 800 ;
	private int taille_menu = 202;
	
	private Dessin window = new Dessin();
	
	public static void main(String argv[]) {

		Fenetre fenetreDeMenu = new Fenetre();
	    fenetreDeMenu.setVisible(true);

	 }
	
	
	public Fenetre() {

		// Paramètres généraux de la fenêtre
		this.setTitle("Simulateur de capteurs");
		this.setSize(largeur_panel,taille_menu);
		this.setResizable(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		window.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Création du panel Menu
		JPanel panelMenu = new JPanel();
		BoxLayout bl = new BoxLayout(panelMenu, BoxLayout.PAGE_AXIS);
		panelMenu.setLayout(bl);
		
		//Label "MENU"
		lb_menu = new JLabel("MENU");
		panelMenu.add(lb_menu);
		

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
		
		panelMenu.add(panelAleatoire);
		
		
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
		bu_manuel.addActionListener(this);
		panelMenu.add(panelManuel);
		
		//Redimensionner le panel
		JPanel panelDimension = new JPanel();
		panelDimension.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lb_redim = new JLabel("Taille de la fenêtre :");
		panelDimension.add(lb_redim);
		
		tf_largeur = new JTextField();
		panelDimension.add(tf_largeur);
		tf_largeur.setColumns(4);
		tf_largeur.setText("800");
		
		tf_hauteur = new JTextField();
		panelDimension.add(tf_hauteur);
		tf_hauteur.setColumns(4);
		tf_hauteur.setText("800");
		
		bu_redim = new JButton("Redimensionner");
		panelDimension.add(bu_redim);
		bu_redim.addActionListener(this);
		panelMenu.add(panelDimension);
		
		//Panel Principal
		BorderLayout borderL = new BorderLayout();
		panelPrincipal.setLayout(borderL);
		panelPrincipal.add(panelMenu, BorderLayout.PAGE_START);
		this.getContentPane().add(panelPrincipal);
		
		lb_x = new JLabel("X : ");
		panelMenu.add(lb_x);
		lb_y = new JLabel("Y : ");
		panelMenu.add(lb_y);
		
		bu_clean = new JButton("Clean");
		panelMenu.add(bu_clean);
		bu_clean.addActionListener(this);
	}

	
	public void redimensionner (int larg, int haut){
		this.setSize(larg, haut);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		window.addMouseMotionListener(new MouseAdapter() {

			public void mouseMoved(MouseEvent me) {
				lb_x.setText("X : "+me.getX());
				lb_y.setText("Y : "+me.getY());
				lb_x.setVisible(true);
				lb_y.setVisible(true);
			}
		});

		// Pression du button "Placer aleatoirement"
		if (e.getActionCommand().equals(bu_aleatoire.getActionCommand())) {

			int valeur_nbrCapteurs = 0;
			int valeur_rayon = 0;

			try {
				valeur_nbrCapteurs = Integer.parseInt(tf_nbrAleatoire.getText());
				valeur_rayon = Integer.parseInt(tf_rayonAleatoire.getText());
				String name;
				
				int somme = valeur_nbrCapteurs+rang_capteur ;
				
				for (int i = rang_capteur; i < somme; i++) {
										
					name = "Capteur" + rang_capteur;
					window.addCapteur(name, valeur_rayon, rang_capteur,false);
					rang_capteur += 1;
				}
				window.lancementSimulation();
				panelPrincipal.add(window, BorderLayout.CENTER);
				this.setSize(largeur_panel, hauteur_panel + taille_menu);
				panelPrincipal.repaint();

			} catch (NumberFormatException nb) {

				JOptionPane.showMessageDialog(null,"Veuillez saisir un entier pour le nombre de capteurs et la valeur du rayon du mode aléatoire !");
			}

		}

		// Pression du button "Placer manuellement"
		if (e.getActionCommand().equals(bu_manuel.getActionCommand())) {

			try {

				panelPrincipal.add(window, BorderLayout.CENTER);
				this.setSize(largeur_panel, hauteur_panel + taille_menu);
				window.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent me) {
						int valeur_rayon = Integer.parseInt(tf_rayonManuel.getText());

						int mouseX = me.getX();
						int mouseY = me.getY();

						Capteur capteur = new Capteur(rang_capteur, "Capteur "+ rang_capteur, valeur_rayon, mouseX, mouseY,false);
						capteur.setHauteur_panel(hauteur_panel);
						capteur.setLargeur_panel(largeur_panel);
						window.addCapteur(capteur);
						rang_capteur += 1;
						window.lancementSimulation();
						panelPrincipal.revalidate();
						panelPrincipal.repaint();
						
					}
				
				});

				panelPrincipal.repaint();

			} catch (NumberFormatException nb) {

				JOptionPane.showMessageDialog(null,"Veuillez saisir un entier pour la valeur du rayon du mode manuel !");
			}

		}

		// Pression du button "Clean"
		if (e.getActionCommand().equals(bu_clean.getActionCommand())) {

			window.clean();
			panelPrincipal.repaint();

		}
		
		//pression du button "Redimensionner"
		if (e.getActionCommand().equals(bu_redim.getActionCommand())) {
			largeur_panel = Integer.parseInt(tf_largeur.getText());
			hauteur_panel = Integer.parseInt(tf_hauteur.getText());
			
			if (largeur_panel < 800) { 
				JOptionPane.showMessageDialog(null,"Veuillez saisir une largeur supérieure à 800 !");
			}
			else {
				int n = JOptionPane.showConfirmDialog(null, "Attention cette opération va réinitialiser le plan", "Avertissement", JOptionPane.OK_CANCEL_OPTION);
				
				if(n == JOptionPane.OK_OPTION){ 
					window.clean();
					window.redimensionner(largeur_panel, hauteur_panel);
					this.redimensionner(hauteur_panel, hauteur_panel + taille_menu); 
			    }			
				
			}

			
		}
	}

	

	public static int getLargeur_panel() {
		return largeur_panel;
	}


	public void setLargeur_panel(int largeur_panel) {
		this.largeur_panel = largeur_panel;
	}


	public static int getHauteur_panel() {
		return hauteur_panel;
	}


	public void setHauteur_panel(int hauteur_panel) {
		this.hauteur_panel = hauteur_panel;
	}
	
}

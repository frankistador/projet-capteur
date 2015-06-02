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
import javax.swing.Timer;

@SuppressWarnings("serial")
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
	private JButton bu_simul;

	private JPanel panelPrincipal = new JPanel();
	private JPanel panelMenu = new JPanel();
	private JPanel panelAleatoire = new JPanel();
	private JPanel panelManuel = new JPanel();
	private JPanel panelDimension = new JPanel();
	private JPanel panelSimulation = new JPanel();

	public static int largeur_panel = 800;
	public static int hauteur_panel = 800;
	private int taille_menu = 215;
	private int rang_capteur = 0;

	private Dessin window = new Dessin();
	
	private Timer t = new Timer(5, this);

	private final int LARGEUR_MINIMUM = 800;
	private final int HAUTEUR_MINIMUM = 800;

	public static void main(String argv[]) {

		Fenetre fenetreDeMenu = new Fenetre();
		fenetreDeMenu.setVisible(true);

	}

	public Fenetre() {

		// Paramètres généraux de la fenêtre
		this.setTitle("Simulateur de capteurs");
		this.setSize(largeur_panel, taille_menu);
		this.setResizable(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		window.setBorder(BorderFactory.createLineBorder(Color.black));

		// Création du panel Menu
		panelMenu = new JPanel();
		BoxLayout bl = new BoxLayout(panelMenu, BoxLayout.PAGE_AXIS);
		panelMenu.setLayout(bl);

		// Label "MENU"
		lb_menu = new JLabel("MENU");
		panelMenu.add(lb_menu);

		// Création du panel du placement aléatoire des points
		panelAleatoire = new JPanel();
		panelAleatoire.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Labels, TextFields et Button
		lb_aleatoire = new JLabel("Placer");
		panelAleatoire.add(lb_aleatoire);

		tf_nbrAleatoire = new JTextField();
		panelAleatoire.add(tf_nbrAleatoire);
		tf_nbrAleatoire.setColumns(3);

		lb_aleatoireSuite = new JLabel(
				"points aléatoirement avec un rayon de communication de :");
		panelAleatoire.add(lb_aleatoireSuite);

		tf_rayonAleatoire = new JTextField();
		panelAleatoire.add(tf_rayonAleatoire);
		tf_rayonAleatoire.setColumns(3);

		bu_aleatoire = new JButton("Placer aléatoirement");
		panelAleatoire.add(bu_aleatoire);
		bu_aleatoire.addActionListener(this);

		panelMenu.add(panelAleatoire);

		// Création du panel du placement manuel
		panelManuel = new JPanel();
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

		// Creation du panel de redimension
		panelDimension = new JPanel();
		panelDimension.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Labels, TextField, Button
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

		lb_x = new JLabel("X : ");
		panelMenu.add(lb_x);
		lb_y = new JLabel("Y : ");
		panelMenu.add(lb_y);

		// Creation du panel de simulation
		panelSimulation = new JPanel();
		panelSimulation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bu_simul = new JButton("Lancer la simulation");
		panelSimulation.add(bu_simul);
		bu_simul.addActionListener(this);
		bu_clean = new JButton("Stop & Clean");
		panelSimulation.add(bu_clean);
		bu_clean.addActionListener(this);

		panelMenu.add(panelSimulation);

		// Panel Principal
		BorderLayout borderL = new BorderLayout();
		panelPrincipal.setLayout(borderL);

		panelPrincipal.add(panelMenu, BorderLayout.PAGE_START);
		this.getContentPane().add(panelPrincipal);

	}
	
	
	//Definition des actions lors d'un evenement
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Affichage des coordonnées lorsque la souris se déplace sur le graphe
		window.addMouseMotionListener(new MouseAdapter() {

			public void mouseMoved(MouseEvent me) {
				lb_x.setText("X : " + me.getX());
				lb_y.setText("Y : " + me.getY());
				lb_x.setVisible(true);
				lb_y.setVisible(true);
			}
		});

		//Si c'est le timer qui declenche l'evenement
		if(e.getSource().getClass() == t.getClass()){
			panelPrincipal.revalidate();
			panelPrincipal.repaint();
		
		}else{
		
		
			// Pression du button "Placer aleatoirement"
			if (e.getActionCommand().equals(bu_aleatoire.getActionCommand())) {
	
				int valeur_nbrCapteurs = 0;
				int valeur_rayon = 0;
	
				try {
					valeur_nbrCapteurs = Integer.parseInt(tf_nbrAleatoire.getText());
					valeur_rayon = Integer.parseInt(tf_rayonAleatoire.getText());
					String name;
	
					int somme = valeur_nbrCapteurs + rang_capteur;
	
					for (int i = rang_capteur; i < somme; i++) {
	
						name = "Capteur" + rang_capteur;
						window.addCapteur(name, valeur_rayon*2, rang_capteur, false);
						rang_capteur += 1;
					}
					panelPrincipal.add(window, BorderLayout.CENTER);
					this.redimensionner();
					panelPrincipal.repaint();
	
				} catch (NumberFormatException nb) {
	
					JOptionPane.showMessageDialog(null,"Veuillez saisir un entier pour le nombre de capteurs et la valeur du rayon du mode aléatoire !");
				}
	
			}
	
			// Pression du button "Placer manuellement"
			if (e.getActionCommand().equals(bu_manuel.getActionCommand())) {
	
				try {
					Integer.parseInt(tf_rayonManuel.getText());
					panelPrincipal.add(window, BorderLayout.CENTER);
					this.redimensionner();
					window.addMouseListener(new MouseAdapter() {
	
						public void mouseClicked(MouseEvent me) {
							int valeur_rayon = Integer.parseInt(tf_rayonManuel.getText());
	
							int mouseX = me.getX();
							int mouseY = me.getY();
							Capteur capteur = new Capteur(rang_capteur, "Capteur "+ rang_capteur, valeur_rayon*2, mouseX, mouseY,false);
							window.addCapteur(capteur);
							rang_capteur += 1;
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
				t.stop();
			}
	
			//Pression du button "Lancer Simulation"
			if (e.getActionCommand().equals(bu_simul.getActionCommand())) {
				window.lancementSimulation();
				t.start();
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
	
			// Pression du button "Redimensionner"
			if (e.getActionCommand().equals(bu_redim.getActionCommand())) {
	
				if ( Integer.parseInt(tf_largeur.getText()) < LARGEUR_MINIMUM) {
					JOptionPane.showMessageDialog(null,"Veuillez saisir une largeur supérieure à 800 !");
					
				} else if (Integer.parseInt(tf_hauteur.getText()) < HAUTEUR_MINIMUM) {
					JOptionPane.showMessageDialog(null,"Veuillez saisir une hauteur supérieure à 800 !");
					
				} else {
					int n = JOptionPane.showConfirmDialog(null,"Attention cette opération va réinitialiser le plan","Avertissement", JOptionPane.OK_CANCEL_OPTION);
	
					if (n == JOptionPane.OK_OPTION) {
						largeur_panel = Integer.parseInt(tf_largeur.getText());
						hauteur_panel = Integer.parseInt(tf_hauteur.getText());
						window.clean();
						window.redimensionner(largeur_panel, hauteur_panel);
						this.redimensionner();
					}
	
				}
			}
			
			
			}
	}

	public static int getLargeur_panel() {
		return largeur_panel;
	}

	public static int getHauteur_panel() {
		return hauteur_panel;
	}

	public void redimensionner() {
		
		if( (Integer.parseInt(tf_largeur.getText()) >= LARGEUR_MINIMUM) && (Integer.parseInt(tf_hauteur.getText()) >= HAUTEUR_MINIMUM ) ){
			
			int hauteur_totale = hauteur_panel + taille_menu;
			this.setSize(largeur_panel, hauteur_totale);
		}else {
			JOptionPane.showMessageDialog(null,"Veuillez saisir des valeurs supérieures à 800 !");
		}
		
		
	}
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



	


public class Fenetre extends JFrame implements ActionListener{
	
	private JTextField tf_nbrAleatoire;
	private JTextField tf_rayonAleatoire;
	private JTextField tf_rayonManuel;
	
	private JLabel lb_menu;
	private JLabel lb_aleatoire;
	private JLabel lb_aleatoireSuite;
	private JLabel lb_manuel; 
	
	private JButton bu_aleatoire;
	private JButton bu_manuel;
	private JButton bu_clean;
	
	private JPanel panelPrincipal = new JPanel();
	
	private int rang_capteur = 0 ;
	
	private Dessin window = new Dessin();
	
	public static void main(String argv[]) {

		Fenetre fenetreDeMenu = new Fenetre();
	    fenetreDeMenu.setVisible(true);

	  }
	
	
	public Fenetre() {

		// Paramètres généraux de la fenêtre
		this.setTitle("Simulateur de capteurs");
		this.setSize(800,145);
		this.setResizable(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
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
		
		//Panel Principal
		BorderLayout borderL = new BorderLayout();
		panelPrincipal.setLayout(borderL);
		panelPrincipal.add(panelMenu, BorderLayout.PAGE_START);
		this.getContentPane().add(panelPrincipal);
		

		bu_clean = new JButton("Clean");
		panelMenu.add(bu_clean);
		bu_clean.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		// Pression du button "Placer aleatoirement"
		if (e.getActionCommand().equals(bu_aleatoire.getActionCommand())) {

			int valeur_nbrCapteurs = 0;
			int valeur_rayon = 0;

			window.clean();
			panelPrincipal.repaint();

			try {
				valeur_nbrCapteurs = Integer.parseInt(tf_nbrAleatoire.getText());
				valeur_rayon = Integer.parseInt(tf_rayonAleatoire.getText());
				String name;
				
				int somme = valeur_nbrCapteurs+rang_capteur ;
				
				for (int i = rang_capteur; i < somme; i++) {
					
					
					
					name = "Capteur" + rang_capteur;
					window.addCapteur(name, valeur_rayon, rang_capteur);
					System.out.println("------- ");
					rang_capteur += 1;
				}
				panelPrincipal.add(window, BorderLayout.CENTER);
				this.setSize(800, 945);
				panelPrincipal.repaint();

			} catch (NumberFormatException nb) {

				JOptionPane.showMessageDialog(null,"Veuillez saisir un entier pour le nombre de capteurs et la valeur du rayon du mode aléatoire !");
			}

		}

		// Pression du button "Placer manuellement"
		if (e.getActionCommand().equals(bu_manuel.getActionCommand())) {

			try {

				final int valeur_rayon = Integer.parseInt(tf_rayonManuel.getText());

				panelPrincipal.add(window, BorderLayout.CENTER);
				this.setSize(800, 945);
				window.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent me) {
						int valeur_rayon = Integer.parseInt(tf_rayonManuel.getText());

						int mouseX = me.getX();
						int mouseY = me.getY();

						Capteur capteur = new Capteur(rang_capteur, "Capteur "+ rang_capteur, valeur_rayon, mouseX, mouseY);
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

		}

	}

}

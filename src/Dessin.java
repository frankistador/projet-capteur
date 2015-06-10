import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Dessin extends JPanel {

	private Capteur newCapteur;
	private LinkedList<Capteur> threads = new LinkedList<Capteur>();

	public Dessin() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		java.util.ListIterator<Capteur> it = threads.listIterator();
		while (it.hasNext()) {
			it.next().draw(g2D);

		}
	
	}

	public void addCapteur(String name, int rayon, int id, boolean bip) {
		newCapteur = new Capteur(id, name, rayon, bip);
		threads.add(newCapteur);
	}

	public void addCapteur(Capteur capteur) {
		threads.add(capteur);
	}

	public void redimensionner(int larg, int haut) {
		this.setSize(larg, haut);

	}
	
	public void stopperCapteurs(){
		 java.util.ListIterator<Capteur> it = threads.listIterator();
	        while(it.hasNext())
	        {
	        		it.next().interrupt();
	        		
	        }
	}
	
	public void lancerCapteurs(){
		 java.util.ListIterator<Capteur> it = threads.listIterator();
	        while(it.hasNext())
	        {
	        		it.next().start();
	        		
	        }
	}

  	    
	  public void clean(){
		  java.util.ListIterator<Capteur> it = threads.listIterator();
	        while(it.hasNext())
	        {
	        		it.next().interrupt();
	        		
	        }
		  threads.clear();
		  Beep.clear();
		  this.repaint();
		  this.revalidate();
	  }
	
}

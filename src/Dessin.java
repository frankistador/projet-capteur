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

	public void clean() {
		threads.clear();
	}

	public void redimensionner(int larg, int haut) {
		this.setSize(larg, haut);

	}

	public void lancementSimulation() {
		int cpt = 0;
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();

			for (int j = 0; j < threads.size(); j++) {
				if (i != j) {
					if (threads.get(i).getCircle().contains(threads.get(j).getCoordX(),threads.get(j).getCoordY())&& threads.get(j).isBip()) {
						if (!threads.get(i).isBip()) {
							threads.get(i).setReceiving(true);
							cpt++;
						} else {
							threads.get(i).setInternalCollision(true);
						}
					}
				}
			}
			if (cpt > 1) {
				threads.get(i).setPeripheralCollision(true);
				threads.get(i).setReceiving(false);
			}
			cpt = 0;

		}
	}
	
	
	public void stopperCapteurs(){
		for (Capteur capteur : threads) {
			capteur.interrupt();
		}
	}
	
//	public void startCapteurs(){
//		threads.notifyAll();
//	}
}

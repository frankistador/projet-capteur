import java.awt.Graphics;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Simulation extends JFrame {

	private int nb_capteurs;
	private int rayon;

	public Simulation(String titre) {
		super(titre);
	}

	public Simulation(String titre, int nb_capteurs, int rayon) {
		super(titre);
		this.nb_capteurs = nb_capteurs;
		this.rayon = rayon;
	}

	public void paint(Graphics g) {

		int aleatX;
		int aleatY;
		int rayon = this.rayon;
		String name;
		Thread[] threads = new Thread[nb_capteurs];

		for (int i = 0; i < this.nb_capteurs; i++) {
			name = "Capteur" + i;
			aleatX = (int) (Math.random() * (900 - 100 + 1)) + 100;
			aleatY = (int) (Math.random() * (900 - 100 + 1)) + 100;
			threads[i] = new Thread(new Capteur(i, name, aleatX, aleatY, rayon));
			System.out.println("------------------> ID : " + threads[i].getId());
			g.fillOval(aleatX, aleatY, 5, 5);
			g.drawOval(aleatX-rayon/2, aleatY-rayon/2, rayon, rayon);
			threads[i].start();
			
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

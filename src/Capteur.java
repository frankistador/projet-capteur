import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class Capteur extends Thread {

	private String nameCapteur;

	private int idCapteur;
	private int coordX;
	private int coordY;
	private int rayon;

	private boolean bip;
	private boolean receiving = false;
	private boolean peripheralCollision = false;
	private boolean internalCollision = false;

	private Circle circle;
	private Timer timer;
	private Circle waveOne;


	//Constructeur avec coordonnees aleatoires
	public Capteur(int idCapteur, String nameCapteur, int rayon, boolean bip) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = (int) (Math.random() * Fenetre.getLargeur_panel());
		this.coordY = (int) (Math.random() * Fenetre.getHauteur_panel());
		this.rayon = rayon;
		this.bip = bip;
		this.circle = new Circle(coordX, coordY, rayon,false);
		this.waveOne = new Circle(coordX,coordY,rayon,true);
		this.timer = createTimer();
		timer.start();
	}
	
	
	//Constructeur avec coordonnees manuelles
	public Capteur(int idCapteur, String nameCapteur, int rayon, int coordX, int coordY, boolean bip) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = coordX;
		this.coordY = coordY;
		this.rayon = rayon;
		this.bip = bip;
		this.circle = new Circle(coordX,coordY,rayon,false);
		this.waveOne = new Circle(coordX,coordY,rayon,true);
		this.timer = createTimer();
		timer.start();
	}

	public synchronized void draw(Graphics2D g) {
		
		//variables pour ecrire le drawString
		String currentStatut = "";
		int coordStringX = coordX;
		int coordStringY = coordY;
		
		
		if (this.isBip()) {
			g.setColor(Color.RED);
			this.waveOne.draw(g);
		} else {
			g.setColor(Color.BLUE);
			
			if (this.isReceiving()) {
				//g.drawString("reception", coordX, coordY);
				currentStatut = "Reception";
			}
			else if (this.isPeripheralCollision()) {
				//g.drawString("PERIPHERAL COLLISION", coordX - 5, coordY - 10);
				currentStatut = "PERIPHERAL COLLISION";
				coordStringX = coordX - 5;
				coordStringY = coordY - 10;
			}
			else if (this.isInternalCollision()) {
				//g.drawString("INTERNAL COLLISION", coordX - 5, coordY - 10);
				currentStatut = "INTERNAL COLLISION";
				coordStringX = coordX - 5;
				coordStringY = coordY - 10;
			}
			else
			{
				currentStatut = "";
			}
		}
		
		g.drawString(currentStatut, coordStringX, coordStringY);		

		g.draw(new Rectangle2D.Double(coordX - 2, coordY - 2, 3, 3));
		this.circle.draw(g);

	}

	// public ArrayList<Capteur> silentListening(ArrayList<Capteur>
	// capteursProches)
	// {
	// this.bip = false;
	//
	// System.out.println("Ecoute " + this.idCapteur);
	//
	// int _rayon = this.rayon;
	// int _x = this.coordX;
	// int _y = this.coordY;
	//
	//
	// return capteursProches;
	// }
	
	private Timer createTimer() {
		
		ActionListener action = new ActionListener() {
			
			//Méthode pour chaque tic de timer
			public void actionPerformed (ActionEvent event)
			{
				move();				
			}

		};

		return new Timer(500, action);
	}
	
	int vitesse = 50;
	public void move()
	{
//		int move;
//		if(this.coordX >= Fenetre.getLargeur_panel())
//		{
//			vitesse = -vitesse;
//		}
//		if(this.coordX <= 500)
//		{
//			vitesse = +vitesse;
//		}
//		move = vitesse;
//		this.setCoordX(this.coordX+move);
		
		
		//this.setCoordY(coordY+10);
	}
	

	public void beeping() {

		this.bip = true;

	}

	public void ecouter() {
		this.bip = false;
	}

	public void calculer() {
		// Faire le lien avec la classe Algo et tous ses calculs
	}

	public int getIdCapteur() {
		return idCapteur;
	}

	public void setIdCapteur(int idCapteur) {
		this.idCapteur = idCapteur;
	}

	public String getNameCapteur() {
		return nameCapteur;
	}

	public void setNameCapteur(String nameCapteur) {
		this.nameCapteur = nameCapteur;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	public boolean isBip() {
		return bip;
	}

	public void setBip(boolean bip) {
		this.bip = bip;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public boolean isReceiving() {
		return receiving;
	}

	public void setReceiving(boolean receiving) {
		this.receiving = receiving;
	}

	public boolean isPeripheralCollision() {
		return peripheralCollision;
	}

	public void setPeripheralCollision(boolean peripheralCollision) {
		this.peripheralCollision = peripheralCollision;
	}

	public boolean isInternalCollision() {
		return internalCollision;
	}

	public void setInternalCollision(boolean internalCollision) {
		this.internalCollision = internalCollision;
	}

	// La fonction run du thread
	public void run() {
		
		while(true) {

			double pileOuFace;
			double probaDuBip = 0.5;
	
			pileOuFace = Math.random();
	
			if (pileOuFace > probaDuBip) {
				this.beeping();
			} else {
				this.ecouter();
			}
	
			try {
				Thread.sleep(5000);// 5 secondes
	
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}
		// ---Test déplacement---
		// System.out.println("Avant: "+this.coordX);
		// this.setX(this.coordX +500);
		// System.out.println("Après: "+this.coordX);
		// panel.addCapteur(this.nameCapteur, this.rayon, this.idCapteur,
		// this.bip);

	}



}

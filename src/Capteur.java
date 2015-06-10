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

	}

	public synchronized void draw(Graphics2D g) {
		
	
		String currentStatut = "";
		int coordStringX = coordX;
		int coordStringY = coordY;
		
		if (this.isBip()) {
			g.setColor(Color.RED);
			this.waveOne.draw(g);
		} 
		else
		{
			g.setColor(Color.BLUE);
		}

		

		if (this.isPeripheralCollision()) {
			currentStatut = "PERIPHERAL COLLISION";

		}
		if (this.isInternalCollision()) {
			currentStatut = "INTERNAL COLLISION";

		}
		if (this.isReceiving()) {
			currentStatut = "Reception";
		}
		

		if( this.isReceiving() == false)
		{
			if( this.isInternalCollision() == false)
			{
				if( this.isPeripheralCollision() == false)
				{
					currentStatut = "";
				}
				
			}
			
		}
		
		    
		g.drawString(this.getNameCapteur() +": "+currentStatut, coordStringX, coordStringY);
		
        g.draw(new Rectangle2D.Double(coordX-2, coordY-2, 3, 3));	
        this.circle.draw(g);

	}

	 
	

	public synchronized void beep(){
		Beep.beep(this);
	}

	public synchronized void listen(){
		if(Fenetre.getAlgoListe().toString().equals("BL")){
			Beep.listenBL(this);
		}else if(Fenetre.getAlgoListe().toString().equals("BLcd")){
			Beep.listenBLcd(this);
		}else if(Fenetre.getAlgoListe().toString().equals("BcdL")){
			Beep.listenBcdL(this);
		}else if(Fenetre.getAlgoListe().toString().equals("BcdLcd")){
			Beep.listenBcdLcd(this);
		}
		
	}
	
	public synchronized void pileOuface(double probaDuBip){
		double pileOuFace;
		
		 pileOuFace = Math.random();
		 
			if(pileOuFace > probaDuBip)
			{
				this.bip = false;
			}
			else
			{
				this.bip = true;
			}
			
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
		int div = 1;
		
		while(true){
			this.pileOuface(0.5/div);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(this.isBip()){
				this.beep();				
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.listen();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.isInternalCollision() || this.isPeripheralCollision())
				div++;
			
				System.out.println(this.getNameCapteur()+"---->"+0.5/div);
						
			//On remet tout à faux pour la prochaine session
			this.setInternalCollision(false);
			this.setPeripheralCollision(false);
			this.setReceiving(false);
			
			Beep.clear();
			
		}
		


	}



}

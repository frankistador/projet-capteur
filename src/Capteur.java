import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Capteur extends Thread  {

	private int idCapteur;
	private String nameCapteur;
	private int coordX;
	private int coordY;
	private int rayon;
	private Dessin panel = new Dessin();

	public Capteur(int idCapteur, String nameCapteur, int rayon) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = (int)(Math.random()*(600 - 300 +1)) + 300;
		this.coordY = (int)(Math.random()*(600 - 300 +1)) + 300;
		this.rayon = rayon;
	}
	
	public Capteur(int idCapteur, String nameCapteur, int rayon, int coordX, int coordY) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = coordX;
		this.coordY = coordY;
		this.rayon = rayon;
	}
		
	public void draw(Graphics2D g)
    {
		int r = (int)(Math.random()* 256);
		int gr = (int)(Math.random()* 256);
		int b = (int)(Math.random()* 256);
		
		Color c = new Color(r,gr,b);
        g.setColor(c);
        g.fillOval(coordX, coordY, 10, 10);	
        g.drawOval(coordX-rayon/2, coordY-rayon/2, rayon, rayon);
      
        
    }
		
	public void ecouter()
	{
		//Le truc qui �coute...
	}
	
	public void envoyer()
	{
	
	}
	
		
	public void calculer()
	{
		//Faire le lien avec la classe Algo et tous ses calculs
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


	public void setX(int coordX) {
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

	public Dessin getPanel() {
		return panel;
	}

	public void setPanel(Dessin panel) {
		this.panel = panel;
	}
	
	public void run()
	{
		//if state = BLOCKED or TERMINATED
		//    on ecoute
		//else if state = RUNNABLE 
		//    on balaye le terrain 
	}
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Capteur extends Thread {

	private int idCapteur;
	private String nameCapteur;
	private int coordX;
	private int coordY;
	private int rayon;
	private boolean bip;
	private Circle circle;
	private boolean receiving = false;

	public Capteur(int idCapteur, String nameCapteur, int rayon,boolean bip) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = (int)(Math.random()*(700 - 200 +1)) + 200;
		this.coordY = (int)(Math.random()*(700 - 200 +1)) + 200;
		this.rayon = rayon;
		this.bip = bip;
		this.circle = new Circle(coordX,coordY,rayon);
	}
		
	public synchronized void draw(Graphics2D g) 
    {

		
		if(this.isBip()){
             g.setColor(Color.RED);
             g.drawString("BIP BIP !", coordX, coordY);
		}
		else
		{
			g.setColor(Color.BLUE);
		}
		

			if(this.isReceiving()){
				g.drawString("reception", coordX, coordY);
			}
		
	    
        g.draw(new Rectangle2D.Double(coordX, coordY, 1, 1));	
        this.circle.draw(g);
        
        
        
    }
	
	
//	public ArrayList<Capteur> silentListening(ArrayList<Capteur> capteursProches)
//	{
//		this.bip = false;
//		
//		System.out.println("Ecoute " + this.idCapteur);
//		
//		int _rayon = this.rayon;
//		int _x = this.coordX;
//		int _y = this.coordY;
//
//		
//		return capteursProches;
//	}
	
	
	public void beeping()
	{
			
		this.bip = true;
		
	}
	
	
		
	public void ecouter()
	{
		this.bip = false;
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

	public void run()
	{
			
		double pileOuFace;
		double probaDuBip = 0.5;
		
		 pileOuFace = Math.random();
		 
			if(pileOuFace > probaDuBip)
			{
				this.beeping();
			}
			else
			{
				this.ecouter();
			}

			try {
				Thread.sleep(5000);//5 secondes
			
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();					
			}

		
			//---Test déplacement---
//			System.out.println("Avant: "+this.coordX);
//			this.setX(this.coordX +500);
//			System.out.println("Après: "+this.coordX);
//			panel.addCapteur(this.nameCapteur, this.rayon, this.idCapteur, this.bip);
		 
	}
	
	public class Circle implements Shape
	{
	private double x, y, radius;

	public Circle(double x, double y, double radius)
	{
	    this.x = x;
	    this.y = y;
	    this.radius = radius;
	}

	// Tests if the specified coordinates are inside the boundary of the Shape
	public boolean contains(double x, double y)
	{
		return this.getRadius()/2 >= Math.sqrt((Math.pow((x - this.x), 2) + (Math.pow((y - this.y), 2))));
			
	}
	

	// Tests if the interior of the Shape entirely contains the specified rectangular area
	public boolean contains(double x, double y, double w, double h)
	{
	    if (this.contains(x, y) && this.contains(x+w, y) && this.contains(x+w, y+h) && this.contains(x, y+h))
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}
	
	public boolean contains(Point2D p)
	{
		 if (this.contains(p.getX(), p.getY()))
		    {
		        return true;
		    }
		    else
		    {
		        return false;
		    }
	}

	public void draw(Graphics2D g){
		g.drawOval((int)x-(int)radius/2, (int)y-(int)radius/2, (int)radius, (int)radius);
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		 return this.contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	

	}
	
	
}

import java.awt.Graphics;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Dessin extends JPanel {

	private Capteur newCapteur;
    private LinkedList<Capteur> threads = new LinkedList<Capteur>();
    
    public Dessin(){
    	
    }
	
	  public void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2D = (Graphics2D) g;
	        java.util.ListIterator<Capteur> it = threads.listIterator();
	        while(it.hasNext())
	        {
	           it.next().draw(g2D);
	        }
	    }
	  
	  public void addCapteur(String name,int rayon,int id)
	    {
	        newCapteur = new Capteur(id,name,rayon);
	        threads.add(newCapteur);
	        newCapteur.start();
	    }
	
}


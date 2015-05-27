import java.awt.Graphics;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
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
	  
	  public void addCapteur(String name,int rayon,int id,boolean bip)
	    {
	        newCapteur = new Capteur(id,name,rayon,bip);	     
	        threads.add(newCapteur);
	        newCapteur.start();
	  
	     
	    }
	  
	  public void lancementSimulation()
	  {	      	
		  int cpt = 0;
	        for(int i=0;i < threads.size();i++){
	        	
	        	for(int j=0;j < threads.size();j++){
	        		if(i != j){   
		        	if(threads.get(i).getCircle().contains(threads.get(j).getCoordX(),threads.get(j).getCoordY()) && threads.get(j).isBip()){	        		
		        		if(!threads.get(i).isBip()){
		        		threads.get(i).setReceiving(true);
	        			cpt++;
		        		}
		        		else
		        		{
		        		threads.get(i).setInternalCollision(true);
		        		}
		        	}
	        		}
	        }
	        	if(cpt > 1){
	        		threads.get(i).setPeripheralCollision(true);
	        		threads.get(i).setReceiving(false);	        		
	        	}
	        	cpt=0;
	        		        	
	        }
	        	        

	  }
	  
	 	
}


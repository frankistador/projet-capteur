import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

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
	  
	  public void addCapteur(Capteur capteur)
	    {
	        threads.add(capteur);
	        capteur.start();
	    }
	
	  public void clean(){
		  threads.clear();
	  }
	  
	  
}


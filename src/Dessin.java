import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Dessin extends JPanel {

	private Capteur newCapteur;
    private LinkedList<Capteur> threads = new LinkedList<Capteur>();
    private static boolean dessinSimul = false;
    
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
	        
	        Dessin.setDessinSimul(false);
	    }
	  
	  public void addCapteur(String name,int rayon,int id,boolean bip)
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
	  
	  
	public void redimensionner(int larg, int haut) {
		this.setSize(larg,haut);
		
	}

	public static boolean isDessinSimul() {
		return dessinSimul;
	}

	public static void setDessinSimul(boolean dessinSimul) {
		Dessin.dessinSimul = dessinSimul;
	}
	  
	  	    
	  public void clean(){
		   java.util.ListIterator<Capteur> it = threads.listIterator();
	        while(it.hasNext())
	        {
	        		it.next().interrupt();
	        		
	        }
		  threads.clear();
		  Beep.clear();
	  }
	  
}


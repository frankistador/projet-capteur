


public class Capteur extends Node implements Runnable {

	public Capteur(int idCapteur, String nameCapteur, int coordX, int coordY, int rayon)
	{
		super(idCapteur,nameCapteur,coordX,coordY,rayon);
	}
	
	public void run()
	{
		//if state = BLOCKED or TERMINATED
		//    on ecoute
		//else if state = RUNNABLE 
		//    on balaye le terrain
		
		
	 this.identifier();
	 
	 
	}
	
}

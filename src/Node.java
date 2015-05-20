
public class Node {
	private int idCapteur;
	private String nameCapteur;
	private float coordX;
	private float coordY;
	private float rayon;

	public Node(int idCapteur, String nameCapteur, int coordX, int coordY, int rayon) {
		this.idCapteur = idCapteur;
		this.nameCapteur = nameCapteur;
		this.coordX = coordX;
		this.coordY = coordY;
		this.rayon = rayon;
	}
	
	public synchronized void identifier()
	{
		System.out.println("Id:"+idCapteur);
		System.out.println("Name:"+nameCapteur);
		System.out.println("X:"+coordX);
		System.out.println("Y:"+coordY);
		System.out.println("Rayon:"+rayon);
		
	}
		
	
	public void ecouter()
	{
		//Le truc qui écoute...
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


	public float getCoordX() {
		return coordX;
	}


	public void setX(float coordX) {
		this.coordX = coordX;
	}


	public float getCoordY() {
		return coordY;
	}


	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}


	public float getRayon() {
		return rayon;
	}


	public void setRayon(float rayon) {
		this.rayon = rayon;
	}
	
}

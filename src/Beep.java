import java.util.HashMap;


public class Beep {

	private static HashMap<int[],Integer> beepList = new HashMap<int[],Integer>();
	
	static void beep(Capteur c){
		int aux[] = {0,0};
		aux[0] = c.getCoordX();
		aux[1] = c.getCoordY();
		beepList.put(aux,c.getRayon());
	}
	
	 static void listenBcdLcd(Capteur c){
		 int cpt = 0;
		 Circle circle;
		 for (int[] mapKey : beepList.keySet()) {
			 
			 circle=new Circle(mapKey[0],mapKey[1],beepList.get(mapKey));
			 
			 if(circle.contains(c.getCoordX(),c.getCoordY())){	
				 if(c.isBip()){
					 if( c.getCoordX() != circle.getX() && c.getCoordY() != circle.getY() && c.getCircle().contains(circle.getX(), circle.getY()))
	     				c.setInternalCollision(true);		
	     			}
				 else{
				 //c.setReceiving(true);
     		     cpt++;
				 }
			 }
		 }
		if(!c.isInternalCollision())
		{
			if(cpt == 1)
			{
				c.setReceiving(true);
			}
			else if (cpt>1)
			{
				c.setPeripheralCollision(true);
			}
		}

		 
		 
	 }
	 
	 static void clear(){
		 beepList.clear();
	 }
	 
	 static void listenBL(Capteur c) {
			int cpt = 0;
			Circle circle;
			for (int[] mapKey : beepList.keySet()) {

				circle = new Circle(mapKey[0], mapKey[1], beepList.get(mapKey));

				if (circle.contains(c.getCoordX(), c.getCoordY())) {
					if (c.isBip()) {
						if (c.getCoordX() != circle.getX()
								&& c.getCoordY() != circle.getY()
								&& c.getCircle().contains(circle.getX(),circle.getY()))
							c.setReceiving(false);
					} else {
						cpt++;
					}
				}
			}

			if (cpt == 1) {
				c.setReceiving(true);
			} 
			else {
				c.setReceiving(false);
			}
		}
		
		static void listenBLcd(Capteur c) {
			int cpt = 0;
			Circle circle;
			for (int[] mapKey : beepList.keySet()) {

				circle = new Circle(mapKey[0], mapKey[1], beepList.get(mapKey));

				if (circle.contains(c.getCoordX(), c.getCoordY())) {
					if (c.isBip()) {
						if (c.getCoordX() != circle.getX()
								&& c.getCoordY() != circle.getY()
								&& c.getCircle().contains(circle.getX(),circle.getY()))
							c.setReceiving(false);
					}
						else {
							c.setReceiving(true);
							cpt++;
						}
				}
			}

			if (cpt == 0) {
				c.setReceiving(false);
			} else if (cpt == 1) {
				c.setReceiving(true);
			} else {
				c.setPeripheralCollision(true);
				c.setReceiving(false);
			}
		}
		
		static void listenBcdL(Capteur c) {
			int cpt = 0;
			Circle circle;
			for (int[] mapKey : beepList.keySet()) {

				circle = new Circle(mapKey[0], mapKey[1], beepList.get(mapKey));

				if (circle.contains(c.getCoordX(), c.getCoordY())) {
					if (c.isBip()) {
						if (c.getCoordX() != circle.getX()
								&& c.getCoordY() != circle.getY()
								&& c.getCircle().contains(circle.getX(),circle.getY()))
							c.setInternalCollision(true);
					} else {
						cpt++;
					}
				}
			}

			if (cpt == 1) {
				c.setReceiving(true);
			} 
			else {
				c.setReceiving(false);
			}
		}
	 
}

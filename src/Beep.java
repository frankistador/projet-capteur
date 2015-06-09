import java.util.HashMap;


public class Beep {

	private static HashMap<Integer,Integer> beepList = new HashMap<Integer,Integer>();
	
	static void beep(Capteur c){		
		beepList.put(c.getCoordX(),c.getCoordY());
	}
	
	 static void listen(Capteur c){
		 int cpt = 0;
		 Circle circle;
		 for (Integer mapKey : beepList.keySet()) {
			 //circle=new Circle()
			 if(c.getCircle().contains(mapKey,beepList.get(mapKey))){	
				 if(c.isBip()){
					 if( c.getCoordX() != mapKey && c.getCoordY() != beepList.get(mapKey))
	     				c.setInternalCollision(true);		
	     			}
				 else{
				 c.setReceiving(true);
     			cpt++;
				 }
			 }
		 }
		 if(cpt > 1){
     		c.setPeripheralCollision(true);
     		c.setReceiving(false);	        		
     	}
		 
		 
	 }
	 
	 static void clear(){
		 beepList.clear();
	 }
}

import java.util.HashMap;


public class Beep {

	static HashMap<Integer,Integer> beepList = new HashMap<Integer,Integer>();
	
	static void beep(Capteur c){
		System.out.println(c.getName()+"   BEEP");
		beepList.put(c.getCoordX(),c.getCoordY());
	}
	
	 static void listen(Capteur c){
		 System.out.println(c.getName()+"   LISTEN");
		 int cpt = 0;
		 for (Integer mapKey : beepList.keySet()) {
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

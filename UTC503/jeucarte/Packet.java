package jeucarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Packet<T> {
	
	private Packet param1;
//	private static Packet instance = null;
	ArrayList<T> carte;

	public Packet(T test) {
		
		this.carte = new ArrayList<T>();
		
		Class C = test.getClass();
		String typage =C.getSimpleName() ;
		
		 
		switch (typage) {
		
		case "Packet32":
			
			GetPaket32(test);	
            break;
 
        // Case 2
        case "Packet54":
 
        	GetPaket54(test);	
            break;
		
        case "Packet78":
        	 
      
            System.out.println("vide");
            break;
		
		}
		
		//}
		//Packet32 melange = (Packet32) test;
		//System.out.println(melange.carte.get(0));
		 Collections.shuffle(this.carte);
		//this.param1 = this.carte;
	}

	// public Packet getMelange() {
		 		 
		//    return this.param1;
		//  }
	
	 
	 
	 
	 private  void GetPaket32(T up) {
		 
		 Packet32 Amelange = (Packet32) up;
		 
		 for (int i=0;i<Amelange.carte.size();i++ ) {
			 this.carte.add((T) Amelange.carte.get(i));	 
			 
		 }
		// System.out.println(this.carte.get(12));
	//	 ((CarteDeBase) this.carte.get(12)).tourner();
		// System.out.println(this.carte.get(12));	 
		
	 }
	 
	 private  void GetPaket54(T up) {
		 
		 Packet54 Amelange = (Packet54) up;
		 
		 for (int i=0;i<Amelange.carte.size();i++ ) {
			 this.carte.add((T) Amelange.carte.get(i));	 
			 
		 }
		// System.out.println(this.carte.get(12));
	//	 ((CarteDeBase) this.carte.get(12)).tourner();
		// System.out.println(this.carte.get(12));	 
		
	 }
	 

	

}

package jeucarte;


import java.util.ArrayList;

public final class Packet32  {
	
	
	private static Packet32 instance = null;
	ArrayList<Carte32> carte;
	
	private Packet32() {
				
		this.carte = new ArrayList<Carte32>(32);
		
		for (int i =0 ; i < 4 ; i++) {
			
			Famille familla = Famille.values()[i] ;
			
			for (int j = 7; j< 15 ; j++) {
				
				TypeCarte type = TypeCarte.values()[j] ;
				
				carte.add(new Carte32(type, familla));
							
				
			}
			
			
		}				
			
		
    }
	
	public static Packet32 getNew() {
        if ( instance == null ) {
            instance = new Packet32();
        }
        return instance;
    }
			

}

package jeucarte;
import java.util.ArrayList;
public class Packet54 {
	
	private static Packet54 instance = null;
	ArrayList<Carte54> carte;
	
	private Packet54() {
				
		this.carte = new ArrayList<Carte54>(54);
		
		for (int i =0 ; i < 4 ; i++) {
			
			Famille familla = Famille.values()[i] ;
			
			for (int j = 2; j< 15 ; j++) {
				
				TypeCarte type = TypeCarte.values()[j] ;
				
				carte.add(new Carte54(type, familla));
							
				
			}
			
			
			
		}				
		carte.add(new Carte54(TypeCarte.JOC1, Famille.JOKER));
		carte.add(new Carte54(TypeCarte.JOC2, Famille.JOKER));	
		
    }
	
	public static Packet54 getNew() {
        if ( instance == null ) {
            instance = new Packet54();
        }
        return instance;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

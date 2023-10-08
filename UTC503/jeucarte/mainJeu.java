package jeucarte;


public class mainJeu {

	
	public static void main(String[] args) {
	
	Packet32 Pqt = Packet32.getNew(); 
	//Packet54 Pqt = Packet54.getNew();		
	
	Packet Pa = new Packet<>(Pqt);	
	
	PlusOuMoins.jeux(Pa);
	
	
	}

	
	
	
}

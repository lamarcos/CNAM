package jeucarte;

public enum Famille {
	PIQUE("♠"),COEUR("♥"),CARREAU("♦"),TREFLE("♣"),ATOUT("A"),JOKER("J");
	
	private String texteRetour ;
	
	private Famille(String donnes) {
		// TODO Auto-generated constructor stub	
		this.texteRetour = donnes;
		
		
	}
	
	
	public String toString() {
		return texteRetour;
	}
	
	
}







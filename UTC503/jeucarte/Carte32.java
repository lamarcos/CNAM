package jeucarte;

public class Carte32 extends CarteDeBase implements ICarte  {
	
	
	public static final int NBR_CARTES = 32;	
	public static final String DOS = "[****]";	
	private final TypeCarte typeCarte;
	private final Famille famille;
	//private boolean visible = false;
	
	public Carte32(TypeCarte typeCarteTmp, Famille familleTmp) {
		// TODO Auto-generated constructor stub
		
		this.typeCarte = typeCarteTmp ;
		this.famille = familleTmp;		
		
	}	
	
	@Override
	public String toString() {
		
		String texte = coteface() ;
		
			if (!super.visible) {
				texte = DOS;
			}
	return texte;
	}

	@Override
	public String coteface() {
		// Coteface est une interface et non une abstract car dans le cas d'une carte pokemon, on le codera d'une autre façon.
		// sans faire appel à verrifcarte
		verrifcarte(NBR_CARTES, typeCarte, famille );
		
		String rep = "cette carte ne fait pas partie d'un jeux " + NBR_CARTES + " cartes"; 
		if (super.existe) {
			rep = "[" + typeCarte + " " + famille + "]";
		}
		
		
		return rep;
	}

	
	
	//public void tourner() {
	//	this.visible = !this.visible;  
		
	//}

	
	

}

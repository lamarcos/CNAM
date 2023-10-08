package jeucarte;

public class Carte54 extends CarteDeBase implements ICarte{

	
	
	public static final int NBR_CARTES = 54;	
	public static final String DOS = "[++++]";	
	private final TypeCarte typeCarte;
	private final Famille famille;
	
	public Carte54(TypeCarte typeCarteTmp, Famille familleTmp) {
		
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
		
		verrifcarte(NBR_CARTES, typeCarte, famille );
		String rep = "cette carte ne fait pas partie d'un jeux " + NBR_CARTES + " cartes"; 
		if (super.existe) {
			rep = "[" + typeCarte + " " + famille + "]";
		}	
		return rep;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

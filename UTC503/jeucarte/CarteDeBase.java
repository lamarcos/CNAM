package jeucarte;

public abstract class CarteDeBase {
	protected boolean visible = false;
	protected boolean existe = false;

	
	public void tourner() {
		this.visible = !this.visible;  
		
	}
	
	
	
	public  void verrifcarte (int nbcarte, TypeCarte typeCarte, Famille famille ) {
		
	//	System.out.println(famille.ordinal());
		
		
		if (nbcarte == 32 && famille.ordinal() < 4 && typeCarte.ordinal() > 6 && typeCarte.ordinal()< 15) {
			
			this.existe = true;
		}
		
		if (nbcarte == 54 && (famille.ordinal() < 4 || famille.ordinal() == 5 ) && typeCarte.ordinal()< 15) {
			
			this.existe = true;
			
			if (famille.ordinal() == 5 &&  typeCarte.ordinal() > 1 ) {
				this.existe = false;
			}
			if (famille.ordinal() < 4 &&  typeCarte.ordinal() < 2 ) {
				this.existe = false;
			}
			
			
		}
		
		
		if (nbcarte == 78 && famille.ordinal() < 5  && typeCarte.ordinal() > 1) {
			
			this.existe = true;
			if (famille.ordinal() < 4 &&  typeCarte.ordinal() > 15 ) {
				this.existe = false;
			}
			if (famille.ordinal() == 4 &&  (typeCarte.ordinal() < 2 || ( typeCarte.ordinal() > 10 && typeCarte.ordinal() < 16 ) ) ) {
				this.existe = false;
			}
			
		}
		
		
		
	}
	
	

}

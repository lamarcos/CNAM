package jeucarte;

public enum TypeCarte {
	JOC1("??"),JOC2("??"),DEUX("02"),TROIS("03"),QUATRE("04"),CINQ("05"),SIX("06"),SEPT("07"),HUIT("08"),NEUF("09"),DIX("10"),
	VALET("VA"),DAME("DA"),ROIS("RO"),AS("AS"),CALVALIER("CA"),UN("01"),ONZE("11"),DOUZE("12"),TREIZE("13"),
	QUATORZE("14"),QUINZE("15"),SEIZE("16"),DIXSEPT("17"),DIXHUIT("18"),DIXNEUF("19"),VINGT("20"),VINGTUN("21");

	private String texteRetour ;
	
	private TypeCarte(String donnes) {

		
		this.texteRetour = donnes;
		//System.out.println(this.texteRetour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return texteRetour;
	}
	
	
	
	
	

}

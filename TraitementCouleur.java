
public interface TraitementCouleur {
	
	public abstract int getRouge(FichePixel aTraiter);
	public abstract int getBleu(FichePixel aTraiter);
	public abstract int getVert(FichePixel aTraiter);
	public abstract int setRouge(FichePixel aTraiter, int couleur);
	public abstract int setBleu(FichePixel aTraiter, int couleur);
	public abstract int setVert(FichePixel aTraiter, int couleur);

}

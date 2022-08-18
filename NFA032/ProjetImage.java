import java.util.Scanner;
import java.io.* ;
import java.net.URL; // je triche mais c'est pour la bonne cause
import java.time.LocalDateTime; // idem

public class ProjetImage {
 static String patchIn , patchOut ;
 static String [][] tableauDesFichier = new String[10][3];
	

	public static void main(String[] args) {
		
		if(args.length == 2){
		
		main (args[0], Integer.parseInt(args[1])) ; }
		else {
		getPatch();
		Scanner sc = new Scanner(System.in);
		System.out.println("I'm GET53370 !! ");
		System.out.println("----------------- ");
		System.out.println("Quel est le nom du fichier a modifier ? ");
		String nomImage = sc.nextLine();
		System.out.println("Quel est votre choix : ");
		System.out.println("   10 - Doubler la largeur ");
		System.out.println("   11 - Doubler la longeur ");
		System.out.println("   12 - Doubler l'image ");
		System.out.println("   13 - Decouper image largeur + longeur ");
		System.out.println("   14 - Decouper image largeur  ");
		System.out.println("   15 - Decouper image longeur ");
		//System.out.println("         ------");
		System.out.println("   20 - Passer en niveau de gris ");
		System.out.println("   21 - Eclaircir dominante rouge ");
		System.out.println("   22 - Eclaircir dominante vert ");
		System.out.println("   23 - Eclaircir dominante bleu ");
		System.out.println("   24 - Foncer dominante rouge ");
		System.out.println("   25 - Foncer dominante vert ");
		System.out.println("   26 - Foncer dominante bleu ");
		System.out.println("   27 - Passage en negatif ");
		
		//...
		int option = Integer.parseInt(sc.nextLine());
		 theLifeLigne(nomImage, option);
		
		sc.close();	
		}
		
	}
	
	public static void main(String nomImage, int option ) {
		getPatch();
		theLifeLigne(nomImage, option);
	}
	
	private static int getPatch () {
		
		URL test = ProjetImage.class.getResource("ProjetImage.class");
		String patch = test.toString() ;
		patch = patch.substring(6,patch.length()-17);
		patchIn = patch +"in/";
		patchOut = patch +"out/";
	    System.out.println("Vos images d entree doivent se trouver dans : " + patchIn);
				
		return 1 ;
		
	}
	
	
	
	public static void theLifeLigne (String nomImage, int option) {
		
		
		openImage startImage = new openImage(nomImage, patchIn );
		tableauDesFichier[0][0] = "Start Image";
		tableauDesFichier[0][1] = nomImage;
		tableauDesFichier[0][2] = patchIn;	
		
		FichePixel pixelPremier = startImage.getpremier();
		FichePixel pixelDernier = startImage.getdernier();
		String[] taille = startImage.getTaille();
		traitementDonnees(pixelPremier,pixelDernier,taille, option, startImage);
		
		pixelDernier = startImage.getdernier();
		pixelPremier = startImage.getpremier();
		EcrireImage FinishImage = new EcrireImage(nomImage, patchOut, pixelPremier, pixelDernier, taille,startImage);		
			
	}
	
	
	private static void traitementDonnees(FichePixel pixelPremier,FichePixel pixelDernier, String[] taille, int option,openImage startImage) {
		System.out.println("Traitement de la modification en cours..");
		if (option == 10) {DoubleLargeur Finale = new DoubleLargeur(pixelPremier,taille ,startImage );}
		else if (option == 11) {DoubleLongeur Finale = new DoubleLongeur(pixelDernier,pixelPremier,taille ,startImage );}
		else if (option == 12) {DoubleLargeur Finale = new DoubleLargeur(pixelPremier,taille ,startImage );DoubleLongeur Finale2 = new DoubleLongeur(pixelDernier,pixelPremier,taille ,startImage );}
		else if (option == 20) {PassageGris Finale = new PassageGris(pixelPremier);}
		else if (option == 21) {Luminosite EclaireRouge = new Luminosite(pixelPremier, startImage , true, 0 , 10);}
		else if (option == 22) {Luminosite EclaireVert = new Luminosite(pixelPremier, startImage , true, 1 ,10 );}
		else if (option == 23) {Luminosite EclaireBleu = new Luminosite(pixelPremier, startImage , true, 2 ,10);}
		else if (option == 24) {Luminosite SombreRouge = new Luminosite(pixelPremier, startImage , false, 0 ,10);}
		else if (option == 25) {Luminosite SombreVert = new Luminosite(pixelPremier, startImage , false, 1,10 );}
		else if (option == 26) {Luminosite SombreBleu = new Luminosite(pixelPremier, startImage , false, 2 ,10);}
		else if (option == 13) {decoupage Finale = new decoupage (pixelDernier,pixelPremier,taille ,startImage,0);}
		else if (option == 14) {decoupage Finale = new decoupage (pixelDernier,pixelPremier,taille ,startImage,1);}
		else if (option == 15) {decoupage Finale = new decoupage (pixelDernier,pixelPremier,taille ,startImage,2);}
		else if (option == 27) {negatif Final = new negatif(pixelPremier, startImage );}
		
	}

}
	

class openImage {
	private FichePixel premier;
	private FichePixel dernier;
	private String[] splitMan;
	private String baseCouleurMax = "";
	
	public openImage(String nomImage , String patchIn) {
	
		String tailleFichier = "";
		
		System.out.println("Lecture du fichier en cours..");
		 try{    
	           FileInputStream fin=new FileInputStream(patchIn+nomImage);    
	            int i=0;
	            int j=0;
	            int blocCount=0;
	            String tempoMan= "";
	            int[]newPixel = {0,0,0};
	            int[]oldPixel = {999,999,999};
	            int counter = 0;
	           while((i=fin.read())!=-1){    

	        	  if (i==10) {j++;}
	        	  if (j==2) {tailleFichier = tailleFichier +(char)i;}
	        	  if (j==3) {baseCouleurMax = baseCouleurMax +(char)i;}
	        	  if (j>3)  {
	        		  
	        		  	if (blocCount<11 && i!= 10) {
	        		  		tempoMan = tempoMan+(char)i;
	        		  		blocCount ++ ;
	        		  
	        		  	}else if (blocCount==11) {
	        		  		newPixel[0] = Integer.parseInt(tempoMan.substring(0,3).replace(" ", "")) ;
	        		  		newPixel[1] = Integer.parseInt(tempoMan.substring(4,7).replace(" ", ""));
	        		  		newPixel[2] = Integer.parseInt(tempoMan.substring(8,11).replace(" ", ""));
	        		  		blocCount = 0 ;
	        		  		tempoMan="";
	        		  		
	        		  		if (newPixel[0]!= oldPixel[0] | newPixel[1]!= oldPixel[1] |newPixel[2]!= oldPixel[2]) {
	        		  			FichePixel ancienPremier = premier;
	        		  			premier  = new FichePixel (newPixel[0],newPixel[1],newPixel[2],ancienPremier) ;
	        		  			
	        		  			//if(ancienPremier == null) {dernier = ancienPremier ; }
	        		  			if(ancienPremier != null) {
	        		  				ancienPremier.setPrecedent(premier);
	        		  				 if (counter == 0) {
	        		  					dernier = ancienPremier ;
	        		  					counter = 1 ;
	        		  				 }
	        		  			}
	        		  			
	        		  			for (int z=0; z<3; z++) {oldPixel[z]= newPixel[z];}
	        		  			
	        		  		} else {
	        		  			
	        		  			premier.nombre = premier.nombre +1 ;
	        		  			
	        		  		}
	        		  			        		  			        		  		
	        		  	}
	        		  
	        	  	}
	        		         		  
	           }	  
	            
	            
	           fin.close();    
	         }catch(Exception e){System.out.println(e);}    
		 
		 
		 tailleFichier= tailleFichier.replace("\n", "");
		  splitMan = tailleFichier.split(" ");
		 System.out.println("Le fichier fait : "+ splitMan[0]+ "x"+splitMan[1] + " Px");
		// System.out.print("Le fichier fait : "+ tailleFichier+ " Px");
	        }    		
	
	void setPremier (FichePixel premier) {
		this.premier = premier ;	
	}
	void setDernier (FichePixel dernier) {
		this.dernier = dernier ;	
	}
	public FichePixel getpremier() {
		return premier;
		
	}
	public FichePixel getdernier() {
		return dernier;
		
	}
	
	public int GetcouleurMax () {
		
		baseCouleurMax=baseCouleurMax.replace("\n", "");
		int couleurmax = Integer.parseInt(baseCouleurMax);
		
		return couleurmax;
	}
		
	public String[] getTaille() {
		return splitMan;
		
	}
	void setTaille(String larg, String longs) {
		splitMan[0]= larg ;
		splitMan[1] = longs ;
	}

}


 class FichePixel {
	
	private int rouge, vert , bleu ;
	private FichePixel suivant , precedent;
	public int nombre ; // Oui, il devrait être en private avec un geter-seter, mais je fais mon rebelle !!! 
	
	
	FichePixel(int rouge,int vert,int bleu, FichePixel suivant){
	this.rouge = rouge;
	this.vert = vert;
	this.bleu = bleu ;	
	this.suivant = suivant;
	nombre = 1;
		
	}
	
	FichePixel getSuivant(){
		return this.suivant ;
	}
	
	
	void setSuivant(FichePixel ancienPremier){
		
	this.suivant = ancienPremier ;	
	}
	
	FichePixel getPrecedent(){
		return this.precedent ;
	}
	 
   void setPrecedent(FichePixel ancienPremier){
		
	this.precedent = ancienPremier ;	
	}
	
	int getRouge(){
		return this.rouge ;
	}
	
	int getVert(){
		return this.vert ;
	}
	
	int getBleu(){
		return this.bleu ;
	}
	
	void setRouge(int rouge) {
	this.rouge=rouge;	
	}
	void setVert(int vert) {
		this.vert=vert;	
		}
	void setBleu(int bleu) {
		this.bleu=bleu;	
		}
	
	
	
	
}

class EcrireImage {
	
	public EcrireImage(String nomImage , String patchOut, FichePixel pixelPremier, FichePixel pixelDernier, String[] taillesortie,openImage startImage ) {
		
		try{
			System.out.println("Ecriture du fichier en cours..");
			
	
			String datation = LocalDateTime.now().toString(); 
			datation = datation.substring(0, 19);
			datation=datation.replace("-", "");
			datation=datation.replace(":", "-");
			FileOutputStream fichier = new FileOutputStream(patchOut+datation+nomImage);
			
			String entete = "P3\n# CREATOR: GET533570\n";
			byte[] mybytes = entete.getBytes();
			fichier.write(mybytes);	
			String enteteSuite= taillesortie[0]+" "+taillesortie[1]+"\n"+startImage.GetcouleurMax()+"\n";
			mybytes = enteteSuite.getBytes();
			fichier.write(mybytes);
			int compteur = 0;
			
			while (pixelDernier != null){
						
				int rougefinal = pixelDernier.getRouge();
				int vertfinal = pixelDernier.getVert();
				int bleufinal = pixelDernier.getBleu();
				String rougeSfinal, bleuSfinal ,vertSfinal;
				
				if (rougefinal <100 && rougefinal > 10) {
					 rougeSfinal = " " + Integer.toString(rougefinal);
					}else if (rougefinal < 10)  {
						 rougeSfinal = "  " + Integer.toString(rougefinal);						
					}else {
						 rougeSfinal = Integer.toString(rougefinal);
					}
				
				if (bleufinal <100 && bleufinal > 9) {
					 bleuSfinal = " " + Integer.toString(bleufinal);
					}else if (bleufinal < 10)  {
						 bleuSfinal = "  " + Integer.toString(bleufinal);						
					}else {
						 bleuSfinal = Integer.toString(bleufinal);
					}
				
				if (vertfinal <100 && vertfinal > 10) {
					 vertSfinal = " " + Integer.toString(vertfinal);
					}else if (vertfinal < 10)  {
						 vertSfinal = "  " + Integer.toString(vertfinal);						
					}else {
						 vertSfinal = Integer.toString(vertfinal);
					}
				String couleur = rougeSfinal+" "+vertSfinal+" "+ bleuSfinal +" ";
				
				mybytes = couleur.getBytes();
				int dupliquePixel =pixelDernier.nombre;
				
				for (int k=0; k< dupliquePixel; k++ ) {
				fichier.write(mybytes);				
				compteur ++;
					if (compteur == 5) {
						fichier.write(10);
						compteur =0;
					}
				}
				
				pixelDernier = pixelDernier.getPrecedent();
			}
			
			fichier.close();    
			System.out.println("Ecriture terminee : "+datation+ nomImage);
	         }catch(Exception e){System.out.println(e);}  
		
		
		
	}
	
}


class DoubleLargeur implements TraitementTaille {

	
	public DoubleLargeur(FichePixel pixelEnCours, String[] taille, openImage startImage) {
		
		int largeur = getLargeur(taille);
		int longeur = getLongeur(taille);
		largeur = largeur*2;
		this.settaille(startImage,largeur,longeur);
		
		while (pixelEnCours != null){									
			pixelEnCours.nombre = pixelEnCours.nombre * 2;						
			pixelEnCours = pixelEnCours.getSuivant(); 			
			}				
	
	}

	@Override
	public int getLargeur(String[] tailleInitial) {
		int largeur = Integer.parseInt(tailleInitial[0]);
				
		return largeur;
	}

	@Override
	public int getLongeur(String[] tailleInitial) {
		int longeur = Integer.parseInt(tailleInitial[1]);
		return longeur;
	}

	@Override
	public int settaille(openImage startImage, int larg, int longs) {
		
		startImage.setTaille(Integer.toString(larg), Integer.toString(longs));
		
		
		return 1;
	}
	
}


class DoubleLongeur implements TraitementTaille {

	
	public DoubleLongeur(FichePixel pixelEnCours,FichePixel pixelEnCoursInv, String[] taille, openImage startImage) {
		
		int largeur = this.getLargeur(taille);
		int longeur = this.getLongeur(taille);
		longeur = longeur*2;
		this.settaille(startImage,largeur,longeur);
		
		CisionParLigne longeurCision = new CisionParLigne(pixelEnCours,largeur,longeur,startImage);
		
		int count = 0 ;
		int countbis =0;
		FichePixel premierDuplique = null ;
		FichePixel enCoursBis = null;
		FichePixel inserPixel = null;
		FichePixel finDeOld = null;
		while (pixelEnCours != null){								
			
			if (count == 0){									
				premierDuplique = pixelEnCours ;				
			}
						
			count = count + pixelEnCours.nombre ;
			
			if (count == largeur){					
				enCoursBis = inserPixel;	
				inserPixel = new FichePixel (pixelEnCours.getRouge(),pixelEnCours.getVert(),pixelEnCours.getBleu(),inserPixel) ;				
				if(enCoursBis!= null) {enCoursBis.setPrecedent(inserPixel);}				
				inserPixel.nombre = pixelEnCours.nombre ;																	
			    premierDuplique.setSuivant(inserPixel);
				inserPixel.setPrecedent(premierDuplique);
				finDeOld = pixelEnCours;
				count = 0;
				countbis= 0;
							
			} else {
				
				enCoursBis = inserPixel;	
				
				inserPixel = new FichePixel (pixelEnCours.getRouge(),pixelEnCours.getVert(),pixelEnCours.getBleu(),inserPixel) ;	
				countbis++;
				 if(enCoursBis!= null && countbis>1) {enCoursBis.setPrecedent(inserPixel);}
				 if(enCoursBis!= null && countbis==1) {
					 finDeOld.setPrecedent(inserPixel);
					 inserPixel.setSuivant(finDeOld);
				 				 
				 
				 }
				 if(enCoursBis == null) {
					 startImage.setDernier(inserPixel);
					 }
				inserPixel.nombre = pixelEnCours.nombre ;											
			}
			
			pixelEnCours = pixelEnCours.getPrecedent();
		
		}
		
		
		
			

				
	}

	@Override
	public int getLargeur(String[] tailleInitial) {
		int largeur = Integer.parseInt(tailleInitial[0]);
				
		return largeur;
	}

	@Override
	public int getLongeur(String[] tailleInitial) {
		int longeur = Integer.parseInt(tailleInitial[1]);
		return longeur;
	}

	@Override
	public int settaille(openImage startImage, int larg, int longs) {
		
		startImage.setTaille(Integer.toString(larg), Integer.toString(longs));
		
		
		return 1;
	}
	
}
	


class CisionParLigne {
	
	public CisionParLigne(FichePixel pixelEnCours, int largeur, int longeur,openImage startImage) {
		// un petit soucis à regler, on a une bande noir à la fin des fichiers cislé, des bandes de pixels disparaissent, il doit manquer un détaille à regler le chainage
		int count = 0 ;
		while (pixelEnCours != null){
									
			
			count = count + pixelEnCours.nombre ;
			if (count > largeur){
				
				int retenu = count - largeur ;
				pixelEnCours.nombre = pixelEnCours.nombre - retenu;																					
				FichePixel inserPixel = new FichePixel (pixelEnCours.getRouge(),pixelEnCours.getVert(),pixelEnCours.getBleu(),pixelEnCours) ;
				inserPixel.nombre = retenu ;
				inserPixel.setPrecedent(pixelEnCours.getPrecedent());			
				FichePixel nextPixel = inserPixel.getPrecedent();
				if(nextPixel != null) {
					nextPixel.setSuivant(inserPixel);
				
				}
				if (pixelEnCours.getPrecedent()== null) {startImage.setPremier(inserPixel);}
				pixelEnCours.setPrecedent(inserPixel);	
				count = 0 ;
			}
			
						
			pixelEnCours = pixelEnCours.getPrecedent(); 
			
			}
		
		
		
		
	}
			
}

class PassageGris implements TraitementCouleur {
	
	

	public PassageGris(FichePixel pixelEnCours) {
		 
		while (pixelEnCours != null){
				
				int rouge, bleu, vert, gris ;
				
				rouge = this.getRouge(pixelEnCours);
				bleu = this.getBleu(pixelEnCours);
				vert = this.getVert(pixelEnCours);
				gris = (int)((rouge+bleu+vert)/3);
				this.setRouge(pixelEnCours,gris);
				this.setVert(pixelEnCours,gris);
				this.setBleu(pixelEnCours,gris);	
				
				pixelEnCours = pixelEnCours.getSuivant(); 
				
				}
		
		
	}
		

	@Override
	public int getRouge(FichePixel aTraiter) {
		
		return aTraiter.getRouge();
	}

	@Override
	public int getBleu(FichePixel aTraiter) {
		
		return aTraiter.getBleu();
	}

	@Override
	public int getVert(FichePixel aTraiter) {
	
		return aTraiter.getVert();
	}

	@Override
	public int setRouge(FichePixel aTraiter, int couleur) {
		
		aTraiter.setRouge(couleur);
		
		return 1 ;
	}

	@Override
	public int setBleu(FichePixel aTraiter, int couleur) {
		aTraiter.setBleu(couleur);
		return 1;
	}

	@Override
	public int setVert(FichePixel aTraiter, int couleur) {
		aTraiter.setVert(couleur);
		return 1;
	}
	
}

class Luminosite implements TraitementCouleur {		
	
public Luminosite(FichePixel pixelEnCours, openImage startImage, boolean eclaire, int couleur, int force) {
			
	if (!eclaire) { force = force*-1 ; }
	 int max = startImage.GetcouleurMax();
	
	while (pixelEnCours != null){
		
		int rouge, bleu, vert, dominante = 0, slaveA =0, slaveB =0;
		rouge = this.getRouge(pixelEnCours);
		bleu = this.getBleu(pixelEnCours);
		vert = this.getVert(pixelEnCours);
		
		if (couleur == 0) { dominante = rouge ; slaveA = vert; slaveB = bleu; }
		else if (couleur == 1) { dominante = vert ; slaveA = rouge; slaveB = bleu; }	
		else if (couleur == 2) { dominante = bleu ; slaveA = vert; slaveB = rouge; }		
		
		if (dominante > slaveA && dominante > slaveB ) {
			
			dominante = dominante +force ;
			slaveA = slaveA +force ;
			slaveB = slaveB + force ;																								
		}
		
		if (dominante > max) { dominante = max ;}
		else if (slaveA > max) { slaveA = max ;}
		else if (slaveB > max) { slaveB = max ;}
		else if (dominante < 1) { dominante = 1 ;}
		else if (slaveA < 1) { slaveA = 1 ;}
		else if (slaveB < 1) { slaveB = 1 ;}
		
		if (couleur == 0) { rouge = dominante ;vert = slaveA ; bleu = slaveB; }
		else if (couleur == 1) { vert = dominante ; rouge = slaveA; bleu = slaveB; }	
		else if (couleur == 2) { bleu = dominante ; vert = slaveA; rouge =slaveB; }	
		
		
		this.setRouge(pixelEnCours,rouge);
		this.setVert(pixelEnCours,bleu);
		this.setBleu(pixelEnCours,vert);	
		
		pixelEnCours = pixelEnCours.getSuivant(); 
		
		}
	
	
	
	
	}

	public int getRouge(FichePixel aTraiter) {
		
		return aTraiter.getRouge();
	}

	@Override
	public int getBleu(FichePixel aTraiter) {
		
		return aTraiter.getBleu();
	}

	@Override
	public int getVert(FichePixel aTraiter) {
	
		return aTraiter.getVert();
	}

	@Override
	public int setRouge(FichePixel aTraiter, int couleur) {
		
		aTraiter.setRouge(couleur);
		
		return 1 ;
	}

	@Override
	public int setBleu(FichePixel aTraiter, int couleur) {
		aTraiter.setBleu(couleur);
		return 1;
	}

	@Override
	public int setVert(FichePixel aTraiter, int couleur) {	
		return 1;
}
}



class decoupage implements TraitementTaille {
	
	public decoupage(FichePixel pixelEnCours,FichePixel pixelEnCoursInv, String[] taille, openImage startImage,int option) {
		
		int largeur = this.getLargeur(taille);
		int longeur = this.getLongeur(taille);
		int L1 =1 ,L2 = longeur,C1 = 1,C2 = largeur ;
		
		CisionParLigne longeurCision = new CisionParLigne(pixelEnCours,largeur,longeur,startImage);
		
		Scanner sc = new Scanner(System.in);
		if (option == 0 | option == 2) {
		System.out.println("Quel est la valeur L1 ? comprise entre 1 & " + (longeur-1));
		 L1 = Integer.parseInt(sc.nextLine());
		System.out.println("Quel est la valeur L2 ? comprise entre "+(L1+2)+" & " + (longeur-1));
		 L2 = Integer.parseInt(sc.nextLine());
		 
		 if (L1<1 |L1> (longeur-1)| L2<L1 | 	L2<1 |L2> (longeur-1)|	C1<1 |C1> (largeur-2)| C2<C1 | 	C2<1 |C2> (largeur-1)) {
				System.out.println ("Erreur dans les donnees rentrees");// c'est une gestion des exceptions faite maison
				System.exit(0);
			}
		 
		this.decoupeLongeur(pixelEnCours,pixelEnCoursInv,taille,startImage,L1,L2);
		}
		if (option == 0 | option == 1) {
		System.out.println("Quel est la valeur C1 ? comprise entre 1 & " + (largeur-1));
		 C1 = Integer.parseInt(sc.nextLine());
		System.out.println("Quel est la valeur C2 ? comprise entre "+(C1+2)+" & " + (largeur));
		 C2 = Integer.parseInt(sc.nextLine());
		 if (L1<1 |L1> (longeur-1)| L2<L1 | 	L2<1 |L2> (longeur-1)|	C1<1 |C1> (largeur-2)| C2<C1 | 	C2<1 |C2> (largeur-1)) {
				System.out.println ("Erreur dans les donnees rentrees");
				System.exit(0);
			}
		this.decoupeLargeur (pixelEnCours,pixelEnCoursInv,taille,startImage,C1,C2);
		this.decoupeLargeurbis (pixelEnCours,pixelEnCoursInv,taille,startImage,C1,C2);
		}
		
		
				
		
			
		
	}
	
	
	void decoupeLargeur (FichePixel pixelEnCours,FichePixel pixelEnCoursInv, String[] taille, openImage startImage, int C1, int C2) {
		
		int largeur = this.getLargeur(taille);
		int longeur = this.getLongeur(taille);
		
		System.out.println("Traitement largeur en cours ...");
		
	//pixelEnCours = startImage.getdernier();	
		
				int count = 0;
				boolean ligneOK =false;
				FichePixel pixelRetenu = null;
				
				while (pixelEnCours != null){					
				count = count + pixelEnCours.nombre ;
					
				if (count >= C2 && !ligneOK){
					// je repete une partie de code déjà écrit dans cision ; apres coup j'aurai du appeler cision avec une largeur C1 et découper; on facturera la modif au clien dans une nouvelle version
					int retenu = count - C2 ;
					pixelEnCours.nombre = pixelEnCours.nombre - retenu;																					
					FichePixel inserPixel = new FichePixel (pixelEnCours.getRouge(),pixelEnCours.getVert(),pixelEnCours.getBleu(),pixelEnCours) ;
					inserPixel.nombre = retenu ;
					inserPixel.setPrecedent(pixelEnCours.getPrecedent());			
					FichePixel nextPixel = inserPixel.getPrecedent();
					if(nextPixel != null) {
						nextPixel.setSuivant(inserPixel);	
					}			
					pixelEnCours.setPrecedent(inserPixel);	
					count =C2;
					ligneOK = true;
					pixelRetenu = pixelEnCours;
				}
				if (count == largeur){
					count =0 ;	
					ligneOK = false;
					if (pixelEnCours.getPrecedent() != null) {
					FichePixel pixelPrecedent = pixelEnCours.getPrecedent();
					pixelPrecedent.setSuivant(pixelRetenu);
					pixelRetenu.setPrecedent(pixelPrecedent);
					}else if(pixelEnCours.getPrecedent() == null) {
						pixelRetenu.setPrecedent(null);
						startImage.setPremier(pixelRetenu);
						}										
				}												
				pixelEnCours = pixelEnCours.getPrecedent();	
				}
												
				largeur = (largeur -(largeur- C2));
				
				startImage.setTaille(Integer.toString(largeur), Integer.toString(longeur));	
		
		
		
		
	}
	
	void decoupeLargeurbis (FichePixel pixelEnCours,FichePixel pixelEnCoursInv, String[] taille, openImage startImage, int C1, int C2) {
		
		int largeur = this.getLargeur(taille);
		int longeur = this.getLongeur(taille);
		
		System.out.println("Traitement largeur en cours ...");
		
	pixelEnCours = startImage.getdernier();	
		
				int count = 0;
				boolean ligneOK =false;
				boolean debut = true;
				
				FichePixel pixelRetenu = null;
				
				while (pixelEnCours != null){					
				count = count + pixelEnCours.nombre ;
				
				if (count >= C1 && !ligneOK){
				int retenu = count - C1 ;
				pixelEnCours.nombre = pixelEnCours.nombre - retenu;	
				FichePixel inserPixel = new FichePixel (pixelEnCours.getRouge(),pixelEnCours.getVert(),pixelEnCours.getBleu(),null) ;
				inserPixel.nombre = retenu ;
				inserPixel.setPrecedent(pixelEnCours.getPrecedent());
				pixelEnCours.setPrecedent(inserPixel);
				//pixelRetenu = inserPixel ;
				
				if (pixelRetenu != null){
					inserPixel.setSuivant (pixelRetenu);
					pixelRetenu.setPrecedent(inserPixel);
					
				}
				//if (inserPixel.getSuivant() == null){
				// startImage.setDernier (inserPixel);
				//}
				if (debut == true){
					startImage.setDernier (inserPixel);
					debut = false ;
				}
					
				ligneOK = true;
				count =C1;
				
				}
				if (count == largeur){
					count =0 ;	
					ligneOK = false;					
					pixelRetenu = pixelEnCours;					
					
				}
								
				pixelEnCours = pixelEnCours.getPrecedent();
				
				}
												
				largeur = (largeur -C1 -(largeur- C2));
				
				startImage.setTaille(Integer.toString(largeur), Integer.toString(longeur));	
			
	}
	
	
	void decoupeLongeur (FichePixel pixelEnCours,FichePixel pixelEnCoursInv, String[] taille, openImage startImage, int L1, int L2) {
	
	
	int largeur = this.getLargeur(taille);
	int longeur = this.getLongeur(taille);
	
	System.out.println("Traitement longeur en cours ...");
	int count = 0 ;
	int compteurLigne = 1;
	FichePixel pixelEnStart = pixelEnCours ;
	
	while (pixelEnCours != null){			
		count = count + pixelEnCours.nombre ;		
	
		
		if (count == largeur){
			compteurLigne = compteurLigne +1 ;
			count =0 ;	
			
			if (compteurLigne == L1){
				
				FichePixel Pixelsuivant = pixelEnCours.getPrecedent();
				Pixelsuivant.setSuivant(null);							
				startImage.setDernier(Pixelsuivant);				
			}
			
			if (compteurLigne == L2){
				pixelEnCours.setPrecedent(null);
				startImage.setPremier(pixelEnCours);				
			}							
		}												
		pixelEnCours = pixelEnCours.getPrecedent(); 		
		}
	
	longeur = (longeur - L1)-(longeur-L2);
	startImage.setTaille(Integer.toString(largeur), Integer.toString(longeur));			
		
		
		
		
	}
		
	
	@Override
	public int getLargeur(String[] tailleInitial) {
		int largeur = Integer.parseInt(tailleInitial[0]);
				
		return largeur;
	}

	@Override
	public int getLongeur(String[] tailleInitial) {
		int longeur = Integer.parseInt(tailleInitial[1]);
		return longeur;
	}

	@Override
	public int settaille(openImage startImage, int larg, int longs) {
		
		startImage.setTaille(Integer.toString(larg), Integer.toString(longs));
		
		
		return 1;
	}
	
}


class negatif implements TraitementCouleur {
	

	public negatif(FichePixel pixelEnCours, openImage startImage) {
		
		while (pixelEnCours != null){
			
			int max = startImage.GetcouleurMax();
			int rouge, bleu, vert, gris ;
			
			rouge = this.getRouge(pixelEnCours);
			bleu = this.getBleu(pixelEnCours);
			vert = this.getVert(pixelEnCours);
			
			vert = max -vert;
			rouge = max -rouge ;
			bleu = max- bleu ;
			this.setRouge(pixelEnCours,rouge);
			this.setVert(pixelEnCours,bleu);
			this.setBleu(pixelEnCours,vert);	
			
			pixelEnCours = pixelEnCours.getSuivant(); 
			
			}
		
	
	}

	@Override
	public int getRouge(FichePixel aTraiter) {
		
		return aTraiter.getRouge();
	}

	@Override
	public int getBleu(FichePixel aTraiter) {
		
		return aTraiter.getBleu();
	}

	@Override
	public int getVert(FichePixel aTraiter) {
	
		return aTraiter.getVert();
	}

	@Override
	public int setRouge(FichePixel aTraiter, int couleur) {
		
		aTraiter.setRouge(couleur);
		
		return 1 ;
	}

	@Override
	public int setBleu(FichePixel aTraiter, int couleur) {
		aTraiter.setBleu(couleur);
		return 1;
	}

	@Override
	public int setVert(FichePixel aTraiter, int couleur) {
		aTraiter.setVert(couleur);
		return 1;
	}
	
	
}




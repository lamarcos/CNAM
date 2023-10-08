package jeucarte;

import java.util.Scanner;

public class PlusOuMoins {
	
	private static int SCORE = 0;
	
	
	public static void jeux (Packet Pa) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bienvenue au plus ou moins sans Patern MVC car je suis pas coder java et je ne connais pas ce truc...");
		System.out.println("Il suffit de cliquer sur + ou - et entrer");

		//System.out.println( Pa.carte.size());
		
		((CarteDeBase) Pa.carte.get(0)).tourner();
		System.out.println( Pa.carte);
		
		for (int i=1; i<Pa.carte.size(); i++) {
			((CarteDeBase) Pa.carte.get(i)).tourner();
			System.out.println("Score : " + SCORE );	
			char reponse = scan(sc);	
			String valEntree = Pa.carte.get(i).toString().substring(1, 3);
			String valEntreeMoin1 = Pa.carte.get(i-1).toString().substring(1, 3);
			int valSortie = getValeur(valEntree);
			int valMoin1 = getValeur(valEntreeMoin1);
			
			if ((reponse == '+' && valSortie >= valMoin1)||(reponse == '-' && valSortie < valMoin1) ) {
				SCORE = SCORE +1;
				System.out.println("Good !!!");
				
			}else {
				
				System.out.println("Bad !!!");
			}
			
			
			System.out.println( Pa.carte);
			
			
			
		}
	
		
		System.out.println("Jeu terminé, Score final :"+ SCORE);	
		
		
		
	}
	
	private static char scan(Scanner sc) {
		System.out.println("Prochaine carte + ou - ? ");
		char rep = 0;
		
		while(2==2) {
		rep = sc.next().charAt(0);
		
		if (rep == '+' || rep == '-') {	
			break ;
		}
		System.out.println("Choix invalide !!!");
		}
		
		return rep ; 
		
	}
	
	private static int getValeur(String val) {
		
		// oui, j'aurai pu creer une énum (je l'ai créér, mais faudrai que je trouve commnt lancer une enum à partir d'un string...
		int valeur = 0 ;
		
			switch (val) {
			
			case "02" :
				valeur = 2 ;
				break;
				
			case "03" :
				valeur = 3 ;
				break;	
				
			case "04" :
				valeur = 4 ;
				break;
				
			case "05" :
				valeur = 5 ;
				break;
				
			case "06" :
				valeur = 6 ;
				break;
				
			case "07" :
				valeur = 7 ;
				break;
				
			case "08" :
				valeur = 8 ;
				break;
				
			case "09" :
				valeur = 9 ;
				break;
				
			case "10" :
				valeur = 10 ;
				break;
				
			case "11" :
				valeur = 11 ;
				break;
				
			case "12" :
				valeur = 12 ;
				break;	
				
			case "13" :
				valeur = 13 ;
				break;
				
			case "14" :
				valeur = 14 ;
				break;
				
			case "15" :
				valeur = 15 ;
				break;
				
			case "16" :
				valeur = 16 ;
				break;
				
			case "17" :
				valeur = 17 ;
				break;
				
			case "18" :
				valeur = 18 ;
				break;
				
			case "20" :
				valeur = 20 ;
				break;
				
			case "21" :
				valeur = 21 ;
				break;
				
			case "01" :
				valeur = 01 ;
				break;
				
			case "VA" :
				valeur = 31 ;
				break;
				
			case "CA" :
				valeur = 32 ;
				break;
				
			case "DA" :
				valeur = 33 ;
				break;
				
			case "RO" :
				valeur = 34 ;
				break;
				
			case "AS" :
				valeur = 35 ;
				break;
			case "??" :
				valeur = 99 ;
				break;	
				
					
			}
				
		
		return valeur ; 
		
	}

}

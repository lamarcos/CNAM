
// ceci est un commentaire, hitoire de dire qu'il y en a un.

import java.util.Scanner ;// y a ecrit dans le sujet que l'on ne devait pas utiliser Arraylist ou tout autre librairy... - 1 pts à tous le monde ;-)

public class Indicage {

	public static void main(String[] argv) throws Exception {	
	
			System.out.println(" ");
			System.out.println(" I'M GET533570 AND YOU ? ");		

			if (argv.length != 0 && argv[0] != null ){
				
				int a = argv[0].length() ;	
				char [] tabInit =	new char[a] ;
				for (int i=0; i<a; i=i+1){
				
					tabInit[i] = argv[0].charAt(i);
				
				}
			
				menu(tabInit);
			} else {
				
				char [] tabInit = {'c','N','a','M',' ','N','f','A','3','1'};
				menu(tabInit);	
			}		
	}
	
	public static void menu (char[] tabTemp) {
				
			System.out.println(" ");
			System.out.println("Que voulez-vous faire ?");
			System.out.println(" ");
			System.out.println("     -(A)fficher le tableau.");
			System.out.println("     -(R)ajouter un character.");
			System.out.println("     -(S)upprimer un character.");	
			System.out.println("     -(Q)uitter");	
			Scanner reponsein = new Scanner(System.in);
			char reponseChar = reponsein.nextLine().charAt(0); 	
			reponses(reponseChar,tabTemp);	
			reponsein.close();
		}
	
	public static void reponses (char reponse ,char[] tabTemp) {
	
		 if (reponse == 'a' || reponse == 'A' ) { 
			 afficher(tabTemp, 0);	 
		 }else if (reponse == 'r' || reponse == 'R' ){			 
			 afficher(tabTemp, 1);	 			 
		 }else if (reponse == 's' || reponse == 'S' ){		 
			 afficher(tabTemp, 2);
		 }else if (reponse == 'q' || reponse == 'Q' ){	
		  	System.exit(0);	 		 
		 }else {
			 System.out.println("Je n ai pas compris votre reponse...");
			 menu (tabTemp) ;	
			
		 }	
		}
		
	public static void afficher (char[] tabTemp, int suite) {
		System.out.println(" ");	
		System.out.print ("|");
		for (int i = 0; i < tabTemp.length;i=i+1){	
			System.out.print (i+1);
			System.out.print ("|");
		}
		System.out.println(" ");
		System.out.print ("|");
		for (int i = 0; i < tabTemp.length;i=i+1){	
			System.out.print (tabTemp[i]);	
			if (i<9){System.out.print ("|");}else{System.out.print (" |");}
		}
		System.out.println("");
		if (suite == 1){rajouter(tabTemp);}else if (suite == 2){supprimer(tabTemp);}else {menu(tabTemp);}	
	}	
	
	public static void rajouter (char[] tabTemp) {
		int indicePos = -1;
		System.out.println(" ");
		System.out.println("apres quelle case voulez vous rajouter un character ? valeur numerique ,(D)ebut,(F)in ou (R)etour au menu ");	
		Scanner reponsein = new Scanner(System.in);
		String reponseSting = reponsein.nextLine(); 
		boolean isNumeric = reponseSting.chars().allMatch(Character::isDigit);
		if (reponseSting.charAt(0) == 'D' || reponseSting.charAt(0) == 'd'){ indicePos = 0 ;}
		else if (reponseSting.charAt(0) == 'F' || reponseSting.charAt(0) == 'f'){ indicePos = tabTemp.length;}
		else if (isNumeric){
			
			if (Integer.parseInt(reponseSting) <=  tabTemp.length && Integer.parseInt(reponseSting)> 0){
				indicePos= Integer.parseInt(reponseSting);
			}else {
				System.out.println("reponse incorrect");
				rajouter(tabTemp);
				return ;
				
			}
		}
		else if (reponseSting.charAt(0) == 'r' || reponseSting.charAt(0) == 'R'){ menu(tabTemp);}
		else {
			System.out.println("reponse incorrect");
			rajouter(tabTemp);
			return ;						
		}
		
		System.out.println("Quel carractere voulez-vous rajouter ?");
		char newChar = reponsein.nextLine().charAt(0);						
		char [] tabNew ;
		tabNew = new char [tabTemp.length + 1] ; 	

		for (int i = 0 ; i<tabNew.length ;i=i+1){
			
			if (i<indicePos) {tabNew[i]=tabTemp[i];}
			else if (i==indicePos) {tabNew[i]=newChar;}
			else if (i>indicePos) {tabNew[i]=tabTemp[i-1];}
								
			
		}
		
		 afficher(tabNew, 0);
	
	}
	
	public static void supprimer (char[] tabTemp) {
	
		int indicePos = -1;
		System.out.println(" ");
		System.out.println("Quel carractere voulez-vous supprimer ? valeur numerique ,(S)pecifier une lettre  ou (R)etour au menu ");
		Scanner reponsein = new Scanner(System.in);
		String reponseSting = reponsein.nextLine(); 
		boolean isNumeric = reponseSting.chars().allMatch(Character::isDigit);
		
		if (reponseSting.charAt(0) == 'S' || reponseSting.charAt(0) == 's'){ 
		
				rechercher(tabTemp);
				return ;
				
		}
		else if (isNumeric){
			
			if (Integer.parseInt(reponseSting) <=  tabTemp.length && Integer.parseInt(reponseSting)> 0){
				indicePos= Integer.parseInt(reponseSting)-1;
			}else {
				System.out.println("reponse incorrect");
				supprimer(tabTemp);
				return ;
				
			}
		}
		else if (reponseSting.charAt(0) == 'r' || reponseSting.charAt(0) == 'R'){ menu(tabTemp);}
		else {
			System.out.println("reponse incorrect");
			supprimer(tabTemp);
			return ;						
		}
	
		char [] tabNew ;
		tabNew = new char [tabTemp.length - 1] ; 	
		for (int i = 0 ; i<tabNew.length ;i=i+1){
			
			if (i<indicePos) {tabNew[i]=tabTemp[i];}
			else if (i>=indicePos) {tabNew[i]=tabTemp[i+1];}						
			
		}
		
		afficher(tabNew, 0);
	
	
	}
	public static void rechercher (char[] tabTemp) {
		
		int j=0 ;
		System.out.println(" ");
		System.out.println("Quel carractere voulez-vous rechercher et supprimer ? (attention a la casse)");
		Scanner reponsein = new Scanner(System.in);
		char charASuppr = reponsein.nextLine().charAt(0); 	
		char [] tabCompteur ;
		tabCompteur = new char [tabTemp.length] ; 
		
		for (int i = 0; i < tabTemp.length;i=i+1){
			
				if (tabTemp[i] == charASuppr){
					j = j+1 ;
					tabCompteur[i] = 1 ;
					
				}else {	tabCompteur[i] = 0 ;}		
		}
		
		if (j == 0) {
			
			System.out.println(" ");
			System.out.println("Aucune instance du caractere trouve ! (casse ?) ");	
			afficher(tabTemp, 0);
			
		}else{
			int z = 0 ;
			char [] tabNew ;
			tabNew = new char [tabTemp.length - j] ;
			for (int i = 0; i < tabTemp.length;i=i+1){
				
				if ( tabCompteur[i] == 0){
					tabNew[i-z] = tabTemp[i] ;					
				}else{z= z+1;}								
			}			
			
			System.out.println(" ");
			System.out.println("Suppression termine : " + j + " instance du caractere " + charASuppr + " supprime" );
			afficher(tabNew, 0);
			
			
		}			
	
	}				
	
}

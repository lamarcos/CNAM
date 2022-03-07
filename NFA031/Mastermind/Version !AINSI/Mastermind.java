package Projet;

import static Projet.RandomMastermind.*;
import java.util.Scanner;

public class Mastermind {
    //ceci est le commentaire pour dire que le code est commenté... : Pour la lisibilité du code pour la correction,
    // je n'ai pas mis de menu ( rejouer, quitter,...)

    public static final int NBESSAIS = 12 ;
    public static Scanner sc = new Scanner(System.in);
    public static int nbsEssai = 1 ;
    public static void main(String[] args) {

        char [] [] tableauJeu = new char[NBESSAIS][NB_COLORS*2];
        String [] combinaisonSecrete = generateRandomCombination();
        System.out.println();
        System.out.println("Bienvenu dans le mastermind de GET533570 (il a un nom chelou le gars...)");
        System.out.println("Vous aurez "+NBESSAIS+" essais pour trouver les "+NB_COLORS +" couleurs (differentes) dans le bon ordre.");
        System.out.println("Pour jouer vous devrez écrire à la suite les lettres qui correspondent à votre combinaison");
        System.out.println("Exemple : Bleu, Rouge, Vert, White. S'ecrira : BRVW");
        System.out.println("La liste des couleurs est : Rouge Jaune Vert Bleu Noir White Purple Cyan" );
        System.out.println("Cela nous donne donc : R J V B N W P C ; Oui, c'est du franglais pour ne pas avoir de doublon...");
        System.out.println("Les reponses du programme seront : X Couleur non présente ; O Present mais a la mauvaise place ; V Couleur a la bonne place."  );
        System.out.println("C'est partie !!!");
        question (tableauJeu , combinaisonSecrete);
    }

    public static void question (char [] [] tableauJeu, String [] combinaisonSecrete  ) {

     System.out.println();
     System.out.println("Essais numéro "+nbsEssai +" - Entrez les "+NB_COLORS+" couleurs :" );
     System.out.println("(Rappel des couleurs: (R)ouge (J)aune (V)ert (B)leu (N)oir (W)hite (P)urple (C)yan )" );
     String reponseBrut = sc.nextLine();
        if (reponseBrut.length()==NB_COLORS) {
            reponseBrut = reponseBrut.toUpperCase();
              for (int i = 0; i < NB_COLORS; i = i + 1) {
                if (reponseBrut.charAt(i)== 'R'|| reponseBrut.charAt(i)== 'J'|| reponseBrut.charAt(i)== 'V'|| reponseBrut.charAt(i)== 'B'|| reponseBrut.charAt(i)== 'N'|| reponseBrut.charAt(i)== 'W'|| reponseBrut.charAt(i)== 'P'|| reponseBrut.charAt(i)== 'C') {
                    tableauJeu [nbsEssai-1] [i] = reponseBrut.charAt(i);
                    if (i > 0) {
                        for (int j = i - 1; j >= 0; j = j - 1) {
                            if (tableauJeu [nbsEssai-1] [i] == tableauJeu [nbsEssai-1] [j]) {
                                System.out.println("Vous avez un doublon de couleur, chaque couleur est unique !!!");
                                question(tableauJeu, combinaisonSecrete);
                                return;
                            }
                        }
                    }
                }else{
                    System.out.println( "Un carractere rentre ne correspond pas a une couleur");
                    question(tableauJeu, combinaisonSecrete);
                    return;
                }
            }
        }else {
            System.out.println("Nombre de couleurs entrées incorrecte !!!");
            question (tableauJeu , combinaisonSecrete);
            return;
        }
        traitementReponse(  tableauJeu,combinaisonSecrete);
    }

    public static void traitementReponse(char [] [] tableauJeu, String [] combinaisonSecrete ){

        for (int i = 0; i< NB_COLORS; i=i+1){
            if (tableauJeu[nbsEssai-1][i] == combinaisonSecrete[i].charAt(0)){
                tableauJeu[nbsEssai-1][i+NB_COLORS] = 'Z';
            }else{
                boolean z= false;
                for (int j=0; j<NB_COLORS ; j=j+1){
                    if (tableauJeu[nbsEssai-1][i] == combinaisonSecrete[j].charAt(0)){
                        z= true;
                        tableauJeu[nbsEssai-1][i+NB_COLORS] = 'O';
                    }
                }
                if (!z){
                    tableauJeu[nbsEssai-1][i+NB_COLORS] = 'X';
                }
            }
        }
     affichage( tableauJeu,combinaisonSecrete);
    }
    public static void affichage (char [] [] tableauJeu, String [] combinaisonSecrete ){

            for (int i=0; i< nbsEssai; i=i+1 ){
            System.out.print("| ");
            for (int j=0; j< NB_COLORS; j=j+1 ){
                System.out.print(miseEnCouleur(tableauJeu[i][j]));
            }
            System.out.print("| ");
            for (int j=NB_COLORS; j< (NB_COLORS*2); j=j+1 ){
                System.out.print(miseEnCouleur(tableauJeu[i][j]));
            }
            System.out.println(" |");
        }
        int victoryDay = 0 ;
        for (int i=NB_COLORS;i< NB_COLORS*2;i=i+1 ){
            if (tableauJeu[nbsEssai-1][i] == 'Z'){
                victoryDay = victoryDay +1 ;
            }else{
                break;
            }
        }
        if (victoryDay == 4){
            System.out.println("--- VICTOIRE !!! Vous avez Trouve la solution en : " +nbsEssai +" Coups ---");
            sc.close();
            System.exit(0);
        }else {

            if (nbsEssai>=NBESSAIS){

                System.out.println("--- PERDU !!! Vous avez atteind le nombre max de coups. ---");
                sc.close();
                System.exit(0);

            }else{
                nbsEssai = nbsEssai +1 ;
                question(tableauJeu, combinaisonSecrete);
            }
        }
    }

    static String miseEnCouleur (char couleurEntree) {
        String couleurSortie = "zz";

        if (couleurEntree == 'B') {
            couleurSortie =  " Bleu  ";
        }else if (couleurEntree == 'R') {
            couleurSortie =  " Rouge ";
        }else if (couleurEntree == 'J') {
            couleurSortie =  " Jaune ";
        }else if (couleurEntree == 'V') {
            couleurSortie =  " Vert  ";
        }else if (couleurEntree == 'N') {
            couleurSortie =  " Noir  ";
        }else if (couleurEntree == 'W') {
            couleurSortie =  " White ";
        }else if (couleurEntree == 'P') {
            couleurSortie =  "Purple ";
        }else if (couleurEntree == 'C') {
            couleurSortie =  " Cyan  ";
        }else if (couleurEntree == 'X') {
            couleurSortie =  " X ";
        }else if (couleurEntree == 'Z') {
            couleurSortie =  " V ";
        }else if (couleurEntree == 'O') {
            couleurSortie =  " O ";
        }
        return couleurSortie ;

    }


}

package Projet;

import static Projet.RandomMastermind.*;

import java.util.Scanner;

public class Mastermind {
    //ceci est le commentaire pour dire que le code est commenté... : Pour la lisibilité du code pour la correction,
    // je n'ai pas mis de menu ( rejouer, quitter,...)

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLACK = "\u001B[30m";
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
        System.out.println("Exemple : " +ANSI_BLUE + "Bleu," + ANSI_RED +" Rouge,"+ANSI_GREEN+" Vert,"+ANSI_WHITE+" White." +
                ANSI_RESET + " S'ecrira : " +ANSI_BLUE + "B" + ANSI_RED +"R"+ANSI_GREEN+"V"+ANSI_WHITE+"W"+ ANSI_RESET);
        System.out.println("La liste des couleurs est :" + ANSI_RED + " Rouge" + ANSI_YELLOW + " Jaune" + ANSI_GREEN +
                " Vert" +ANSI_BLUE + " Bleu" + ANSI_BLACK + " Noir" + ANSI_WHITE + " White " + ANSI_PURPLE + "Purple" +
                ANSI_CYAN + " Cyan"+ ANSI_RESET );
        System.out.println("Cela nous donne donc :" + ANSI_RED + " R" + ANSI_YELLOW + " J" + ANSI_GREEN +
                " V" +ANSI_BLUE + " B" + ANSI_BLACK + " N" + ANSI_WHITE + " W " + ANSI_PURPLE + "P" +
                ANSI_CYAN + " C"+ ANSI_RESET + " ; Oui, c'est du franglais pour ne pas avoir de doublon...");
        System.out.println("Les reponses du programme seront : "+ ANSI_RED + "X" + ANSI_RESET +" Couleur non présente ; " +
                ANSI_BLUE + "O" + ANSI_RESET +" Present mais a la mauvaise place ; " + ANSI_GREEN + "V" + ANSI_RESET +
                " Couleur a la bonne place."  );
        System.out.println("C'est partie !!!");
        question (tableauJeu , combinaisonSecrete);
    }

    public static void question (char [] [] tableauJeu, String [] combinaisonSecrete  ) {

     System.out.println();
     System.out.println("Essais numéro "+nbsEssai +" - Entrez les "+NB_COLORS+" couleurs :" );
     System.out.println("(Rappel des couleurs:" + ANSI_RED + " (R)ouge" + ANSI_YELLOW + " (J)aune" + ANSI_GREEN +
            " (V)ert" +ANSI_BLUE + " (B)leu" + ANSI_BLACK + " (N)oir" + ANSI_WHITE + " (W)hite " + ANSI_PURPLE +
            "(P)urple" + ANSI_CYAN + " (C)yan"+ ANSI_RESET+")" );
     String reponseBrut = sc.nextLine();
        if (reponseBrut.length()==NB_COLORS) {
            reponseBrut = reponseBrut.toUpperCase();
              for (int i = 0; i < NB_COLORS; i = i + 1) {
                if (reponseBrut.charAt(i)== 'R'|| reponseBrut.charAt(i)== 'J'|| reponseBrut.charAt(i)== 'V'|| reponseBrut.charAt(i)== 'B'|| reponseBrut.charAt(i)== 'N'|| reponseBrut.charAt(i)== 'W'|| reponseBrut.charAt(i)== 'P'|| reponseBrut.charAt(i)== 'C') {
                    tableauJeu [nbsEssai-1] [i] = reponseBrut.charAt(i);
                    if (i > 0) {
                        for (int j = i - 1; j >= 0; j = j - 1) {
                            if (tableauJeu [nbsEssai-1] [i] == tableauJeu [nbsEssai-1] [j]) {
                                System.out.println(ANSI_RED + "Vous avez un doublon de couleur, chaque couleur est unique !!!" + ANSI_RESET);
                                question(tableauJeu, combinaisonSecrete);
                                return;
                            }
                        }
                    }
                }else{
                    System.out.println(ANSI_RED + "Un carractere rentre ne correspond pas a une couleur" + ANSI_RESET);
                    question(tableauJeu, combinaisonSecrete);
                    return;
                }
            }
        }else {
            System.out.println(ANSI_RED +"Nombre de couleurs entrées incorrecte !!!"+ ANSI_RESET);
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
            System.out.print(ANSI_RESET+"| ");
            for (int j=NB_COLORS; j< (NB_COLORS*2); j=j+1 ){
                System.out.print(miseEnCouleur(tableauJeu[i][j]));
            }
            System.out.println(ANSI_RESET+" |");
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
            System.out.println(ANSI_GREEN+"--- VICTOIRE !!!"+ ANSI_BLUE + " Vous avez Trouve la solution en : "+
                    ANSI_YELLOW +nbsEssai+ANSI_BLUE +" Coups ---"+ANSI_RESET);
            sc.close();
            System.exit(0);
        }else {

            if (nbsEssai>=NBESSAIS){

                System.out.println("--- "+ANSI_RED +"PERDU !!!"+ ANSI_BLUE + " Vous avez atteind le nombre max de coups. ---"+ ANSI_RESET);
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
            couleurSortie = ANSI_BLUE + " Bleu  ";
        }else if (couleurEntree == 'R') {
            couleurSortie = ANSI_RED + " Rouge ";
        }else if (couleurEntree == 'J') {
            couleurSortie = ANSI_YELLOW + " Jaune ";
        }else if (couleurEntree == 'V') {
            couleurSortie = ANSI_GREEN + " Vert  ";
        }else if (couleurEntree == 'N') {
            couleurSortie = ANSI_BLACK + " Noir  ";
        }else if (couleurEntree == 'W') {
            couleurSortie = ANSI_WHITE + " White ";
        }else if (couleurEntree == 'P') {
            couleurSortie = ANSI_PURPLE + "Purple ";
        }else if (couleurEntree == 'C') {
            couleurSortie = ANSI_CYAN + " Cyan  ";
        }else if (couleurEntree == 'X') {
            couleurSortie = ANSI_RED + " X ";
        }else if (couleurEntree == 'Z') {
            couleurSortie = ANSI_GREEN + " V ";
        }else if (couleurEntree == 'O') {
            couleurSortie = ANSI_BLUE + " O ";
        }
        return couleurSortie ;

    }


}

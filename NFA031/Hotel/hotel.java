package TD8;

import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Hotel {
    public static void main(String[] args) {

        int tableau[] = {0, 2, 1, 0, 0};

        menu(tableau);
       // affichetableau(tableau);

    }

    public static void menu(int[] tableautmp) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est votre choix : 1- Reservation , 2- Depart , 3- Etat des reservations ,4-quitter");
        int reponse = Integer.parseInt(sc.nextLine());

         if (reponse == 1) { reservation(tableautmp) ;}
         else if (reponse == 2) { depart(tableautmp) ;}
         else if (reponse == 3) { afficheTableau(tableautmp) ;}
        else if (reponse == 4) { quite() ;}
        else { menu(tableautmp); }

        // si on rentre un string ou char, le prog plante, à corriger.
        sc.close(); // surement jamais executé, faut lui trouver un vrai endroit

    }

    public static void afficheTableau(int[] tableautmp) {

        System.out.println("*************************************");
        System.out.print("* Chambre  : ");

        for (int i = 1; i < 6; i++) {
            System.out.print(" | " + i  );

        }
        System.out.println(" | * "  );
        System.out.print("* Occupant : ");
        for (int i = 0; i < 5; i++) {
            System.out.print(" | " + tableautmp[i]);
        }
        System.out.println(" | * "  );
        System.out.println("*************************************");
        menu(tableautmp);
        }

    public static void reservation(int[] tableautmp) {
        Scanner sc = new Scanner(System.in);
        System.out.println("combien de nouvelles personne à enregister ?");
        int reponse = Integer.parseInt(sc.nextLine());

        if (reponse>4) {
            System.out.println("Reservation impossible, On aime pas les familles nombreuses.");
        }else {

            if (reponse >2) {
                Boolean u = false;
                for (int i=0; i<5 ; i=i+2){
                    if (tableautmp[i] == 0 && u==false){
                       u = true;
                        tableautmp[i] = reponse;
                        System.out.println("Reservation reussi");
                        System.out.println("Chambre numero : " + (i+1) + " ."  );
                        break;

                    }

                }
                if (u== false){System.out.println("Reservation échoué : Hotel complet en chambre de 4");}

            }else {

                Boolean u = false;
                for (int i=1; i<5 ; i=i+2){
                    if (tableautmp[i] == 0 && u==false){
                        u = true;
                        tableautmp[i] = reponse;
                        System.out.println("Reservation reussi en chambre de 2");
                        System.out.println("Chambre numero : " + (i+1) + " ."  );
                        break;
                    }
                }

                for (int i=0; i<5 ; i=i+2){
                    if (tableautmp[i] == 0 && u==false){
                        u = true;
                        tableautmp[i] = reponse;
                        System.out.println("Reservation reussi en chambre de 4");
                        System.out.println("Chambre numero : " + (i+1) + " ."  );
                        break;
                    }
                }
                if (u== false){System.out.println("Reservation échoué : Hotel complet");}

                }
        }
       // sc.close();  // désactivé car provoque la panne de menu, fuite de memoire a corriger.
        afficheTableau(tableautmp);

    }

    public static void depart(int[] tableautmp){

        Scanner sc = new Scanner(System.in);
        System.out.println("Départ : quel numeros de chambre ?");
        int reponse =  Integer.parseInt(sc.nextLine());
        reponse = reponse -1;
        System.out.println("Départ de la chambre complete ? (O / N)");
        String reponseFull = sc.nextLine();
        int resultefinal = -1;
        int comparestr = reponseFull.compareToIgnoreCase("o");
         if (comparestr == 0 ){ resultefinal =0 ;}
         else {
             System.out.println("Nombre de personne restant dans la chambre ?");
             resultefinal =  Integer.parseInt(sc.nextLine());

         }
        if (resultefinal >tableautmp[reponse] ) {
            System.out.println("impossible il y a plus de personne dans la chambre qu avant le depart");
        }else {

            tableautmp[reponse] = resultefinal;
            System.out.println("Départ validé");
        }

        // sc.close();  // désactivé car provoque la panne de menu, fuite de memoire à corriger.
        afficheTableau(tableautmp)  ;

    }


    public static void quite(){
       // sc.close();  // n existe pas dans cette instance.
        System.exit(0);

    }

}

package Projet;

public final class RandomMastermind
{

    static String [] TAB_REF_COLORS = {
            "Rouge",
            "Jaune",
            "Vert",
            "Bleu",
            "Noir",
            "White",
            "Purple",
            "Cyan"
    };

    static int NB_COLORS = 4;

    //public static void main(String[] args) {
        // Pour utiliser generateRandomCombination, il suffit de recupérer le tableau de couleurs généré.
       // String [] combinaisonSecrete = generateRandomCombination();
      //  for(int i=0;i<4;i++) {
      //      System.out.println(combinaisonSecrete[i]);
      //  }
      //  System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);

    //}

    // Generate random combination of 4 colors
    static String [] generateRandomCombination() {
        String [] combination = new String[NB_COLORS];
        int currentPosition = 0;
        while(currentPosition!=NB_COLORS) {
            int indexRandom = (int)(Math.random()*TAB_REF_COLORS.length);
            String color = TAB_REF_COLORS[indexRandom];
            if(!isIn(color, combination)) {
                combination[currentPosition] = color;
                currentPosition++;
            }
        }
        return combination;
    }

    static boolean isIn(String iStringToFind, String [] iTab) {
        int size = iTab.length;
        for(int i=0;i<size;i++) {
            if(iStringToFind.equalsIgnoreCase(iTab[i])) return true;
        }
        return false;
    }



}

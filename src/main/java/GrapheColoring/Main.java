package GrapheColoring;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            System.out.println("Action : ");
            System.out.println("1. Graphe simple");
            System.out.println("2. Graphe avec des arêtes préférences");
            System.out.println("3. Graphe avec du spill (version pessimiste et optimiste");
            System.out.println("4. Quitter le programme\n");

            System.out.println("Insérer le numéro de l'action à effectuer :");

            Scanner scanner = new Scanner(System.in);

            boolean estUnInteger = false;
            int choix = 4;

            while (!estUnInteger) {
                try {
                    choix = scanner.nextInt();
                    estUnInteger = true;
                }
                catch (InputMismatchException e) {
                    choix = scanner.nextInt();
                }
            }

            switch (choix) {
                case 1:
                    grapheSimpleTest();
                    break;
                case 2:
                    grapheAvecPreferenceTest();
                    break;
                case 3:
                    grapheAvecSpillTest();
                    break;
                default :
                    System.exit(0);
            }

            System.out.println("Voulez-vous voir un autre graphe ? :");
            System.out.println("1. Oui");
            System.out.println("2. Non");

            estUnInteger = false;
            choix = 2;

            while (!estUnInteger) {
                try {
                    choix = scanner.nextInt();
                    estUnInteger = true;
                }
                catch (InputMismatchException e) {
                    choix = scanner.nextInt();
                }
            }

            if (choix != 1) {
                System.exit(0);
            }


        }

    }

    private static void grapheAvecSpillTest() {

        ArrayList<Sommet> listeSommets;
        ArrayList<Arete> listeAretesPreference;
        ArrayList<Arete> listeAretesInterference;

        Graphe graphe;

        // Graphe avec du spill
        // Premier affichage avec la version pessimiste de l'algorithme, et le deuxieme avec la version optimiste.

        Sommet s10 = new Sommet("A");
        Sommet s11 = new Sommet("B");
        Sommet s12 = new Sommet("C");
        Sommet s13 = new Sommet("D");

        Arete a10 = new Arete(s10,s11);
        Arete a11 = new Arete(s11,s12);
        Arete a12 = new Arete(s12,s13);
        Arete a13 = new Arete(s13,s10);

        listeAretesInterference = new ArrayList<>();
        listeAretesInterference.add(a10);
        listeAretesInterference.add(a11);
        listeAretesInterference.add(a12);
        listeAretesInterference.add(a13);

        listeAretesPreference = new ArrayList<>();

        listeSommets = new ArrayList<>();
        listeSommets.add(s10);
        listeSommets.add(s11);
        listeSommets.add(s12);
        listeSommets.add(s13);

        graphe = new Graphe(listeSommets, listeAretesInterference, listeAretesPreference);

        graphe.afficherRelation();

        System.out.println("Affichage du graphe (vu en cours avec l'algorithme pessimiste");

        colorerGraphePessimiste(graphe, 2);
        System.out.println(graphe);

        System.out.println("------------\n");

        System.out.println("Affichage du graphe (vu en cours avec l'algorithme optimiste");

        graphe = new Graphe(listeSommets, listeAretesInterference, listeAretesPreference);

        colorerGrapheOptimiste(graphe, 2);
        System.out.println(graphe);

    }

    private static void grapheAvecPreferenceTest() {

        ArrayList<Sommet> listeSommets;
        ArrayList<Arete> listeAretesPreference;
        ArrayList<Arete> listeAretesInterference;

        Graphe graphe;

        // Graphe avec des preferences

        Sommet s6 = new Sommet("A");
        Sommet s7 = new Sommet("B");
        Sommet s8 = new Sommet("C");
        Sommet s9 = new Sommet("D");
        Sommet s14 = new Sommet("E");

        Arete a6 = new Arete(s6,s7);
        Arete a7 = new Arete(s6,s8);
        Arete a8 = new Arete(s8,s9);
        Arete a9 = new Arete(s7,s8); //Preference
        Arete a14 = new Arete(s14,s7);
        Arete a15 = new Arete(s14,s6);


        listeAretesInterference = new ArrayList<>();
        listeAretesInterference.add(a6);
        listeAretesInterference.add(a7);
        listeAretesInterference.add(a8);
        listeAretesInterference.add(a14);
        listeAretesInterference.add(a15);


        listeAretesPreference = new ArrayList<>();
        listeAretesPreference.add(a9);

        listeSommets = new ArrayList<>();
        listeSommets.add(s6);
        listeSommets.add(s7);
        listeSommets.add(s8);
        listeSommets.add(s9);
        listeSommets.add(s14);


        graphe = new Graphe(listeSommets, listeAretesInterference, listeAretesPreference);

        graphe.afficherRelation();

        colorerGraphe(graphe, 3);
        System.out.println(graphe);
    }

    private static void grapheSimpleTest() {

        ArrayList<Arete> listeAretesInterference;
        ArrayList<Sommet> listeSommets;

        Graphe graphe;

        // Graphe simple
        Sommet s1 = new Sommet("A");
        Sommet s2 = new Sommet("B");
        Sommet s3 = new Sommet("C");
        Sommet s4 = new Sommet("D");
        Sommet s5 = new Sommet("E");

        Arete a1 = new Arete(s1,s2);
        Arete a2 = new Arete(s1,s3);
        Arete a3 = new Arete(s2,s3);
        Arete a4 = new Arete(s3,s4);
        Arete a5 = new Arete(s2,s5);


        listeAretesInterference = new ArrayList<>();
        listeAretesInterference.add(a1);
        listeAretesInterference.add(a2);
        listeAretesInterference.add(a3);
        listeAretesInterference.add(a4);
        listeAretesInterference.add(a5);

        listeSommets = new ArrayList<>();
        listeSommets.add(s1);
        listeSommets.add(s2);
        listeSommets.add(s3);
        listeSommets.add(s4);
        listeSommets.add(s5);

        graphe = new Graphe(listeSommets, listeAretesInterference);

        graphe.afficherRelation();

        colorerGraphe(graphe, 3);
        System.out.println(graphe);
    }

    private static void colorerGraphe(Graphe graphe, int nbColor){
        colorerGraphePessimiste(graphe, nbColor);
        colorerGrapheOptimiste(graphe,nbColor);
    }

    private static void colorerGrapheOptimiste(Graphe graphe, int nbColor) {
        for(Sommet sommet : graphe.getSommets()){
            if(sommet.getCouleur() == -1){
                for(int i = 1; i <= nbColor; i++){
                    if(graphe.peutEtreColore(sommet,i)){
                        sommet.setCouleur(i);
                    }
                }
            }
        }
    }

    private static void colorerGraphePessimiste(Graphe graphe, int nbColor){

        if(!graphe.getSommets().isEmpty()) {
            boolean estTrouve = false;

            Sommet sommetTrivial = new Sommet("vide");
            for (Sommet sommet : graphe.getSommets()) {
                if (graphe.trivialementColorable(sommet, nbColor)) {
                    estTrouve = true;
                    sommetTrivial = sommet;
                    break;
                }
            }

            ArrayList<Sommet> nouveauxSommets;
            ArrayList<Arete> nouvellesAretesInterferences;
            ArrayList<Arete> nouvellesAretesPreferences;

            // Si un sommet trivialement coloriable existe
            if (estTrouve) {

                // Alors, on exécute colorerGraphePessimiste sans ce sommet
                nouveauxSommets = graphe.getNouveauxSommets(sommetTrivial);
                nouvellesAretesInterferences = graphe.getNouvellesAretesInterferences(sommetTrivial);
                nouvellesAretesPreferences = graphe.getNouvelleAretePreference(sommetTrivial);
                colorerGraphePessimiste(new Graphe(nouveauxSommets, nouvellesAretesInterferences, nouvellesAretesPreferences), nbColor);

                // Coloration du sommet avec la couleur disponible
                graphe.colorerSommet(sommetTrivial, nbColor);

            } else { // Non trivialement colorable

                Sommet sommetDegreMax = new Sommet("vide");
                for (Sommet sommet : graphe.getSommets()) {
                    if (graphe.interDegre(sommet) == graphe.maximumDegre()) {
                        sommetDegreMax = sommet;
                    }
                }

                nouveauxSommets = graphe.getNouveauxSommets(sommetDegreMax);
                nouvellesAretesInterferences = graphe.getNouvellesAretesInterferences(sommetDegreMax);
                nouvellesAretesPreferences = graphe.getNouvelleAretePreference(sommetDegreMax);
                colorerGraphePessimiste(new Graphe(nouveauxSommets, nouvellesAretesInterferences, nouvellesAretesPreferences), nbColor);
            }
        }
    }
}

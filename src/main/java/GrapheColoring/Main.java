package GrapheColoring;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Graphe simple
        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Sommet s3 = new Sommet(3);
        Sommet s4 = new Sommet(4);
        Sommet s5 = new Sommet(5);

        Arete a1 = new Arete(s1,s2);
        Arete a2 = new Arete(s1,s3);
        Arete a3 = new Arete(s2,s3);
        Arete a4 = new Arete(s3,s4);
        Arete a5 = new Arete(s2,s5);


        ArrayList<Arete> listeAretesInterference = new ArrayList<>();
        listeAretesInterference.add(a1);
        listeAretesInterference.add(a2);
        listeAretesInterference.add(a3);
        listeAretesInterference.add(a4);
        listeAretesInterference.add(a5);

        ArrayList<Sommet> listeSommets = new ArrayList<>();
        listeSommets.add(s1);
        listeSommets.add(s2);
        listeSommets.add(s3);
        listeSommets.add(s4);
        listeSommets.add(s5);

        Graphe g = new Graphe(listeSommets, listeAretesInterference);
        colorerGraphe(g, 3);
        System.out.println(g);

        // Graphe avec des preferences

        Sommet s6 = new Sommet(6);
        Sommet s7 = new Sommet(7);
        Sommet s8 = new Sommet(8);
        Sommet s9 = new Sommet(9);
        Sommet s14 = new Sommet(10);

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


        ArrayList<Arete> listAretePref = new ArrayList<>();
        listAretePref.add(a9);

        listeSommets = new ArrayList<>();
        listeSommets.add(s6);
        listeSommets.add(s7);
        listeSommets.add(s8);
        listeSommets.add(s9);
        listeSommets.add(s14);


        g = new Graphe(listeSommets, listeAretesInterference, listAretePref);
        colorerGraphe(g, 3);
        System.out.println(g);

        // Graphe avec du spill
        // Premier affichage avec la version pessimiste de l'algorithme, et le deuxieme avec la version optimiste.

        Sommet s10 = new Sommet(10);
        Sommet s11 = new Sommet(11);
        Sommet s12 = new Sommet(12);
        Sommet s13 = new Sommet(13);

        Arete a10 = new Arete(s10,s11);
        Arete a11 = new Arete(s11,s12);
        Arete a12 = new Arete(s12,s13);
        Arete a13 = new Arete(s13,s10);

        listeAretesInterference = new ArrayList<>();
        listeAretesInterference.add(a10);
        listeAretesInterference.add(a11);
        listeAretesInterference.add(a12);
        listeAretesInterference.add(a13);

        listAretePref = new ArrayList<>();

        listeSommets = new ArrayList<>();
        listeSommets.add(s10);
        listeSommets.add(s11);
        listeSommets.add(s12);
        listeSommets.add(s13);

        g = new Graphe(listeSommets, listeAretesInterference, listAretePref);

        colorerGraphe(g, 2);
        System.out.println(g);

    }

    private static void colorerGraphe(Graphe graphe, int nbColor){
        colorerGraphePessimiste(graphe, nbColor);
        colorerGrapheOptimiste(graphe,nbColor);
    }

    private static void colorerGrapheOptimiste(Graphe graphe, int nbColor) {
        for(Sommet s : graphe.getSommets()){
            if(s.getCouleur() == -1){
                for(int i = 1; i <= nbColor; i++){
                    if(graphe.peutEtreColore(s,i)){
                        s.setCouleur(i);
                    }
                }
            }
        }
    }

    private static void colorerGraphePessimiste(Graphe graphe, int nbColor){

        if(graphe.getSommets().size() != 0) {
            boolean estTrouve = false;

            Sommet sommetTrivial = new Sommet(-1);
            for (Sommet s : graphe.getSommets()) {
                if (graphe.trivialementColorable(s, nbColor)) {
                    estTrouve = true;
                    sommetTrivial = s;
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

                Sommet sommetDegreMax = new Sommet(-1);
                for (Sommet s : graphe.getSommets()) {
                    if (graphe.interDegre(s) == graphe.maximumDegre()) {
                        sommetDegreMax = s;
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

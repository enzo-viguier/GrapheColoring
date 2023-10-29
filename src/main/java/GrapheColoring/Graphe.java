package GrapheColoring;

import java.util.ArrayList;

public class Graphe {

    private ArrayList<Sommet> sommets;
    private ArrayList<Arete> aretesPreferences;
    private ArrayList<Arete> aretesInterferences;


    Graphe(ArrayList<Sommet> sommets, ArrayList<Arete> interferences, ArrayList<Arete> preferences){
        aretesPreferences = preferences;
        aretesInterferences = interferences;
        this.sommets = sommets;
    }

    Graphe(ArrayList<Sommet> sommets, ArrayList<Arete> interferences){
        aretesPreferences = new ArrayList<>();
        aretesInterferences = interferences;
        this.sommets = sommets;
    }

    public int interDegre(Sommet s) {
        int compteur = 0;
        for (Arete a : aretesInterferences) {
            if (a.implique(s)) {
                compteur++;
            }
        }
        return compteur;
    }

    public int maximumDegre(){
        int max = 0;
        for(Sommet s : sommets){
            if(interDegre(s) > max){
                max = interDegre(s);
            }
        }
        return max;
    }

    public boolean trivialementColorable(Sommet s, int nbColor){
        return interDegre(s) < nbColor;
    }

    public ArrayList<Sommet> getNouveauxSommets(Sommet sommet) {
        ArrayList<Sommet> res = new ArrayList<>();
        for(Sommet s : sommets){
            if(!s.equals(sommet)){
                res.add(s);
            }
        }
        return res;
    }

    public ArrayList<Arete> getNouvellesAretesInterferences(Sommet sTrivial) {
        ArrayList<Arete> res = new ArrayList<>();
        for(Arete a : aretesInterferences){
            if(!a.implique(sTrivial)){
                res.add(a);
            }
        }
        return res;
    }

    public ArrayList<Arete> getNouvelleAretePreference(Sommet sTrivial) {
        ArrayList<Arete> res = new ArrayList<>();
        for(Arete a : aretesPreferences){
            if(!a.implique(sTrivial)){
                res.add(a);
            }
        }
        return res;
    }

    public void colorerSommet(Sommet sommetTrivial, int nbColor) {
        if(couleurPreference(sommetTrivial) != -1 && peutEtreColore(sommetTrivial, couleurPreference(sommetTrivial))){
            sommetTrivial.setCouleur(couleurPreference(sommetTrivial));
        } else {
            for (int i = 1; i <= nbColor; i++) {
                if(peutEtreColore(sommetTrivial,i)){
                    sommetTrivial.setCouleur(i);
                    break;
                }
            }
        }
    }

    public boolean peutEtreColore(Sommet sTrivial, int color){
        for(Arete a : aretesInterferences){
            if(a.implique(sTrivial)){
                if(a.sommets[0].getCouleur() == color||a.sommets[1].getCouleur() == color){
                    return false;
                }
            }
        }
        return true;
    }

    private int couleurPreference(Sommet sTrivial){
        for(Arete a : aretesPreferences){
            if(a.implique(sTrivial)){
                if(a.sommets[0].equals(sTrivial)){
                    if(a.sommets[1].getCouleur() != -1){
                        return a.sommets[1].getCouleur();
                    }
                } else {
                    if(a.sommets[0].getCouleur() != -1){
                        return a.sommets[0].getCouleur();
                    }
                }
            }
        }
        return -1;
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void afficherRelation() {

        System.out.println("--------------------------");
        System.out.println("Relation du graphe : ");

        for (Arete arete : this.aretesInterferences) {
            System.out.println(arete.getArete());
        }

        for (Arete arete : this.aretesPreferences) {
            System.out.println(arete.getArete());
        }
        System.out.println("--------------------------\n");

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Sommet s : sommets){
            sb.append("Sommet ");
            sb.append(s.nom);
            sb.append(" est coloré avec : ");
            sb.append(s.couleur);
            sb.append("\n");
        }
        return sb.toString();
    }
}

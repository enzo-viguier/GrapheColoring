package GrapheColoring;

public class Sommet {

    int couleur = -1;
    int nom;

    public Sommet(int nom){
        this.nom = nom;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return nom + "  coloré avec  " + couleur;
    }

}

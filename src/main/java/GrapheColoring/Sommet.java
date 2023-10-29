package GrapheColoring;

public class Sommet {

    int couleur = -1;
    String nom;

    public Sommet(String nom){
        this.nom = nom;
    }

    public int getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return nom + "  coloré avec  " + couleur;
    }

}

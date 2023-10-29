package GrapheColoring;

public class Arete {

    Sommet[] sommets = new Sommet[2];

    public Arete(Sommet s1, Sommet s2){
        this.sommets[0] = s1;
        this.sommets[1] = s2;
    }

    public boolean implique(Sommet s) {
        return (sommets[0].equals(s)|| sommets[1].equals(s));
    }

    public String getArete(){
        return sommets[0].getNom() + " -> " + sommets[1].getNom();
    }

    @Override
    public String toString() {
        return "Cette arête lie le sommet " + sommets[0].toString() + " et le sommet " + sommets[1].toString();
    }


}

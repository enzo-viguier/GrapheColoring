import GrapheColoring.Graphe;
import GrapheColoring.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrapheSimple {

    Graphe graphe = new Graphe();

    Sommet A = new Sommet("A");
    Sommet B = new Sommet("B");
    Sommet C = new Sommet("C");
    Sommet D = new Sommet("D");

    int nombreDeCouleurs;

    @Before
    public void setUp() {

        A.ajouterVoisin(B);
        A.ajouterVoisin(C);

        B.ajouterVoisin(A);
        B.ajouterVoisin(C);
        B.ajouterVoisin(D);

        C.ajouterVoisin(A);
        C.ajouterVoisin(B);
        C.ajouterVoisin(D);

        D.ajouterVoisin(B);
        D.ajouterVoisin(C);

        nombreDeCouleurs = 4;

    }

    @Test
    public void LeGrapheEstColoriable() {

        if (graphe.estColoriable(nombreDeCouleurs)) {
            System.out.println("Le graphe est coloriable avec " + nombreDeCouleurs + " couleurs.");
        } else {
            System.out.println("Le graphe n'est pas coloriable avec " + nombreDeCouleurs + " couleurs.");
        }
    }

    @Test
    public void leSommetPossedeLeBonDegre() {
        int nbDegreA = A.getDegre();
        int nbDegreB = B.getDegre();
        int nbDegreC = C.getDegre();
        int nbDegreD = D.getDegre();

        assertEquals(2, nbDegreA);
        assertEquals(3, nbDegreB);
        assertEquals(3, nbDegreC);
        assertEquals(2, nbDegreD);


    }

}

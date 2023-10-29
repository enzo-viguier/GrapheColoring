import GrapheColoring.Graphe;
import GrapheColoring.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrapheDiamand {

    Graphe graphe = new Graphe();

    Sommet A = new Sommet("A");
    Sommet B = new Sommet("B");
    Sommet C = new Sommet("C");
    Sommet D = new Sommet("D");

    int nombreDeCouleurs;

    @Before
    public void setUp() {

        A.ajouterVoisin(B);
        B.ajouterVoisin(C);
        C.ajouterVoisin(D);
        D.ajouterVoisin(A);

        nombreDeCouleurs = 1;

    }

    @Test
    public void grapheColoriable() {

        if (graphe.estColoriable(nombreDeCouleurs)) {
            System.out.println("Le graphe est coloriable avec " + nombreDeCouleurs + " couleurs.");
        } else {
            System.out.println("Le graphe n'est pas coloriable avec " + nombreDeCouleurs + " couleurs.");
        }
    }

    @Test
    public void testMethod() {
        int result = 8;
        assertEquals(8, result); // Vérifie que 5 + 3 = 8
    }

}

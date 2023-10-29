import GrapheColoring.Graphe;
import GrapheColoring.Sommet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrapheComplexe {

    Graphe graphe = new Graphe();

    Sommet A = new Sommet("A");
    Sommet B = new Sommet("B");
    Sommet C = new Sommet("C");
    Sommet D = new Sommet("D");
    Sommet E = new Sommet("E");
    Sommet F = new Sommet("F");

    int nombreDeCouleurs;

    @Before
    public void setUp() {

        A.ajouterVoisin(B);
        A.ajouterVoisin(E);
        A.ajouterVoisin(F);

        B.ajouterVoisin(A);
        B.ajouterVoisin(F);
        B.ajouterVoisin(C);

        C.ajouterVoisin(B);
        C.ajouterVoisin(F);
        C.ajouterVoisin(D);

        D.ajouterVoisin(C);
        D.ajouterVoisin(E);

        E.ajouterVoisin(D);
        E.ajouterVoisin(A);
        E.ajouterVoisin(F);

        F.ajouterVoisin(E);
        F.ajouterVoisin(A);
        F.ajouterVoisin(B);
        F.ajouterVoisin(C);

        nombreDeCouleurs = 4;

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

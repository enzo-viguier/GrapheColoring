package GrapheColoringTest;

import org.junit.Test;
import GrapheColoring.Arete;
import GrapheColoring.Sommet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class AreteTest {

    @Test
    public void testImplique() {
        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(1);
        Arete arete = new Arete(s1, s2);

        assertTrue(arete.implique(s1));
        assertTrue(arete.implique(s2));
        assertFalse(arete.implique(new Sommet(3)));
    }

    @Test
    public void testToString() {
        Sommet s1 = new Sommet(1);
        Sommet s2 = new Sommet(2);
        Arete arete = new Arete(s1, s2);

        String expectedString = "Cette arête lie le sommet " + s1.toString() + " et le sommet " + s2.toString();
        assertEquals(expectedString, arete.toString());
    }

}

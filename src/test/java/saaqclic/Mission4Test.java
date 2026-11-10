package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 4 : génériques")
class Mission4Test {

    private static final double DELTA = 0.001;

    @Test
    @DisplayName("Registre a un paramètre de type")
    void registreGenerique() {
        assertEquals(1, Registre.class.getTypeParameters().length, "Déclarez public class Registre<T>");
    }

    @Test
    @DisplayName("Registre ajoute et obtient avec T, pas avec Object")
    void signatures() throws Exception {
        assertTrue(Registre.class.getMethod("obtenir", int.class).getGenericReturnType() instanceof TypeVariable,
                "obtenir doit retourner T");
        Method ajouter = null;
        for (Method m : Registre.class.getMethods()) {
            if (m.getName().equals("ajouter")) {
                ajouter = m;
            }
        }
        assertTrue(ajouter != null && ajouter.getGenericParameterTypes()[0] instanceof TypeVariable,
                "ajouter doit recevoir un T");
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    @DisplayName("Registre garde ses éléments")
    void registreFonctionne() {
        Registre registre = new Registre();
        registre.ajouter("Lina");
        registre.ajouter("Marc");
        assertEquals(2, registre.taille(), "Deux éléments ajoutés");
        assertEquals("Marc", registre.obtenir(1), "La position 1 contient Marc");
    }

    @Test
    @DisplayName("max est une méthode générique bornée")
    void maxSignature() throws Exception {
        Method max = null;
        for (Method m : Util.class.getMethods()) {
            if (m.getName().equals("max")) {
                max = m;
            }
        }
        assertTrue(max != null, "Écrivez la méthode max");
        assertEquals(1, max.getTypeParameters().length, "Déclarez <T extends Comparable<T>> avant le type de retour");
        assertTrue(max.getTypeParameters()[0].getBounds()[0].getTypeName().startsWith("java.lang.Comparable"),
                "T doit être borné par Comparable<T>");
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    @DisplayName("max fonctionne avec des types différents")
    void maxFonctionne() {
        assertEquals(Integer.valueOf(7), Util.max(new Integer[] {3, 7, 2}), "Le plus grand entier est 7");
        assertEquals("Roy", Util.max(new String[] {"Gagnon", "Roy", "Lavoie"}), "Roy est le plus grand texte");
        Comparable[] panier = {
            (Comparable) (Object) new Renouvellement("R-1", 1, false),
            (Comparable) (Object) new Amende("A-1", 500.0, 0)
        };
        assertEquals(panier[1], Util.max(panier), "La transaction la plus chère est l'amende");
    }

    @Test
    @DisplayName("max d'un tableau vide ou null : null")
    void maxVide() {
        assertNull(Util.max(new Integer[0]), "Tableau vide : retournez null");
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    @DisplayName("totalListe accepte une liste d'enfants")
    void joker() throws Exception {
        String type = Caisse.class.getMethod("totalListe", List.class).getGenericParameterTypes()[0].getTypeName();
        assertTrue(type.contains("? extends saaqclic.Transaction"), "Le paramètre doit être List<? extends Transaction>");
        List liste = new ArrayList<Renouvellement>();
        liste.add(new Renouvellement("R-1", 2, false));
        assertEquals(60.0, Caisse.totalListe(liste), DELTA, "Deux ans au comptoir font 60 $");
    }
}

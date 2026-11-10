package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 2 : trier")
class Mission2Test {

    private static Transaction immat(String reference) {
        return new Immatriculation(reference, "ABC 123");
    }

    private static List<Transaction> panier() {
        List<Transaction> liste = new ArrayList<>();
        liste.add(new Immatriculation("B-2", "ZZZ 999"));
        liste.add(new Renouvellement("C-3", 1, false));
        liste.add(new Amende("A-1", 500.0, 0));
        return liste;
    }

    @SuppressWarnings("unchecked")
    private static Comparable<Transaction> comparable(Transaction t) {
        Object o = t;
        return assertInstanceOf(Comparable.class, o, "Transaction doit implémenter Comparable<Transaction>");
    }

    @Test
    @DisplayName("compareTo compare les montants")
    void compareTo() {
        Transaction petite = new Renouvellement("R-1", 1, false);
        Transaction grande = new Amende("A-1", 500.0, 0);
        assertTrue(comparable(petite).compareTo(grande) < 0, "30 $ vient avant 500 $ : le résultat doit être négatif");
        assertTrue(comparable(grande).compareTo(petite) > 0, "500 $ vient après 30 $ : le résultat doit être positif");
        assertEquals(0, comparable(grande).compareTo(new Amende("A-2", 500.0, 0)), "Même montant : le résultat doit être 0");
    }

    @Test
    @DisplayName("triees retourne une copie triée par montant")
    void triees() {
        List<Transaction> depart = panier();
        List<Transaction> resultat = Caisse.triees(depart);
        assertNotSame(depart, resultat, "triees doit retourner une nouvelle liste");
        assertEquals("C-3", resultat.get(0).getReference(), "La moins chère vient d'abord");
        assertEquals("A-1", resultat.get(2).getReference(), "La plus chère vient à la fin");
        assertEquals("B-2", depart.get(0).getReference(), "La liste de départ ne doit pas changer");
    }

    @Test
    @DisplayName("parReference trie par référence")
    void parReference() {
        List<Transaction> liste = panier();
        liste.sort(Tris.parReference());
        assertEquals("A-1", liste.get(0).getReference(), "A-1 vient d'abord");
        assertEquals("C-3", liste.get(2).getReference(), "C-3 vient à la fin");
    }

    @Test
    @DisplayName("parReference est écrite avec une classe anonyme")
    void classeAnonyme() {
        assertTrue(Tris.parReference().getClass().isAnonymousClass(),
                "parReference doit retourner new Comparator<Transaction>() { ... }");
    }

    @Test
    @DisplayName("parDescription trie par description")
    void parDescription() {
        List<Transaction> liste = panier();
        liste.sort(Tris.parDescription());
        assertEquals("A-1", liste.get(0).getReference(), "Amende vient d'abord");
        assertEquals("C-3", liste.get(2).getReference(), "Renouvellement vient à la fin");
    }

    @Test
    @DisplayName("parDescription est écrite avec une lambda")
    void lambda() throws Exception {
        String source = Files.readString(Path.of("src/main/java/saaqclic/Tris.java"));
        assertTrue(source.contains("->"), "parDescription doit utiliser une expression lambda");
    }
}

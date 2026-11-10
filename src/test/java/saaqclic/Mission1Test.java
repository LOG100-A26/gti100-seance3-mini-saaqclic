package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 1 : interfaces")
class Mission1Test {

    private static final double DELTA = 0.001;

    private static Paiement carte(double plafond) {
        Object o = new CarteCredit("4242", plafond);
        return assertInstanceOf(Paiement.class, o, "CarteCredit doit implémenter Paiement");
    }

    private static Paiement interac(double solde) {
        Object o = new Interac(solde);
        return assertInstanceOf(Paiement.class, o, "Interac doit implémenter Paiement");
    }

    private static Imprimable imprimable(Object o) {
        return assertInstanceOf(Imprimable.class, o,
                o.getClass().getSimpleName() + " doit être Imprimable");
    }

    @Test
    @DisplayName("CarteCredit paie un montant valide")
    void carte() throws Exception {
        assertEquals("Carte : 55.0 $", carte(500.0).payer(55.0), "Format attendu : Carte : 55.0 $");
    }

    @Test
    @DisplayName("Interac paie et diminue le solde")
    void interac() throws Exception {
        Object o = new Interac(100.0);
        Paiement p = assertInstanceOf(Paiement.class, o, "Interac doit implémenter Paiement");
        assertEquals("Interac : 30.0 $", p.payer(30.0), "Format attendu : Interac : 30.0 $");
        assertEquals(70.0, ((Interac) o).getSolde(), DELTA, "Le solde doit passer de 100 $ à 70 $");
    }

    @Test
    @DisplayName("Un tableau accepte tous les modes de paiement")
    void polymorphisme() throws Exception {
        Paiement[] modes = {carte(500.0), interac(500.0)};
        assertEquals("Carte : 10.0 $", modes[0].payer(10.0), "Chaque objet exécute sa version");
        assertEquals("Interac : 10.0 $", modes[1].payer(10.0), "Chaque objet exécute sa version");
    }

    @Test
    @DisplayName("Un permis est Imprimable")
    void permisImprimable() {
        assertEquals("Permis B123456789012 (Lina Tremblay) : 3 pts",
                imprimable(new Permis("B123456789012", "Lina Tremblay", 3)).imprimer(),
                "Format attendu : Permis suivi de toString()");
    }

    @Test
    @DisplayName("Une transaction est Imprimable")
    void transactionImprimable() {
        assertEquals("Reçu I-1 | Immatriculation ABC 123 | 120.0 $",
                imprimable(new Immatriculation("I-1", "ABC 123")).imprimer(),
                "Format attendu : Reçu suivi de recu()");
    }

    @Test
    @DisplayName("imprimerTout traite des classes sans lien de parenté")
    void imprimerTout() {
        Imprimable[] documents = {
            imprimable(new Permis("B123456789012", "Lina Tremblay", 3)),
            null,
            imprimable(new Immatriculation("I-1", "ABC 123"))
        };
        assertEquals("Permis B123456789012 (Lina Tremblay) : 3 pts\nReçu I-1 | Immatriculation ABC 123 | 120.0 $\n",
                Guichet.imprimerTout(documents), "Une ligne par document, et les cases null sont ignorées");
    }
}

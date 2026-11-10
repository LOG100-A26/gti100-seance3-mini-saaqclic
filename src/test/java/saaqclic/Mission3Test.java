package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 3 : exceptions")
class Mission3Test {

    private static final double DELTA = 0.001;

    private static Paiement carte(double plafond) {
        return (Paiement) (Object) new CarteCredit("4242", plafond);
    }

    @Test
    @DisplayName("PaiementRefuseException est une exception vérifiée")
    void exceptionVerifiee() {
        assertTrue(Exception.class.isAssignableFrom(PaiementRefuseException.class), "Elle doit hériter de Exception");
        assertFalse(RuntimeException.class.isAssignableFrom(PaiementRefuseException.class),
                "Elle doit hériter de Exception, pas de RuntimeException");
    }

    @Test
    @DisplayName("Paiement.payer déclare l'exception")
    void contrat() throws Exception {
        Class<?>[] declarees = Paiement.class.getMethod("payer", double.class).getExceptionTypes();
        assertEquals(1, declarees.length, "Ajoutez throws PaiementRefuseException à l'interface");
        assertEquals(PaiementRefuseException.class, declarees[0], "Ajoutez throws PaiementRefuseException à l'interface");
    }

    @Test
    @DisplayName("La carte refuse au-delà du plafond")
    void plafond() throws Exception {
        PaiementRefuseException e = assertThrows(PaiementRefuseException.class, () -> carte(50.0).payer(50.01),
                "Au-delà du plafond, lancez PaiementRefuseException");
        assertEquals("Plafond dépassé", e.getMessage(), "Le message doit être Plafond dépassé");
        assertEquals("Carte : 50.0 $", carte(50.0).payer(50.0), "Au plafond exact, le paiement passe");
    }

    @Test
    @DisplayName("Interac refuse si le solde manque et garde son solde")
    void solde() {
        Object o = new Interac(20.0);
        PaiementRefuseException e = assertThrows(PaiementRefuseException.class, () -> ((Paiement) o).payer(30.0),
                "Si le solde manque, lancez PaiementRefuseException");
        assertEquals("Solde insuffisant", e.getMessage(), "Le message doit être Solde insuffisant");
        assertEquals(20.0, ((Interac) o).getSolde(), DELTA, "Un paiement refusé ne doit rien débiter");
    }

    @Test
    @DisplayName("Un montant nul ou négatif est une erreur de programmation")
    void montantInvalide() {
        assertThrows(IllegalArgumentException.class, () -> carte(50.0).payer(0.0),
                "Montant de 0 $ : lancez IllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> ((Paiement) (Object) new Interac(50.0)).payer(-1.0),
                "Montant négatif : lancez IllegalArgumentException");
    }

    @Test
    @DisplayName("encaisser retourne la confirmation d'un paiement accepté")
    void encaisserAccepte() {
        assertEquals("Carte : 120.0 $", Caisse.encaisser(new Immatriculation("I-1", "ABC 123"), carte(500.0)),
                "encaisser doit retourner le résultat de payer");
    }

    @Test
    @DisplayName("encaisser transforme un refus en message")
    void encaisserRefuse() {
        assertEquals("Refusé : Plafond dépassé", Caisse.encaisser(new Immatriculation("I-1", "ABC 123"), carte(10.0)),
                "Attrapez l'exception et retournez Refusé : suivi de son message");
    }

    @Test
    @DisplayName("finally compte chaque tentative, même une erreur")
    void tentatives() {
        int avant = Caisse.tentatives();
        Caisse.encaisser(new Immatriculation("I-1", "ABC 123"), carte(500.0));
        assertEquals(avant + 1, Caisse.tentatives(), "Un paiement accepté compte pour une tentative");
        Caisse.encaisser(new Immatriculation("I-2", "ABC 123"), carte(10.0));
        assertEquals(avant + 2, Caisse.tentatives(), "Un refus compte aussi");
        assertThrows(IllegalArgumentException.class, () -> Caisse.encaisser(new Amende("A-1", 0.0, 0), carte(10.0)),
                "IllegalArgumentException ne doit pas être attrapée par encaisser");
        assertEquals(avant + 3, Caisse.tentatives(), "Le compteur doit être dans le bloc finally");
    }
}

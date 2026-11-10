package saaqclic;

import java.util.List;

/** La caisse ne connaît que Transaction et Paiement. */
public class Caisse {

    private static int tentatives = 0;

    public static int tentatives() {
        return tentatives;
    }

    public static double total(Transaction[] transactions) {
        double total = 0.0;
        for (Transaction t : transactions) {
            if (t != null) {
                total += t.montant();
            }
        }
        return total;
    }

    // Mission 4 : cette méthode refuse une List<Renouvellement>. Élargissez son paramètre.
    public static double totalListe(List<Transaction> transactions) {
        double total = 0.0;
        for (Transaction t : transactions) {
            total += t.montant();
        }
        return total;
    }

    public static List<Transaction> triees(List<Transaction> transactions) {
        // TODO Mission 2 : retournez une nouvelle liste triée par montant. L'originale ne change pas.
        throw new UnsupportedOperationException("TODO Mission 2 : triees");
    }

    public static String encaisser(Transaction transaction, Paiement paiement) {
        // TODO Mission 3 : essayez le paiement et retournez son résultat.
        // Un refus retourne Refusé : suivi du message de l'exception.
        // Chaque tentative compte une fois, même si une autre exception traverse la méthode.
        throw new UnsupportedOperationException("TODO Mission 3 : encaisser");
    }
}

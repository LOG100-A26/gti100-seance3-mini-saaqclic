package saaqclic;

/** Mission 0 : que veut dire une transaction sans forme précise ? */
public class Transaction {

    protected String reference;

    public Transaction(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    // TODO Mission 0 : rendez la classe et les deux méthodes suivantes abstraites.
    public double montant() {
        return 0.0;
    }

    public String description() {
        return "Transaction";
    }

    public String recu() {
        return reference + " | " + description() + " | " + montant() + " $";
    }

    // TODO Mission 1 : respectez le contrat Imprimable. imprimer retourne Reçu suivi de recu().
    // TODO Mission 2 : respectez le contrat Comparable<Transaction>, en comparant les montants.
}

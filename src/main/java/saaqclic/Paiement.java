package saaqclic;

/** Le contrat de tout mode de paiement. */
public interface Paiement {

    String payer(double montant);
}

package saaqclic;

/** Mission 3 : un paiement refusé est une situation prévue, que l'appelant doit traiter. */
public class PaiementRefuseException extends RuntimeException {

    // TODO Mission 3 : le compilateur ne force personne à traiter cette exception.
    // Changez son parent pour qu'il le fasse, puis corrigez les erreurs de compilation une à une.

    public PaiementRefuseException(String message) {
        super(message);
    }
}

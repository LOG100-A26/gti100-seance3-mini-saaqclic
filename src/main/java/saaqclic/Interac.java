package saaqclic;

/** Missions 1 et 3 : paiement débité d'un solde. */
public class Interac /* TODO Mission 1 : respectez le contrat Paiement */ {

    private double solde;

    public Interac(double solde) {
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    // TODO Mission 1 : écrivez payer. Elle débite le solde et retourne par exemple : Interac : 30.0 $
    // TODO Mission 3 : montant nul ou négatif, IllegalArgumentException avec le message Montant invalide.
    // TODO Mission 3 : solde trop bas, PaiementRefuseException avec le message Solde insuffisant.
    // Un paiement refusé ne débite rien.
}

package saaqclic;

/** Missions 1 et 3 : paiement par carte, limité par un plafond. */
public class CarteCredit /* TODO Mission 1 : respectez le contrat Paiement */ {

    private String numero;
    private double plafond;

    public CarteCredit(String numero, double plafond) {
        this.numero = numero;
        this.plafond = plafond;
    }

    // TODO Mission 1 : écrivez payer. Elle retourne par exemple : Carte : 55.0 $
    // TODO Mission 3 : montant nul ou négatif, IllegalArgumentException avec le message Montant invalide.
    // TODO Mission 3 : montant au-dessus du plafond, PaiementRefuseException avec le message Plafond dépassé.
}

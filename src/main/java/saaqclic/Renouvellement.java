package saaqclic;

/** Renouvellement d'un permis. */
public class Renouvellement extends Transaction {

    private int annees;
    private boolean enLigne;

    public Renouvellement(String reference, int annees, boolean enLigne) {
        super(reference);
        this.annees = annees;
        this.enLigne = enLigne;
    }

    @Override
    public double montant() {
        return Tarifs.fraisRenouvellement(annees, enLigne);
    }

    @Override
    public String description() {
        return "Renouvellement " + annees + " an(s)";
    }
}

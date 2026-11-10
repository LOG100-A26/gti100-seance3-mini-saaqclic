package saaqclic;

/** Une nouvelle transaction, ajoutée sans toucher à Caisse. */
public class Amende extends Transaction {

    public static final double FRAIS_PAR_JOUR = 2.0;

    private double montantBase;
    private int joursDeRetard;

    public Amende(String reference, double montantBase, int joursDeRetard) {
        super(reference);
        this.montantBase = montantBase;
        this.joursDeRetard = joursDeRetard;
    }

    @Override
    public double montant() {
        return montantBase + joursDeRetard * FRAIS_PAR_JOUR;
    }

    @Override
    public String description() {
        return "Amende (" + joursDeRetard + " j de retard)";
    }
}

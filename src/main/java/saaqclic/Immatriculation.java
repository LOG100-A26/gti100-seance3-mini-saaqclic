package saaqclic;

/** Immatriculation d'un véhicule. Tarif fictif. */
public class Immatriculation extends Transaction {

    public static final double TARIF = 120.0;

    private String plaque;

    public Immatriculation(String reference, String plaque) {
        super(reference);
        this.plaque = plaque;
    }

    @Override
    public double montant() {
        return TARIF;
    }

    @Override
    public String description() {
        return "Immatriculation " + plaque;
    }
}

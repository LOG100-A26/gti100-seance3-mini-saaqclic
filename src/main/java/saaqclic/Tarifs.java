package saaqclic;

/** Mission 1 : méthodes, surcharge et Javadoc. Tarifs fictifs. */
public class Tarifs {

    public static final double TARIF_ANNUEL = 30.0;
    public static final double RABAIS_EN_LIGNE = 5.0;

    public static double fraisRenouvellement(int annees) {
        if (annees <= 0) {
            return 0.0;
        }
        return annees * TARIF_ANNUEL;
    }

    public static double fraisRenouvellement(int annees, boolean enLigne) {
        double frais = fraisRenouvellement(annees);
        if (enLigne && frais > 0) {
            frais = frais - RABAIS_EN_LIGNE;
        }
        return frais;
    }

    /**
     * Vérifie le format d'un numéro de permis : une lettre majuscule suivie de 12 chiffres.
     *
     * @param numero le numéro à vérifier, possiblement null
     * @return true si le format est respecté, false sinon
     */
    public static boolean numeroValide(String numero) {
        if (numero == null || numero.length() != 13) {
            return false;
        }
        char premier = numero.charAt(0);
        if (premier < 'A' || premier > 'Z') {
            return false;
        }
        for (int i = 1; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

package saaqclic;

/** Le guichet : opérations sur les permis et impression. */
public class Guichet {

    public static void corrigerTitulaire(Permis permis, String nouveauTitulaire) {
        permis.setTitulaire(nouveauTitulaire);
    }

    public static Permis remplacer(Permis ancien, String nouveauNumero) {
        return new Permis(nouveauNumero, ancien.getTitulaire(), ancien.getPoints());
    }

    public static int compterSuspendus(Permis[] permis) {
        int total = 0;
        for (Permis p : permis) {
            if (p != null && p.estSuspendu()) {
                total++;
            }
        }
        return total;
    }

    public static String imprimerTout(Imprimable[] documents) {
        // TODO Mission 1 : une ligne par document, avec imprimer() suivi de \n. Sautez les cases null.
        throw new UnsupportedOperationException("TODO Mission 1 : imprimerTout");
    }
}

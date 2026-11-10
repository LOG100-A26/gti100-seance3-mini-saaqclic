package saaqclic;

/** Un permis de conduire. Règles simplifiées pour l'exercice. */
public class Permis {

    private static int nombreEmis = 0;

    private String numero;
    private String titulaire;
    private int points;

    public Permis(String numero, String titulaire, int points) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.points = Math.max(0, points);
        nombreEmis++;
    }

    public Permis(String numero, String titulaire) {
        this(numero, titulaire, 0);
    }

    public static int nombreEmis() {
        return nombreEmis;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public int getPoints() {
        return points;
    }

    public void ajouterPoints(int nombre) {
        if (nombre > 0) {
            points += nombre;
        }
    }

    public boolean estSuspendu() {
        return points >= limitePoints();
    }

    /** Seuil de points d'inaptitude de ce permis. */
    public int limitePoints() {
        return 15;
    }

    @Override
    public String toString() {
        return numero + " (" + titulaire + ") : " + points + " pts";
    }
}

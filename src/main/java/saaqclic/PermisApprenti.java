package saaqclic;

/** Un permis d'apprenti est un permis dont le seuil est plus bas. */
public class PermisApprenti extends Permis {

    public PermisApprenti(String numero, String titulaire) {
        super(numero, titulaire);
    }

    @Override
    public int limitePoints() {
        return 4;
    }

    @Override
    public String toString() {
        return super.toString() + " [apprenti]";
    }
}

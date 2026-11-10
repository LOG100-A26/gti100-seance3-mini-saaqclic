package saaqclic;

import java.util.ArrayList;

/** Mission 4 : ce registre accepte tout et n'importe quoi. Il devrait contenir un seul type. */
public class Registre /* TODO Mission 4 : rendez cette classe générique */ {

    private ArrayList<Object> elements = new ArrayList<>();

    public void ajouter(Object element) {
        elements.add(element);
    }

    public Object obtenir(int position) {
        return elements.get(position);
    }

    public int taille() {
        return elements.size();
    }
}

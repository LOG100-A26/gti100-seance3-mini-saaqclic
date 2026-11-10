package saaqclic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 0 : Transaction abstraite")
class Mission0Test {

    @Test
    @DisplayName("Transaction est une classe abstraite")
    void classeAbstraite() {
        assertTrue(Modifier.isAbstract(Transaction.class.getModifiers()),
                "Déclarez la classe Transaction avec le mot-clé abstract");
    }

    @Test
    @DisplayName("montant() est abstraite")
    void montantAbstrait() throws Exception {
        assertTrue(Modifier.isAbstract(Transaction.class.getMethod("montant").getModifiers()),
                "montant() doit être abstraite : retirez son corps");
    }

    @Test
    @DisplayName("description() est abstraite")
    void descriptionAbstraite() throws Exception {
        assertTrue(Modifier.isAbstract(Transaction.class.getMethod("description").getModifiers()),
                "description() doit être abstraite : retirez son corps");
    }

    @Test
    @DisplayName("recu() reste concrète et fonctionne dans les enfants")
    void recuConcret() throws Exception {
        assertTrue(!Modifier.isAbstract(Transaction.class.getMethod("recu").getModifiers()),
                "recu() doit rester concrète");
        assertTrue(new Immatriculation("I-1", "ABC 123").recu().startsWith("I-1 | "),
                "recu() doit toujours fonctionner dans les enfants");
    }
}

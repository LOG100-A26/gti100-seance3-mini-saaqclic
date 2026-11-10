package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Boss : charger un fichier")
class BossTest {

    @Test
    @SuppressWarnings("rawtypes")
    @DisplayName("Charge les lignes valides et ignore les autres")
    void fichierExemple() throws Exception {
        Registre registre = ChargeurPermis.charger("donnees/permis.csv");
        assertEquals(2, registre.taille(), "Le fichier contient 2 lignes valides sur 4");
        assertEquals("Lina Tremblay", ((Permis) registre.obtenir(0)).getTitulaire(), "Première ligne valide");
        assertEquals(16, ((Permis) registre.obtenir(1)).getPoints(), "Deuxième ligne valide");
    }

    @Test
    @SuppressWarnings("rawtypes")
    @DisplayName("Un fichier vide donne un registre vide")
    void fichierVide() throws Exception {
        Path vide = Files.createTempFile("permis", ".csv");
        Registre registre = ChargeurPermis.charger(vide.toString());
        assertEquals(0, registre.taille(), "Un fichier vide ne contient aucun permis");
    }

    @Test
    @DisplayName("Un fichier absent propage IOException")
    void fichierAbsent() {
        assertThrows(IOException.class, () -> ChargeurPermis.charger("donnees/absent.csv"),
                "Ne traitez pas le fichier absent : laissez IOException remonter");
    }

    @Test
    @DisplayName("Le fichier est ouvert avec try-with-resources")
    void tryWithResources() throws Exception {
        String source = Files.readString(Path.of("src/main/java/saaqclic/ChargeurPermis.java"));
        assertTrue(source.replace(" ", "").contains("try(BufferedReader"), "Ouvrez le lecteur dans try ( ... )");
    }
}

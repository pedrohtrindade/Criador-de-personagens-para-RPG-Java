package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RaceDAOTest {

    @Test
    public void testListarRacas() {
        List<Race> racas = RaceDAO.listarRacas();
        assertNotNull(racas, "A lista de raças não deve ser nula");
        assertEquals(true, racas.size() > 0, "Deve existir pelo menos uma raça cadastrada");
    }
}
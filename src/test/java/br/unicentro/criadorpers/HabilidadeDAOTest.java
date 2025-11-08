package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class HabilidadeDAOTest {

    @Test
    public void testListarHabilidades() {
        List<Habilidade> habilidades = HabilidadeDAO.listarHabilidades();
        assertNotNull(habilidades, "A lista de habilidades não deve ser nula");
        assertEquals(true, habilidades.size() > 0, "Deve existir pelo menos uma habilidade cadastrada");
    }
}

package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ClassDAOTest {

    @Test
    public void testListarClasses() {
        List<Classe> classes = ClassDAO.listarClasses();
        assertNotNull(classes, "A lista de classes não deve ser nula");
        assertEquals(true, classes.size() > 0, "Deve existir pelo menos uma classe cadastrada");
    }
}

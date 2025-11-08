package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EquipamentoDAOTest {



    @Test

    public void testListarEquipamentos() {
        List<Equipamento> equipamentos = EquipamentoDAO.listarEquipamentos();

        assertNotNull(equipamentos, "A lista de equipamentos não deve ser nula");

        assertEquals(true, equipamentos.size() > 0, "Deve existir pelo menos um equipamento cadastrado");

    }

}
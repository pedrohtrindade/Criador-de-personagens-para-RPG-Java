package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HabilidadeTest {

    @Test
    public void testCriacaoDeHabilidade() {
        Habilidade habilidade = new Habilidade(1, "Força", "Aumenta o poder físico do personagem");

        assertNotNull(habilidade, "O objeto Habilidade não deve ser nulo");
        assertEquals(1, habilidade.getId(), "O ID deve ser igual a 1");
        assertEquals("Força", habilidade.getNome(), "O nome deve ser 'Força'");
        assertEquals("Aumenta o poder físico do personagem", habilidade.getDescricao(), "A descrição deve corresponder ao valor informado");
        assertEquals("Força", habilidade.toString(), "O método toString deve retornar o nome da habilidade");
    }
}

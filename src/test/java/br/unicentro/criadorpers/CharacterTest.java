package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    @Test
    public void testCriacaoPersonagem() {
        Race raca = new Race(1, "Humano");
        Classe classe = new Classe(1, "Guerreiro");

        Character personagem = new Character(1, "Arthas", 5, 15, 12, 14, 8, 10, 11,
                raca, classe, List.of(), List.of());

        assertEquals("Arthas", personagem.getNome(), "O nome do personagem deve ser 'Arthas'");
        assertEquals(15, personagem.getForca(), "A força do personagem deve ser 15");
        assertEquals("Humano", personagem.getRaca().getNome(), "A raça do personagem deve ser 'Humano'");
        assertEquals("Guerreiro", personagem.getClasse().getNome(), "A classe do personagem deve ser 'Guerreiro'");
    }
}
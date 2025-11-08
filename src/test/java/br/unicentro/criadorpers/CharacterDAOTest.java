package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterDAOTest {

    @Test
    public void testInserirPersonagem() {
        int idRaca = RaceDAO.listarRacas().get(0).getId();
        int idClasse = ClassDAO.listarClasses().get(0).getId();

        int novoId = CharacterDAO.inserirPersonagem(
                "TesteUnitario",
                1, 10, 10, 10, 10, 10, 10,
                idRaca, idClasse
        );

        assertEquals(true, novoId > 0, "O ID retornado do personagem deve ser maior que zero");
    }
}
package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {



    @Test

    public void testCriacaoRaca() {

        Race raca = new Race(1, "Elfo");

        assertEquals(1, raca.getId(), "O ID da raça deve ser 1");

        assertEquals("Elfo", raca.getNome(), "O nome da raça deve ser 'Elfo'");

        assertEquals("Elfo", raca.toString(), "O método toString deve retornar o nome da raça");

    }

}
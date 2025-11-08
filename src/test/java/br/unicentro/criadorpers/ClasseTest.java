package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClasseTest {



    @Test

    public void testCriacaoClasse() {

        Classe classe = new Classe(2, "Mago");



        assertEquals(2, classe.getId(), "O ID da classe deve ser 2");

        assertEquals("Mago", classe.getNome(), "O nome da classe deve ser 'Mago'");

        assertEquals("Mago", classe.toString(), "O método toString deve retornar o nome da classe");

    }

}
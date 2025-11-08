package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipamentoTest {



    @Test

    public void testCriacaoEquipamento() {

        Equipamento espada = new Equipamento(1, "Espada Longa", "Arma", "Espada afiada de duas mãos", 5, 2);



        assertEquals(1, espada.getId(), "O ID do equipamento deve ser 1");

        assertEquals("Espada Longa", espada.getNome(), "O nome do equipamento deve ser 'Espada Longa'");

        assertEquals("Arma", espada.getTipo(), "O tipo do equipamento deve ser 'Arma'");

        assertEquals("Espada afiada de duas mãos", espada.getDescricao(), "A descrição deve corresponder");

        assertEquals(5, espada.getBonusAtaque(), "O bônus de ataque deve ser 5");

        assertEquals(2, espada.getBonusDefesa(), "O bônus de defesa deve ser 2");

        assertEquals("Espada Longa", espada.toString(), "O método toString deve retornar o nome do equipamento");

    }

}
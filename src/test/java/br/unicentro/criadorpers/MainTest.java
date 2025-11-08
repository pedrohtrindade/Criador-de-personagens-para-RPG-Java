package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Application;

public class MainTest {

    @Test
    public void testMainMetodo() {
        assertDoesNotThrow(() -> Main.main(new String[]{}), "O método main não deve lançar exceções ao iniciar a aplicação");
    }

    @Test
    public void testHerancaDeApplication() {
        assertTrue(Application.class.isAssignableFrom(Main.class), "A classe Main deve herdar de javafx.application.Application");
    }
}

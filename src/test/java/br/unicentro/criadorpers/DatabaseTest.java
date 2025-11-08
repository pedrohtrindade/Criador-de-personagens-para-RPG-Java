package br.unicentro.criadorpers;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void testConexaoComBanco() {
        Connection conexao = Database.getConnection();
        assertNotNull(conexao, "A conexão com o banco deve ser estabelecida com sucesso");
    }
}
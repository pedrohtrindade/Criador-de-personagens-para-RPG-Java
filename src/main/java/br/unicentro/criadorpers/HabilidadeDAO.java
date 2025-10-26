package br.unicentro.criadorpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabilidadeDAO {
    public static List<Habilidade> listarHabilidades() {
        List<Habilidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM habilidades";
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Habilidade(
                        rs.getInt("id_habilidade"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                ));
            }

        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}

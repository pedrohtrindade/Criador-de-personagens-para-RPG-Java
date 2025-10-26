package br.unicentro.criadorpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    public static List<Equipamento> listarEquipamentos() {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos";
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Equipamento(
                        rs.getInt("id_equipamento"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getString("descricao"),
                        rs.getInt("bonus_ataque"),
                        rs.getInt("bonus_defesa")
                ));
            }

        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}

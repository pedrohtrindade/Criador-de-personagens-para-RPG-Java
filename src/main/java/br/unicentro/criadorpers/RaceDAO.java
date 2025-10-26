package br.unicentro.criadorpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaceDAO {
    public static List<Race> listarRacas() {
        List<Race> lista = new ArrayList<>();
        String sql = "SELECT * FROM racas";
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Race(rs.getInt("id_raca"), rs.getString("nome")));
            }

        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}

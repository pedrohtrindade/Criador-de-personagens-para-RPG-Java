package br.unicentro.criadorpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    public static List<Classe> listarClasses() {
        List<Classe> lista = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Connection con = Database.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Classe(rs.getInt("id_classe"), rs.getString("nome")));
            }

        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}

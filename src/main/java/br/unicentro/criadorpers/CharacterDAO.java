package br.unicentro.criadorpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/rpg_creator",
                "postgres",
                "postgres"
        );
    }

    // Inserir personagem e retornar o ID gerado
    public static int inserirPersonagem(String nome, int nivel, int forca, int destreza, int constituicao,
                                        int inteligencia, int sabedoria, int carisma, int idRaca, int idClasse) {
        String sql = "INSERT INTO personagens(nome, nivel, forca, destreza, constituicao, inteligencia, sabedoria, carisma, id_raca, id_classe) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_personagem";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setInt(2, nivel);
            ps.setInt(3, forca);
            ps.setInt(4, destreza);
            ps.setInt(5, constituicao);
            ps.setInt(6, inteligencia);
            ps.setInt(7, sabedoria);
            ps.setInt(8, carisma);
            ps.setInt(9, idRaca);
            ps.setInt(10, idClasse);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_personagem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Listar todos os personagens
    public static List<Character> listarPersonagens() {
        List<Character> lista = new ArrayList<>();
        String sql = "SELECT p.*, r.nome AS nome_raca, c.nome AS nome_classe " +
                "FROM personagens p " +
                "LEFT JOIN racas r ON p.id_raca = r.id_raca " +
                "LEFT JOIN classes c ON p.id_classe = c.id_classe";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Race r = new Race(rs.getInt("id_raca"), rs.getString("nome_raca"));
                Classe c = new Classe(rs.getInt("id_classe"), rs.getString("nome_classe"));

                Character p = new Character(
                        rs.getInt("id_personagem"),
                        rs.getString("nome"),
                        rs.getInt("nivel"),
                        rs.getInt("forca"),
                        rs.getInt("destreza"),
                        rs.getInt("constituicao"),
                        rs.getInt("inteligencia"),
                        rs.getInt("sabedoria"),
                        rs.getInt("carisma"),
                        r,
                        c,
                        new ArrayList<>(), // habilidades
                        new ArrayList<>()  // equipamentos
                );

                // Carregar equipamentos e habilidades do personagem
                p.getEquipamentos().addAll(listarEquipamentosPersonagem(p.getId()));
                p.getHabilidades().addAll(listarHabilidadesPersonagem(p.getId()));

                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // --------------------- EQUIPAMENTOS ---------------------

    public static void adicionarEquipamento(int idPersonagem, int idEquipamento) {
        String sql = "INSERT INTO personagem_equipamento(id_personagem, id_equipamento) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ps.setInt(2, idEquipamento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerEquipamento(int idPersonagem, int idEquipamento) {
        String sql = "DELETE FROM personagem_equipamento WHERE id_personagem = ? AND id_equipamento = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ps.setInt(2, idEquipamento);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Equipamento> listarEquipamentosPersonagem(int idPersonagem) {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT e.* FROM equipamentos e " +
                "JOIN personagem_equipamento pe ON e.id_equipamento = pe.id_equipamento " +
                "WHERE pe.id_personagem = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Equipamento eq = new Equipamento(
                        rs.getInt("id_equipamento"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getString("descricao"),
                        rs.getInt("bonus_ataque"),
                        rs.getInt("bonus_defesa")
                );
                lista.add(eq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // --------------------- HABILIDADES ---------------------

    public static void adicionarHabilidade(int idPersonagem, int idHabilidade) {
        String sql = "INSERT INTO personagem_habilidade(id_personagem, id_habilidade) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ps.setInt(2, idHabilidade);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerHabilidade(int idPersonagem, int idHabilidade) {
        String sql = "DELETE FROM personagem_habilidade WHERE id_personagem = ? AND id_habilidade = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ps.setInt(2, idHabilidade);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Habilidade> listarHabilidadesPersonagem(int idPersonagem) {
        List<Habilidade> lista = new ArrayList<>();
        String sql = "SELECT h.* FROM habilidades h " +
                "JOIN personagem_habilidade ph ON h.id_habilidade = ph.id_habilidade " +
                "WHERE ph.id_personagem = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Habilidade h = new Habilidade(
                        rs.getInt("id_habilidade"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                );
                lista.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void deletarPersonagem(int idPersonagem) {
        String sql = "DELETE FROM personagens WHERE id_personagem = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPersonagem);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

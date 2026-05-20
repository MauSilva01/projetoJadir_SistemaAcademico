package sistemaacademico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sistemaacademico.connection.ConnectionBD;
import sistemaacademico.model.NotaFalta;

public class NotaFaltaDAO {

	public void salvar(NotaFalta notaFalta) {

		String sql = "INSERT INTO nota_falta "
				+ "(rgm, disciplina, semestre, a1, a2, af, media, faltas, situacao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, notaFalta.getRgm());
			stmt.setString(2, notaFalta.getDisciplina());
			stmt.setString(3, notaFalta.getSemestre());
			stmt.setDouble(4, notaFalta.getA1());
			stmt.setDouble(5, notaFalta.getA2());
			stmt.setDouble(6, notaFalta.getAf());
			stmt.setDouble(7, notaFalta.getMedia());
			stmt.setInt(8, notaFalta.getFaltas());
			stmt.setString(9, notaFalta.getSituacao());

			stmt.execute();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao salvar nota/falta: " + e.getMessage());

		}
	}

	public List<NotaFalta> consultarPorRgm(String rgm) {

		String sql = "SELECT * FROM nota_falta WHERE rgm = ?";

		List<NotaFalta> lista = new ArrayList<NotaFalta>();

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				NotaFalta nf = new NotaFalta();

				nf.setIdNotaFalta(rs.getInt("id_nota_falta"));
				nf.setRgm(rs.getString("rgm"));
				nf.setDisciplina(rs.getString("disciplina"));
				nf.setSemestre(rs.getString("semestre"));
				nf.setA1(rs.getDouble("a1"));
				nf.setA2(rs.getDouble("a2"));
				nf.setAf(rs.getDouble("af"));
				nf.setMedia(rs.getDouble("media"));
				nf.setFaltas(rs.getInt("faltas"));
				nf.setSituacao(rs.getString("situacao"));

				lista.add(nf);
			}

			rs.close();
			stmt.close();
			conn.close();

			return lista;

		} catch (Exception e) {

			throw new RuntimeException("Erro ao consultar notas/faltas: " + e.getMessage());

		}
	}

	public void alterar(NotaFalta notaFalta) {

		String sql = "UPDATE nota_falta SET "
				+ "disciplina = ?, "
				+ "semestre = ?, "
				+ "a1 = ?, "
				+ "a2 = ?, "
				+ "af = ?, "
				+ "media = ?, "
				+ "faltas = ?, "
				+ "situacao = ? "
				+ "WHERE id_nota_falta = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, notaFalta.getDisciplina());
			stmt.setString(2, notaFalta.getSemestre());
			stmt.setDouble(3, notaFalta.getA1());
			stmt.setDouble(4, notaFalta.getA2());
			stmt.setDouble(5, notaFalta.getAf());
			stmt.setDouble(6, notaFalta.getMedia());
			stmt.setInt(7, notaFalta.getFaltas());
			stmt.setString(8, notaFalta.getSituacao());
			stmt.setInt(9, notaFalta.getIdNotaFalta());

			stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao alterar nota/falta: " + e.getMessage());

		}
	}

	public void excluir(int idNotaFalta) {

		String sql = "DELETE FROM nota_falta WHERE id_nota_falta = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idNotaFalta);

			stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao excluir nota/falta: " + e.getMessage());

		}
	}

	public boolean existeNota(String rgm, String disciplina, String semestre) {

		String sql = "SELECT id_nota_falta FROM nota_falta "
				+ "WHERE rgm = ? AND disciplina = ? AND semestre = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);
			stmt.setString(2, disciplina);
			stmt.setString(3, semestre);

			ResultSet rs = stmt.executeQuery();

			boolean existe = rs.next();

			rs.close();
			stmt.close();
			conn.close();

			return existe;

		} catch (Exception e) {

			throw new RuntimeException("Erro ao verificar nota/falta: " + e.getMessage());

		}
	}

	public void excluirPorRgmDisciplinaSemestre(String rgm, String disciplina, String semestre) {

		String sql = "DELETE FROM nota_falta "
				+ "WHERE rgm = ? AND disciplina = ? AND semestre = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);
			stmt.setString(2, disciplina);
			stmt.setString(3, semestre);

			int linhasAfetadas = stmt.executeUpdate();

			stmt.close();
			conn.close();

			if (linhasAfetadas == 0) {
				throw new RuntimeException("Nenhuma nota/falta encontrada para exclusão.");
			}

		} catch (Exception e) {

			throw new RuntimeException("Erro ao excluir nota/falta: " + e.getMessage());

		}
	}
}
package sistemaacademico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sistemaacademico.connection.ConnectionBD;
import sistemaacademico.model.Curso;

public class CursoDAO {

	public void salvar(Curso curso) {

		String sql = "INSERT INTO curso (rgm, curso, campus, periodo) VALUES (?, ?, ?, ?)";

		try {
			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, curso.getRgm());
			stmt.setString(2, curso.getCurso());
			stmt.setString(3, curso.getCampus());
			stmt.setString(4, curso.getPeriodo());

			stmt.execute();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar curso: " + e.getMessage());
		}
	}

	public Curso consultarPorRgm(String rgm) {

		String sql = "SELECT * FROM curso WHERE rgm = ?";

		try {
			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Curso curso = new Curso();

				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setRgm(rs.getString("rgm"));
				curso.setCurso(rs.getString("curso"));
				curso.setCampus(rs.getString("campus"));
				curso.setPeriodo(rs.getString("periodo"));

				rs.close();
				stmt.close();
				conn.close();

				return curso;
			}

			rs.close();
			stmt.close();
			conn.close();

			return null;

		} catch (Exception e) {
			throw new RuntimeException("Erro ao consultar curso: " + e.getMessage());
		}
	}

	public void alterar(Curso curso) {

		String sql = "UPDATE curso SET curso = ?, campus = ?, periodo = ? WHERE rgm = ?";

		try {
			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, curso.getCurso());
			stmt.setString(2, curso.getCampus());
			stmt.setString(3, curso.getPeriodo());
			stmt.setString(4, curso.getRgm());

			stmt.executeUpdate();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao alterar curso: " + e.getMessage());
		}
	}

	public void excluir(String rgm) {

		String sql = "DELETE FROM curso WHERE rgm = ?";

		try {
			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);
			stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir curso: " + e.getMessage());
		}
	}
}
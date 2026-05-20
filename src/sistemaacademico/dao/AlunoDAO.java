package sistemaacademico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sistemaacademico.connection.ConnectionBD;
import sistemaacademico.model.Aluno;

public class AlunoDAO {

	public void salvar(Aluno aluno) {

		String sql = "INSERT INTO aluno "
				+ "(rgm, nome, data_nascimento, cpf, email, endereco, municipio, uf, celular) "
				+ "VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?, ?, ?, ?)";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, aluno.getRgm());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getDataNascimento());
			stmt.setString(4, aluno.getCpf());
			stmt.setString(5, aluno.getEmail());
			stmt.setString(6, aluno.getEndereco());
			stmt.setString(7, aluno.getMunicipio());
			stmt.setString(8, aluno.getUf());
			stmt.setString(9, aluno.getCelular());

			stmt.execute();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage());

		}
	}

	public Aluno consultarPorRgm(String rgm) {

		String sql = "SELECT rgm, nome, DATE_FORMAT(data_nascimento, '%d/%m/%Y') AS data_nascimento, "
				+ "cpf, email, endereco, municipio, uf, celular FROM aluno WHERE rgm = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				Aluno aluno = new Aluno();

				aluno.setRgm(rs.getString("rgm"));
				aluno.setNome(rs.getString("nome"));
				aluno.setDataNascimento(rs.getString("data_nascimento"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setEmail(rs.getString("email"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setMunicipio(rs.getString("municipio"));
				aluno.setUf(rs.getString("uf"));
				aluno.setCelular(rs.getString("celular"));

				rs.close();
				stmt.close();
				conn.close();

				return aluno;
			}

			rs.close();
			stmt.close();
			conn.close();

			return null;

		} catch (Exception e) {

			throw new RuntimeException("Erro ao consultar aluno: " + e.getMessage());

		}
	}

	public void alterar(Aluno aluno) {

		String sql = "UPDATE aluno SET "
				+ "nome = ?, "
				+ "data_nascimento = STR_TO_DATE(?, '%d/%m/%Y'), "
				+ "cpf = ?, "
				+ "email = ?, "
				+ "endereco = ?, "
				+ "municipio = ?, "
				+ "uf = ?, "
				+ "celular = ? "
				+ "WHERE rgm = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getDataNascimento());
			stmt.setString(3, aluno.getCpf());
			stmt.setString(4, aluno.getEmail());
			stmt.setString(5, aluno.getEndereco());
			stmt.setString(6, aluno.getMunicipio());
			stmt.setString(7, aluno.getUf());
			stmt.setString(8, aluno.getCelular());
			stmt.setString(9, aluno.getRgm());

			stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao alterar aluno: " + e.getMessage());

		}
	}

	public void excluir(String rgm) {

		String sql = "DELETE FROM aluno WHERE rgm = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);

			stmt.executeUpdate();

			stmt.close();
			conn.close();

		} catch (Exception e) {

			throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());

		}
	}

	public boolean rgmExiste(String rgm) {

		String sql = "SELECT rgm FROM aluno WHERE rgm = ?";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, rgm);

			ResultSet rs = stmt.executeQuery();

			boolean existe = rs.next();

			rs.close();
			stmt.close();
			conn.close();

			return existe;

		} catch (Exception e) {

			throw new RuntimeException("Erro ao verificar RGM: " + e.getMessage());

		}
	}

	public String gerarNovoRgm() {

		String sql = "SELECT MAX(rgm) AS ultimo_rgm FROM aluno WHERE rgm LIKE 'SA2026%'";

		try {

			Connection conn = ConnectionBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				String ultimoRgm = rs.getString("ultimo_rgm");

				if (ultimoRgm == null) {

					rs.close();
					stmt.close();
					conn.close();

					return "SA20260001";
				}

				String numeroTexto = ultimoRgm.substring(6);

				int numero = Integer.parseInt(numeroTexto);

				numero++;

				rs.close();
				stmt.close();
				conn.close();

				return "SA2026" + String.format("%04d", numero);
			}

			rs.close();
			stmt.close();
			conn.close();

			return "SA20260001";

		} catch (Exception e) {

			throw new RuntimeException("Erro ao gerar RGM: " + e.getMessage());

		}
	}
}
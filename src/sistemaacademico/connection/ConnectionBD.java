package sistemaacademico.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBD {

	private static final String URL =
			"jdbc:mysql://localhost:3306/sistema_academico?useSSL=false&serverTimezone=UTC";

	private static final String USER = "root";

	private static final String PASSWORD = "";

	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(
					URL,
					USER,
					PASSWORD
			);

		} catch (Exception e) {

			throw new RuntimeException(
					"Erro na conexão com banco: "
							+ e.getMessage()
			);
		}
	}
}
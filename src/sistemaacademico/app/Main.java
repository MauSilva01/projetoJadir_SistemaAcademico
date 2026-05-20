package sistemaacademico.app;

import java.sql.Connection;

import sistemaacademico.connection.ConnectionBD;

public class Main {

    public static void main(String[] args) {

        Connection conexao = ConnectionBD.getConnection();

        if (conexao != null) {

            System.out.println("Conexão realizada com sucesso!");

        }

    }

}
package bancodados.cadastroaluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Aluno {
	private String matricula;
	private String url = "jdbc:derby:banco-de-teste;create=true";
	private Connection conn;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String fone;

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void incluir() {
		try {
			// Obtém a conexão.
			conn = DriverManager.getConnection(url);
			// Cria a sentença SQL.
			String sql = "insert into aluno (matricula, nome, fone, cpf) values (?, ?, ?, ?)";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, matricula);
			prepareStatement.setString(2, nome);
			prepareStatement.setString(3, fone);
			prepareStatement.setString(4, cpf);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			// Fecha a sentença.
			prepareStatement.close();
			// Fecha a conexão.
			conn.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public void alterar(String matricula2, String nome2, String fone2,
			String cpf2) {
		try {
			// Obtém a conexão.
			Connection conn = DriverManager.getConnection(url);
			// Cria a sentença SQL.
			String sql = "update aluno set nome=?, fone=?, cpf=? where matricula=?";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, nome);
			prepareStatement.setString(2, fone);
			prepareStatement.setString(3, cpf);
			prepareStatement.setString(4, matricula);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			// Fecha a sentença.
			prepareStatement.close();
			// Fecha a conexão.
			conn.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public void remover(String matricula2) {
		try {
			conn = DriverManager.getConnection(url);
			String sql = "delete from aluno where matricula=?";
			// Obtém referência para uma sentença SQL.
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, matricula2);
			// Executa a instrução SQL.
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}
	}

	public void pesquisar(String matricula2) {
		try {
			conn = DriverManager.getConnection(url);
			String sql;
			PreparedStatement prepareStatement;
			if (matricula2.equals("")) {
				sql = "select * from aluno order by matricula";
				prepareStatement = conn.prepareStatement(sql);
			} else {
				sql = "select * from aluno where matricula=? order by matricula";
				prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setString(1, matricula2);
			}
			// Obtém referência para uma sentença SQL.
			
			// Executa a instrução SQL.
			ResultSet rs = prepareStatement.executeQuery();
			setMatricula("<br>");
			while (rs.next()) {
				matricula += rs.getString(1) + " - " + rs.getString(2) + " - "
						+ rs.getString(3) + " - " + rs.getString(4) + "<br>";
			}
			rs.close();
			prepareStatement.close();
		} catch (Throwable e) {
			// Para repassar a exceção para o container tratar.
			throw new RuntimeException(e);
		}

	}
}
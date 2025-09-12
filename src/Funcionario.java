import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private String funcao;

    public Funcionario() {}

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "salario=" + salario +
                ", funcao='" + funcao + '\'' +
                '}';
    }

    public static void inserirFuncionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, data_nascimento, salario, funcao) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setDate(2, java.sql.Date.valueOf(dataNascimento));
            pstmt.setBigDecimal(3, salario);
            pstmt.setString(4, funcao);
            pstmt.executeUpdate();
        }
    }


    public static void alterarSalarioFuncionarioEspecifico(String nome, BigDecimal salario) throws SQLException {
        String sql = "UPDATE funcionarios SET salario = ? WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, salario);
            pstmt.setString(2, nome);
            pstmt.executeUpdate();
        }
    }

    public static void alterarSalarioTodosFuncionarios(BigDecimal percentual) throws SQLException {
        String sql = "UPDATE funcionarios SET salario = salario * (1 + ? / 100)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBigDecimal(1, percentual);
            pstmt.executeUpdate();
        }
    }

    public static void removerFuncionario(String nome) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE nome = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
        }
    }

    public static List<Funcionario> listarFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT nome, data_nascimento, salario, funcao FROM funcionarios";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario");
                String funcao = rs.getString("funcao");
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
        }
        return funcionarios;
    }

    public static List<Funcionario> imprimirFuncionariosPorFuncao() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT nome, data_nascimento, salario, funcao FROM funcionarios ORDER BY funcao";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario");
                String funcao = rs.getString("funcao");
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
        }
        return funcionarios;
    }

    public static List<Funcionario> imprimirFuncionariosAniversarioOutubroDezembro() throws SQLException{
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT nome, data_nascimento, salario, funcao FROM funcionarios WHERE EXTRACT(MONTH FROM data_nascimento) IN (10,12)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario");
                String funcao = rs.getString("funcao");
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
        }
        return funcionarios;
    }


    public static List<Funcionario> imprimirFuncionariosOrdemAlfabetica() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT nome, data_nascimento, salario, funcao FROM funcionarios ORDER BY nome ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario");
                String funcao = rs.getString("funcao");
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
        }
        return funcionarios;
    }

    public static BigDecimal imprimirTotalSalarioFuncionarios() throws SQLException {
        String sql = "SELECT SUM(salario) AS total_salarios FROM funcionarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal("total_salarios");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO; // retorna 0 se não houver resultado ou erro
    }

    public static List<Funcionario> imprimirTotalSalarioMinimosFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT nome, data_nascimento, funcao, salario / 1212 AS salario FROM funcionarios";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                BigDecimal salario = rs.getBigDecimal("salario").setScale(2, RoundingMode.HALF_UP);
                String funcao = rs.getString("funcao");
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
        }
        return funcionarios;
    }
}

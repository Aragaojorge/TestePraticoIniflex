import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Inserir Funcionário");
            System.out.println("2. Remover Funcionário");
            System.out.println("3. Imprimir Todos Funcionários");
            System.out.println("4. Alterar Salários");
            System.out.println("5. Imprimir Funcionários por Função");
            System.out.println("6. Imprimir Funcionários - Aniversário Meses 10 e 12");
            System.out.println("7. Imprimir Funcionário com Maior Idade");
            System.out.println("8. Imprimir Funcionários - Ordem Alfabética");
            System.out.println("9. Imprimir Funcionários - Total Salários");
            System.out.println("10. Imprimir Funcionários - Salários Mínimos");
            System.out.println("0. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (option) {
                case 1: // Inserir Novo Funcionário
                    inserirFuncionario(scanner);
                    System.out.println();
                    break;

                case 2: // Remover Funcionários
                    removerFuncionario(scanner);
                    System.out.println();
                    break;

                case 3: // Imprimir Lista Completa de Funcionários
                    listarTodosFuncionarios(scanner);
                    System.out.println();
                    break;

                case 4: // Alterar Salários
                    alterarSalarios(scanner);
                    System.out.println();
                    break;

                case 5: // Imprimir Funcionários por Função
                    imprimirFuncionariosPorFuncao();
                    System.out.println();
                    break;

                case 6: // Imprimir Funcionários que Fazem Aniversários nos Meses 10 e 12
                    imprimirFuncionariosAniversarioOutubroDezembro();
                    System.out.println();
                    break;

                case 7: // Imprimir Funcionário com Maior Idade
                    System.out.println("Em desenvolvimento!");
                    System.out.println();
                    break;

                case 8: // Imprimir Funcionários por Ordem Alfabética
                    imprimirFuncionariosOrdemAlfabetica();
                    System.out.println();
                    break;

                case 9: // Imprimir Total dos Salários dos Funcionários
                    imprimirTotalSalarioFuncionarios();
                    System.out.println();
                    break;

                case 10: // Imprimir Total dos Salários Mínimos por Funcionário
                    imprimirTotalSalariosMinimoFuncionarios();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    System.out.println();
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);

        scanner.close();
    }

    private static void inserirFuncionario(Scanner scanner) {
        int option;
        do {
            System.out.println("- Gestão de Funcionários -");
            System.out.println("1. Criar Funcionário");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print(("Digite o Nome do Funcionário: "));
                    String nome = scanner.nextLine();
                    System.out.print("Digite a Data de Nascimento (dd/MM/aaaa): ");
                    String input = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataNascimento = LocalDate.parse(input, formatter);
                    System.out.print("Digite o Salário do Funcionário: ");
                    String inputSalario = scanner.nextLine();
                    inputSalario = inputSalario.replace(",", ".");
                    BigDecimal salario = new BigDecimal(inputSalario);
                    System.out.print("Digite a Função do Funcionário: ");
                    String funcao = scanner.nextLine();
                    try {
                        Funcionario.inserirFuncionario(nome, dataNascimento, salario, funcao);
                        System.out.print("Funcionário criado com Sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao tentar criar novo funcionário!");
                        System.out.print("Erro: " + e);
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Novamente");
            }
        } while (option != 0);
    }

    private static void removerFuncionario(Scanner scanner) {
        int option;
        do {
            System.out.println("- Gestão de Funcionários -");
            System.out.println("1. Remover Funcionário");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print(("Digite o Nome do Funcionário: "));
                    String nome = scanner.nextLine();
                    try {
                        Funcionario.removerFuncionario(nome);
                        System.out.println("Funcionário Removido com Sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao tentar remover funcionário!");
                        System.out.print("Erro: " + e);
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Novamente");
            }
        } while (option != 0);
    }

    private static void alterarSalarios(Scanner scanner) {
        int option;
        do {
            System.out.println("- Gestão de Salários -");
            System.out.println("1. Alterar Salário de Funcionário Específico");
            System.out.println("2. Alterar Todos os Salários");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println();
            System.out.print("Escolha uma Opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Digite o Nome do Funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o Novo Salário: ");
                    BigDecimal salario = scanner.nextBigDecimal();
                    try {
                        Funcionario.alterarSalarioFuncionarioEspecifico(nome, salario);
                        System.out.println("Salário alterado com Sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao tentar alterar salário!");
                        System.out.print("Erro: " + e);
                    }
                    break;
                case 2:
                    System.out.print(("Digite o Percentual de Aumento Para os Salários (ex: X.XX%): "));
                    BigDecimal percentual = scanner.nextBigDecimal();
                    try {
                        Funcionario.alterarSalarioTodosFuncionarios(percentual);
                        System.out.println("Salários alterados com Sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao tentar alterar salários!");
                        System.out.print("Erro: " + e);
                    }
                default:
                    System.out.println("Opção Inválida. Tente Novamente");
            }
        } while (option != 0);
    }

    private static void listarTodosFuncionarios(Scanner scanner) {
        try {
            List<Funcionario> funcionarios= Funcionario.listarFuncionarios();
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                    "Nome", "Nascimento", "Salário", "Função");
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            DateTimeFormatter dataNascimentoFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            NumberFormat salarioFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            salarioFormatter.setMinimumFractionDigits(2);
            salarioFormatter.setMaximumFractionDigits(2);
            for(Funcionario f : funcionarios) {
                System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                        f.getNome(),
                        f.getDataNascimento().format(dataNascimentoFormatter),
                        salarioFormatter.format(f.getSalario()),
                        f.getFuncao());
            }
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }

    private static void imprimirFuncionariosPorFuncao() {
        try {
            List<Funcionario> funcionarios= Funcionario.imprimirFuncionariosPorFuncao();
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                    "Nome", "Nascimento", "Salário", "Função");
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            DateTimeFormatter dataNascimentoFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            NumberFormat salarioFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            salarioFormatter.setMinimumFractionDigits(2);
            salarioFormatter.setMaximumFractionDigits(2);
            for(Funcionario f : funcionarios) {
                System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                        f.getNome(),
                        f.getDataNascimento().format(dataNascimentoFormatter),
                        salarioFormatter.format(f.getSalario()),
                        f.getFuncao());
            }
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }

    private static void imprimirFuncionariosAniversarioOutubroDezembro() {
        try {
            List<Funcionario> funcionarios= Funcionario.imprimirFuncionariosAniversarioOutubroDezembro();
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                    "Nome", "Nascimento", "Salário", "Função");
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            DateTimeFormatter dataNascimentoFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            NumberFormat salarioFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            salarioFormatter.setMinimumFractionDigits(2);
            salarioFormatter.setMaximumFractionDigits(2);
            for(Funcionario f : funcionarios) {
                System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                        f.getNome(),
                        f.getDataNascimento().format(dataNascimentoFormatter),
                        salarioFormatter.format(f.getSalario()),
                        f.getFuncao());
            }
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }

    private static void imprimirFuncionariosOrdemAlfabetica() {
        try {
            List<Funcionario> funcionarios= Funcionario.imprimirFuncionariosOrdemAlfabetica();
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                    "Nome", "Nascimento", "Salário", "Função");
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            DateTimeFormatter dataNascimentoFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            NumberFormat salarioFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            salarioFormatter.setMinimumFractionDigits(2);
            salarioFormatter.setMaximumFractionDigits(2);
            for(Funcionario f : funcionarios) {
                System.out.printf("| %-18s | %-15s | %-15s | %-18s |%n",
                        f.getNome(),
                        f.getDataNascimento().format(dataNascimentoFormatter),
                        salarioFormatter.format(f.getSalario()),
                        f.getFuncao());
            }
            System.out.println("+--------------------+-----------------+-----------------+--------------------+");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }

    private static void imprimirTotalSalarioFuncionarios() {
        try {
            NumberFormat salarioFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            salarioFormatter.setMinimumFractionDigits(2);
            salarioFormatter.setMaximumFractionDigits(2);
            BigDecimal rs =Funcionario.imprimirTotalSalarioFuncionarios();
            salarioFormatter.format(rs);
            System.out.println("-----------------------------------------------------");
            System.out.println("| Total de salários dos funcionários: " + rs + " ******");
            System.out.println("-----------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }

    private static void imprimirTotalSalariosMinimoFuncionarios() {
        try {
            List<Funcionario> funcionarios = Funcionario.imprimirTotalSalarioMinimosFuncionarios();
            System.out.println("+--------------------+-----------------+");
            System.out.printf("| %-18s | %-15s |%n",
                    "Nome", "N° Sal-Min");
            System.out.println("+--------------------+-----------------+");
            for(Funcionario f : funcionarios) {
                System.out.printf("| %-18s | %-15s |%n",
                        f.getNome(),
                        f.getSalario());
            }
            System.out.println("+--------------------+-----------------+");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(("Erro ao Tentar Acessar Funcionários!"));
            System.out.println("Erro: " + e);
        }
    }
}

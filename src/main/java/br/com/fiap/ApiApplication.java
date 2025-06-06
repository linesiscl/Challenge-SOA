package br.com.fiap;

import br.com.fiap.dto.UserDTO;
import br.com.fiap.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {"br.com.fiap", "com.investbot"})
public class ApiApplication implements CommandLineRunner {

    private final UserService userService;

    public ApiApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Login Admin");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> { // Cadastro de usuário comum
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Perfil de risco (Conservador / Moderado / Agressivo): ");
                        String risco = scanner.nextLine();

                        if (nome.isBlank() || email.isBlank() || risco.isBlank()) {
                            System.out.println("Cadastro cancelado: campos obrigatórios vazios.");
                            continue;
                        }

                        UserDTO novoUsuario = new UserDTO(null, nome.trim(), email.trim(), risco.trim());
                        UserDTO criado = userService.createUser(novoUsuario);

                        System.out.println("Usuário cadastrado com sucesso:\n" + criado);
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                    }
                }
                case "2" -> { // Login admin
                    System.out.print("Login do administrador: ");
                    String login = scanner.nextLine();

                    System.out.print("Senha do administrador: ");
                    String senha = scanner.nextLine();

                    if ("admin".equals(login) && "1234".equals(senha)) {
                        System.out.println("Login bem-sucedido. Acessando painel de administração...");

                        while (true) {
                            System.out.println("\n--- Painel do Administrador ---");
                            System.out.println("1 - Listar todos os usuários");
                            System.out.println("2 - Buscar usuário por ID");
                            System.out.println("3 - Atualizar usuário");
                            System.out.println("4 - Deletar usuário");
                            System.out.println("5 - Voltar");
                            System.out.print("Escolha uma operação: ");
                            String crudOption = scanner.nextLine();

                            switch (crudOption) {
                                case "1" -> { // Listar todos
                                    List<UserDTO> users = userService.getAllUsers();
                                    if (users.isEmpty()) {
                                        System.out.println("Nenhum usuário cadastrado.");
                                    } else {
                                        System.out.println("Usuários cadastrados:");
                                        users.forEach(System.out::println);
                                    }
                                }
                                case "2" -> { // Buscar por ID
                                    System.out.print("Digite o ID do usuário: ");
                                    try {
                                        Long id = Long.parseLong(scanner.nextLine());
                                        Optional<UserDTO> userOpt = userService.getUserById(id);
                                        if (userOpt.isPresent()) {
                                            System.out.println("Usuário encontrado:\n" + userOpt.get());
                                        } else {
                                            System.out.println("Usuário não encontrado.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID inválido.");
                                    }
                                }
                                case "3" -> { // Atualizar
                                    System.out.print("Digite o ID do usuário a atualizar: ");
                                    try {
                                        Long id = Long.parseLong(scanner.nextLine());
                                        Optional<UserDTO> existingUserOpt = userService.getUserById(id);

                                        if (existingUserOpt.isPresent()) {
                                            UserDTO existingUser = existingUserOpt.get();

                                            System.out.print("Novo nome (" + existingUser.getName() + "): ");
                                            String name = scanner.nextLine();

                                            System.out.print("Novo email (" + existingUser.getEmail() + "): ");
                                            String email = scanner.nextLine();

                                            System.out.print("Novo perfil de risco (" + existingUser.getRiskProfile() + "): ");
                                            String risk = scanner.nextLine();

                                            // Se usuário deixar em branco, mantém valor antigo
                                            UserDTO updatedData = new UserDTO(
                                                    id,
                                                    name.isBlank() ? existingUser.getName() : name.trim(),
                                                    email.isBlank() ? existingUser.getEmail() : email.trim(),
                                                    risk.isBlank() ? existingUser.getRiskProfile() : risk.trim()
                                            );

                                            Optional<UserDTO> updatedUserOpt = userService.updateUser(id, updatedData);
                                            if (updatedUserOpt.isPresent()) {
                                                System.out.println("Usuário atualizado:\n" + updatedUserOpt.get());
                                            } else {
                                                System.out.println("Falha ao atualizar usuário.");
                                            }
                                        } else {
                                            System.out.println("Usuário não encontrado.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID inválido.");
                                    }
                                }
                                case "4" -> { // Deletar
                                    System.out.print("Digite o ID do usuário a deletar: ");
                                    try {
                                        Long id = Long.parseLong(scanner.nextLine());
                                        boolean deleted = userService.deleteUser(id);
                                        if (deleted) {
                                            System.out.println("Usuário deletado com sucesso.");
                                        } else {
                                            System.out.println("Usuário não encontrado.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID inválido.");
                                    }
                                }
                                case "5" -> {
                                    System.out.println("Saindo do painel administrativo.");
                                    break;
                                }
                                default -> System.out.println("Opção inválida.");
                            }
                            if ("5".equals(crudOption)) break;
                        }

                    } else {
                        System.out.println("Credenciais inválidas.");
                    }
                }
                case "3" -> {
                    System.out.println("Saindo da aplicação.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

// Classe representando uma história
class Historia {
    private String usuario;
    private String conteudo;
    private ArrayList<String> comentarios;

    public Historia(String usuario, String conteudo) {
        this.usuario = usuario;
        this.conteudo = conteudo;
        this.comentarios = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void adicionarComentario(String comentario) {
        comentarios.add(comentario);
    }

    public void exibirHistoria() {
        System.out.println("Usuário: " + usuario);
        System.out.println("História: " + conteudo);
        if (comentarios.isEmpty()) {
            System.out.println("Nenhum comentário ainda.");
        } else {
            System.out.println("Comentários:");
            for (String comentario : comentarios) {
                System.out.println("- " + comentario);
            }
        }
    }
}

// Classe principal
public class RedeSocial {
    private static ArrayList<Historia> historias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Rede Social de Histórias ===");
            System.out.println("1. Postar uma história");
            System.out.println("2. Ver histórias");
            System.out.println("3. Comentar em uma história");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    postarHistoria(scanner);
                    break;
                case 2:
                    verHistorias();
                    break;
                case 3:
                    comentarHistoria(scanner);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void postarHistoria(Scanner scanner) {
        System.out.print("Seu nome: ");
        String usuario = scanner.nextLine();
        System.out.print("Sua história: ");
        String conteudo = scanner.nextLine();
        historias.add(new Historia(usuario, conteudo));
        System.out.println("História postada com sucesso!");
    }

    private static void verHistorias() {
        if (historias.isEmpty()) {
            System.out.println("Ainda não há histórias.");
        } else {
            System.out.println("\n=== Histórias ===");
            for (int i = 0; i < historias.size(); i++) {
                System.out.println("\nHistória #" + (i + 1));
                historias.get(i).exibirHistoria();
            }
        }
    }

    private static void comentarHistoria(Scanner scanner) {
        if (historias.isEmpty()) {
            System.out.println("Ainda não há histórias para comentar.");
            return;
        }

        verHistorias();
        System.out.print("Escolha o número da história para comentar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        if (indice < 1 || indice > historias.size()) {
            System.out.println("Número inválido.");
        } else {
            System.out.print("Seu comentário: ");
            String comentario = scanner.nextLine();
            historias.get(indice - 1).adicionarComentario(comentario);
            System.out.println("Comentário adicionado!");
        }
    }
}

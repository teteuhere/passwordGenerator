import java.security.SecureRandom;
import java.util.Scanner;

public class senhaGerador {

    private static final String MINUSCULO = "abcdefghijklmnopqrstuvxyz";
    private static final String MAIUSCULO = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private static final String DIGITOS = "0123456789";
    private static final String CARACTERES_ESPECIAIS = "!@#$%^*()-_=+[]{}|;:'\",.?/<>";

    public static String senhaGerador(int length, boolean incluirMinusculo, boolean incluirMaiusculo, boolean incluirDigitos, boolean incluirCaracteresEspeciais) {
        if (length <= 0) {
            throw new IllegalArgumentException("Comprimento da senha deve ser maior que 0.");
        }
        if (length >= 40) {
            throw new IllegalArgumentException("Comprimento da senha deve ser menor que 40 caracteres.");
        }

        StringBuilder senha = new StringBuilder();
        String caracteres = "";

        if (incluirMinusculo) caracteres += MINUSCULO;
        if (incluirMaiusculo) caracteres += MAIUSCULO;
        if (incluirDigitos) caracteres += DIGITOS;
        if (incluirCaracteresEspeciais) caracteres += CARACTERES_ESPECIAIS;

        if (caracteres.isEmpty()) {
            throw new IllegalArgumentException("Necessário pelo menos um caracter especial, número, letra minúscula e maiúscula.");
        }

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(caracteres.length());
            char randomChar = caracteres.charAt(randomIndex);
            senha.append(randomChar);
        }

        return senha.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o tamanho da senha que deseja criar? ");
        int senhaLength = scanner.nextInt();

        System.out.print("Me forneça detalhes, possuirá letras minúsculas? (S/N) ");
        boolean incluirMinusculo = simResposta(scanner.next());

        System.out.print("A respeito de letras maiúsculas? (S/N) ");
        boolean incluirMaiusculo = simResposta(scanner.next());

        System.out.print("Incluirá números? (S/N) ");
        boolean incluirDigitos = simResposta(scanner.next());

        System.out.print("Por fim, incluirá caracteres especiais? (S/N) ");
        boolean incluirCaracteresEspeciais = simResposta(scanner.next());

        String senhaGerada = senhaGerador(senhaLength, incluirMinusculo, incluirMaiusculo, incluirDigitos, incluirCaracteresEspeciais);
        System.out.println("Senha criada: " + senhaGerada);

        scanner.close();
    }

    private static boolean simResposta(String response) {
        return response.trim().equalsIgnoreCase("s") || response.trim().equalsIgnoreCase("sim");
    }

    private static boolean naoResposta(String response) {
        return response.trim().equalsIgnoreCase("n") || response.trim().equalsIgnoreCase("nao") || response.trim().equalsIgnoreCase("não");
    }
}

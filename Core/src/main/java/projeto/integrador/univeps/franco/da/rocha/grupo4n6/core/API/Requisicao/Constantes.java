package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao;

public interface Constantes {
    String EMAIL = "email";
    String ASSINATURA = "assinatura";

    String ASSINATURA_ADMIN = "assinatura_admin";

    interface ContaAcoes {
        byte ALTERAR_EMAIL = 0x0;

        Object[] ALTERAR_EMAIL_ARGS = new String[] {
          "Email Antigo",
          "Email Novo",
          "Chave Antiga"
        };

        byte ALTERAR_SENHA = 0x1;
        byte ALTERAR_CADASTRO = 0x2;

        byte BAIXAR_CADASTRO = 0x3;
    }
}

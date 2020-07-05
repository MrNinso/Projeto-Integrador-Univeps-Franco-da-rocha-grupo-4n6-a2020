package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao;

import java.io.Serializable;

public interface Constantes {
    String EMAIL = "email";
    String ASSINATURA = "assinatura";

    String ASSINATURA_ADMIN = "assinatura_admin";

    enum ContaAcoes {
        ALTERAR_EMAIL((byte) 0x0),
        ALTERAR_SENHA((byte)0x1),
        ALTERAR_CADASTRO((byte) 0x2),
        BAIXAR_CADASTRO((byte) 0x3);

        byte b;

        ContaAcoes(byte info) {
            this.b = info;
        }

        public static class AlterarArgs implements Serializable {
            public final Object Antigo, Novo;

            public AlterarArgs(Object antigo, Object novo) {
                Antigo = antigo;
                Novo = novo;
            }
        }
    }
}

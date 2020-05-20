package projeto.integrador.univeps.franco.da.rocha.grupo4n6.core.API.Requisicao;

public enum LoginArgumento {
    EMAIL((byte) 0x0),
    CHAVE_LOGIN((byte) 0x1);

    private byte arg;

    LoginArgumento(byte arg) {
        this.arg = arg;
    }

    public byte getArg() {
        return arg;
    }
}

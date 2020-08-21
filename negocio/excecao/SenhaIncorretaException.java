package negocio.excecao;

public class SenhaIncorretaException extends Exception {

    boolean senhaIncorreta;

    public SenhaIncorretaException(){
        super("A senha está incorreta");
        this.senhaIncorreta = true;
    }

    public boolean isSenhaIncorreta() {
        return senhaIncorreta;
    }
}

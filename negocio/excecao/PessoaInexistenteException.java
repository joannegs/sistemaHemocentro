package negocio.excecao;

public class PessoaInexistenteException extends Exception {
	
	private boolean naoexistencia;

    public PessoaInexistenteException() {
        super("Pessoa nao encontrado!");
        this.naoexistencia = true;
    }

    public boolean isExistencia() {
        return naoexistencia;
    }

}

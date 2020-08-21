package negocio.excecao;

public class PessoaJaExistenteException extends Exception{
	
	private boolean clienteJaExisteNoRepositorio;

    public PessoaJaExistenteException(){
        super("Pessoa ja esta cadastrada");
        this.clienteJaExisteNoRepositorio = true;
    }

    public boolean isClienteJaExisteNoRepositorio() {
        return clienteJaExisteNoRepositorio;
    }
}

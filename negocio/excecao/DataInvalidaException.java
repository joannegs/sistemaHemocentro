package negocio.excecao;

public class DataInvalidaException extends Exception {

    public DataInvalidaException(){
        super("A data é inválida");
    }

}

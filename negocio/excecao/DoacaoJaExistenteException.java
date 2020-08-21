package negocio.excecao;

public class DoacaoJaExistenteException extends Exception {

    boolean agendamentoJaExiste;

    public  DoacaoJaExistenteException(){
        super("A doação já está cadastrado");
        this.agendamentoJaExiste = true;
    }


}

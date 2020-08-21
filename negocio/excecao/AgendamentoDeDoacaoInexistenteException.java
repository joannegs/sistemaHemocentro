package negocio.excecao;

public class AgendamentoDeDoacaoInexistenteException extends Exception {

    public AgendamentoDeDoacaoInexistenteException(){
        super("Agendamento não cadastrado");
    }

}

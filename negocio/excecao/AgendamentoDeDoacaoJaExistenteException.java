package negocio.excecao;

public class AgendamentoDeDoacaoJaExistenteException extends Exception {

    boolean agendamentoJaExiste;

    public  AgendamentoDeDoacaoJaExistenteException(){
        super("O agendamento já está cadastrado");
            this.agendamentoJaExiste = true;
        }


    }



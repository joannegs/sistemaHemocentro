package negocio;

import negocio.entidade.AgendamentoDoacao;
import negocio.excecao.AgendamentoDeDoacaoInexistenteException;
import negocio.excecao.AgendamentoDeDoacaoJaExistenteException;
import negocio.excecao.PessoaInexistenteException;
import repositorio.RepositorioAgendamentosDeDoacao;
import repositorio.RepositorioDoador;

import java.time.LocalDate;
import java.util.ArrayList;

public class NegocioAgendamentoDeDoacao {

    private RepositorioAgendamentosDeDoacao repositorioAgendamentosDeDoacao;
    private RepositorioDoador repositorioDoador;

    public NegocioAgendamentoDeDoacao() {
        this.repositorioAgendamentosDeDoacao = RepositorioAgendamentosDeDoacao.getInstance();
        this.repositorioDoador = RepositorioDoador.getInstance();
    }

    public AgendamentoDoacao buscar(String cpf_doador, LocalDate data) {
        return repositorioAgendamentosDeDoacao.buscar(cpf_doador, data);
    }

    public ArrayList<AgendamentoDoacao> listarTodosOsAgendamentos() {
        return repositorioAgendamentosDeDoacao.listarTodosOsAgendamentos();
    }

    public void adicionar(AgendamentoDoacao agendamento) throws AgendamentoDeDoacaoJaExistenteException {
        if (repositorioAgendamentosDeDoacao.checarExistencia(agendamento.getDoador().getCpf(), agendamento.getData()) == true) {
            throw new AgendamentoDeDoacaoJaExistenteException();
        } else {
            repositorioAgendamentosDeDoacao.adicionar(agendamento);
        }
    }

    public void remover(String cpf_doador, LocalDate dataDoacao) throws AgendamentoDeDoacaoInexistenteException {
        if (repositorioAgendamentosDeDoacao.buscar(cpf_doador, dataDoacao) != null) {
            throw new AgendamentoDeDoacaoInexistenteException();
        } else {
            repositorioAgendamentosDeDoacao.remover(cpf_doador, dataDoacao);
        }
    }

    public void atualizar_doador(String cpfDoador, LocalDate data, String cpf_novo) throws AgendamentoDeDoacaoInexistenteException, PessoaInexistenteException {
        if (repositorioDoador.buscarDoador(cpfDoador) == null) {
            throw new PessoaInexistenteException();
        } else {
            if (repositorioAgendamentosDeDoacao.buscar(cpfDoador, data) == null) {
                throw new AgendamentoDeDoacaoInexistenteException();
            } else {
                repositorioAgendamentosDeDoacao.atualizarDoador(cpfDoador, data, cpf_novo);
            }
        }
    }

    public void atualizar_data(String cpfDoador, LocalDate data, LocalDate nova_data) throws AgendamentoDeDoacaoInexistenteException, PessoaInexistenteException {
        if(repositorioDoador.buscarDoador(cpfDoador) == null) {
            throw new PessoaInexistenteException();
        } else {
            if(repositorioAgendamentosDeDoacao.buscar(cpfDoador, data) == null) {
                throw new AgendamentoDeDoacaoInexistenteException();
            } else {
                repositorioAgendamentosDeDoacao.atualizarData(cpfDoador, data, nova_data);
            }
        }
    }
}


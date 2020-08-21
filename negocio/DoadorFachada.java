package negocio;

import negocio.entidade.*;
import negocio.excecao.AgendamentoDeDoacaoJaExistenteException;
import negocio.excecao.DataInvalidaException;
import negocio.excecao.PessoaInexistenteException;
import negocio.excecao.PessoaJaExistenteException;
import repositorio.RepositorioDoador;


import java.time.LocalDate;
import java.util.ArrayList;

public class DoadorFachada {

    protected NegocioDoador negocioDoador;
    protected NegocioAgendamentoDeDoacao negocioAgendamentoDeDoacao;
    protected NegocioDoacoesSanguineas negocioDoacoesSanguineas;

    public DoadorFachada() {
        this.negocioDoador = new NegocioDoador(RepositorioDoador.getInstance());
        this.negocioAgendamentoDeDoacao = new NegocioAgendamentoDeDoacao();
        this.negocioDoacoesSanguineas = new NegocioDoacoesSanguineas();
    }

    public void agendarDoacao(String cpfDoador, LocalDate dataAgendamento) throws AgendamentoDeDoacaoJaExistenteException, PessoaInexistenteException, DataInvalidaException {
        this.negocioDoador.agendarDoacao(cpfDoador, dataAgendamento);
    }

    public ArrayList<DoacaoSanguinea> visualizarDoacoes(String cpf) {
        return negocioDoacoesSanguineas.buscarTodasAsDoacoesPorPessoa(cpf);
    }


    public void atualizarNome(String cpf, String nome) throws PessoaInexistenteException {
        negocioDoador.atualizarNome(cpf, nome);
    }

    public void atualizarCpf(String cpf, String novoCpf) throws PessoaInexistenteException {
        negocioDoador.atualizarCPF(cpf, novoCpf);
    }

    public void atuzalizarIdade(String cpf) throws PessoaInexistenteException {
        negocioDoador.atualizarIdade(cpf);
    }

    public void atualizarGenero(String cpf) throws PessoaInexistenteException {
        negocioDoador.atualizarGenero(cpf);
    }

    public void atualizarTipoSanguineo(String cpf, String novoTipoSanguineo) throws PessoaInexistenteException {
        negocioDoador.atualizarTipoSanguineo(cpf, novoTipoSanguineo);
    }

    public void atualizarSenha(String cpf, String novaSenha) throws PessoaInexistenteException {
        negocioDoador.atualizarSenha(cpf, novaSenha);
    }

    public boolean logar(String numIdentificador, String senha) throws PessoaInexistenteException {
        Doador doador = negocioDoador.buscarDoador(numIdentificador);
        return doador.autenticar(numIdentificador, senha);
    }


    public ArrayList<FichaMedica> visualizarFichasMedicas(String cpf) throws PessoaInexistenteException{
        return this.negocioDoador.verFichas(cpf);
    }

    public boolean checarExistencia(String cpf) {
        return negocioDoador.checarExistencia(cpf);
    }

}
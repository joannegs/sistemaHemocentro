package negocio;

import negocio.entidade.*;
import negocio.excecao.*;
import repositorio.RepositorioAgendamentosDeDoacao;
import repositorio.RepositorioDoacoesSanguineas;
import repositorio.RepositorioDoador;

import java.time.LocalDate;
import java.util.ArrayList;


public class NegocioDoador {

    private RepositorioDoador repositorioDoador;
    private RepositorioAgendamentosDeDoacao repositorioAgendamentos;
    private RepositorioDoacoesSanguineas repositorioDoacoesSanguineas;

    public NegocioDoador(RepositorioDoador repositorioDoador) {
        this.repositorioDoador = repositorioDoador;
        this.repositorioAgendamentos = new RepositorioAgendamentosDeDoacao();
        this.repositorioDoacoesSanguineas = new RepositorioDoacoesSanguineas();
    }

    public void remover(String cpf) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.remover(cpf);}
        else{throw new PessoaInexistenteException();}
    }

    public ArrayList<FichaMedica> verFichas(String cpf) throws PessoaInexistenteException{
        if(repositorioDoador.checarExistencia(cpf)){
            return this.repositorioDoacoesSanguineas.filtrarFichasPorDoador(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }


    public void adicionar(Pessoa pessoa) throws PessoaJaExistenteException, InformacoesNulasException {
        if (repositorioDoador.checarExistencia(pessoa.getCpf())) {
            throw new PessoaJaExistenteException();
        } else {
            if (pessoa.getNome().equals("") || pessoa.getCpf().equals("") || pessoa.getSenha().equals("") || pessoa.getIdade() == 0 || pessoa.getGenero().equals("")) {
                throw new InformacoesNulasException();
            } else {
                repositorioDoador.adicionar(pessoa);
            }
        }
    }

    public ArrayList<Doador> listarTodosOsDoadores() {
        return repositorioDoador.listarTodosOsDoadores();
    }


    public void AtribuirFDM(String cpfDoador, FormularioDoacaoMedula formularioDoacaoMedula) throws PessoaInexistenteException , JaEhDoadorMedulaException {
        Doador doadorTeste = repositorioDoador.buscarDoador(cpfDoador);
        if(doadorTeste == null){
            throw new PessoaInexistenteException();
        }else{
            if(doadorTeste.isDoadorDeMedula()){
                throw new JaEhDoadorMedulaException();
            }else {
                repositorioDoador.atribuirFormularioDoacaoMedula(cpfDoador, formularioDoacaoMedula);
            }
        }
    }


    public void removerCadastroMedula(String cpfDoador) throws PessoaInexistenteException, DoadorMedulaException {
        Doador doador_teste = buscarDoador(cpfDoador);
        if(doador_teste == null) {
            throw new PessoaInexistenteException();
        }else {
            if(!doador_teste.isDoadorDeMedula()){
                throw new DoadorMedulaException();
            }else{
                repositorioDoador.apagarFormularioDoacaoDeMedula(cpfDoador);
            }
        }
    }

    public boolean checarExistencia(String cpf) {
        return repositorioDoador.checarExistencia(cpf);
    }


    public void atualizarNome(String cpf, String novo_nome) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.atualizarNome(cpf, novo_nome);}
        else{throw new PessoaInexistenteException();}
    }

    public void atualizarCPF(String cpf, String novo_cpf) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.atualizarNome(cpf,novo_cpf);}
        else{throw new PessoaInexistenteException();}
    }

    public void atualizarGenero(String cpf) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.atualizarGenero(cpf);}
        else{throw new PessoaInexistenteException();}
    }

    public void atualizarSenha(String cpf, String nova_senha) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.atualizarSenha(cpf,nova_senha);}
        else{throw new PessoaInexistenteException();}
    }

    public void atualizarIdade(String cpf) throws PessoaInexistenteException {
        if(repositorioDoador.checarExistencia(cpf)){repositorioDoador.atualizarIdade(cpf);}
        else{throw new PessoaInexistenteException();}
    }

    public void atualizarTipoSanguineo(String cpf, String novoTipoSanguineo) throws PessoaInexistenteException{
        if(repositorioDoador.checarExistencia(cpf)){
            repositorioDoador.atualizarTipoSanguineo(cpf, novoTipoSanguineo);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public Doador buscarDoador(String cpf) throws PessoaInexistenteException{
        if(repositorioDoador.checarExistencia(cpf) == true){
            return repositorioDoador.buscarDoador(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void agendarDoacao(String cpf, LocalDate data) throws PessoaInexistenteException, AgendamentoDeDoacaoJaExistenteException, DataInvalidaException {
        if (this.checarExistencia(cpf)) {
            if (this.repositorioAgendamentos.quantosAgendamentosAgendadosNaData(data) < 20) {
                if (this.repositorioAgendamentos.checarExistencia(cpf, data) == false) {
                    Doador doador = repositorioDoador.buscarDoador(cpf);
                    AgendamentoDoacao agendamento = new AgendamentoDoacao(doador, data);
                    this.repositorioAgendamentos.adicionar(agendamento);

                } else {
                    throw new AgendamentoDeDoacaoJaExistenteException();
                }
            } else {
                throw new DataInvalidaException();
            }
        } else {
            throw new PessoaInexistenteException();
        }
    }
}

package negocio;

import negocio.entidade.*;
import negocio.excecao.*;
import repositorio.RepositorioAgendamentosDeDoacao;
import repositorio.RepositorioDoacoesSanguineas;
import repositorio.RepositorioDoador;
import repositorio.RepositorioFuncionarios;

import java.time.LocalDate;
import java.util.ArrayList;

public class NegocioFuncionario {

    private RepositorioFuncionarios funcionarios;
    private RepositorioDoador doadores;
    private RepositorioAgendamentosDeDoacao agendamentos;
    private RepositorioDoacoesSanguineas doacoes;

    public NegocioFuncionario() {
        this.funcionarios = RepositorioFuncionarios.getInstance();
        this.doadores = RepositorioDoador.getInstance();
        this.agendamentos = RepositorioAgendamentosDeDoacao.getInstance();
        this.doacoes = RepositorioDoacoesSanguineas.getInstance();
    }

    public ArrayList<Funcionario> listarTodosOsFuncionarios() {
        return funcionarios.listarTodosOsFuncionarios();
    }

    public void remover(String cpf) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.remover(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void adicionar(Pessoa pessoa) throws PessoaJaExistenteException, InformacoesNulasException {
        if (funcionarios.checarExistencia(pessoa.getCpf())) {
            throw new PessoaJaExistenteException();
        } else {
            if(pessoa.getNome().equals("") || pessoa.getCpf().equals("") || pessoa.getSenha().equals("") || pessoa.getIdade() == 0 || pessoa.getGenero().equals("") ){
                throw new InformacoesNulasException();
            } else {
                funcionarios.adicionar(pessoa);
            }
        }
    }

    public boolean checarExistencia(String cpf) {
        return true;
    }

    public void atualizarNome(String cpf, String novo_nome) throws PessoaInexistenteException, PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.atualizarNome(cpf, novo_nome);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void atualizarCPF(String cpf, String novo_cpf) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.atualizarCPF(cpf, novo_cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void atualizarGenero(String cpf) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.atualizarGenero(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void atualizarSenha(String cpf, String nova_senha) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.atualizarSenha(cpf, nova_senha);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public void atualizarIdade(String cpf) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf)) {
            funcionarios.atualizarIdade(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public Funcionario buscarFuncionario(String cpf) throws PessoaInexistenteException {
        if (funcionarios.checarExistencia(cpf) == true) {
            return funcionarios.buscarFuncionario(cpf);
        } else {
            throw new PessoaInexistenteException();
        }
    }

    public FormularioDoacao verificaSePodeDoar(boolean fumante, boolean alcoolatra, boolean gestante, boolean lactante, boolean diabetico) {
        if (fumante == true || lactante == true || alcoolatra == true || gestante == true || diabetico == true) {
            return null;
        } else {
            return new FormularioDoacao(fumante, alcoolatra, gestante, lactante, diabetico);
        }
    }

    public void adicionarDoacao(String cpf, LocalDate data, FormularioDoacao formulario) throws PessoaInexistenteException, DoacaoJaExistenteException, DataInvalidaException{
        if(doacoes.checarExistencia(cpf, data) == false){
            if(doadores.checarExistencia(cpf)){
                if(this.doacoes.quantasDoacoesNaData(data) < 20){
                    DoacaoSanguinea doacao = new DoacaoSanguinea(doadores.buscarDoador(cpf), data, formulario);
                    this.doacoes.adicionar(doacao);
                } else {
                    throw new DataInvalidaException();
                }
            } else {
                throw new PessoaInexistenteException();
            }
        } else {
            throw new DoacaoJaExistenteException();
        }
    }


    public DoacaoSanguinea buscarDoacao(String cpf, LocalDate data) throws DoacaoSanguineaInexistenteException {
        if(doacoes.checarExistencia(cpf, data)){
            return doacoes.buscar(cpf, data);
        } else {
            throw new DoacaoSanguineaInexistenteException();
        }
    }


}

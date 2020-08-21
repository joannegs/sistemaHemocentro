package negocio;

import negocio.entidade.*;
import negocio.excecao.*;
import repositorio.RepositorioDoador;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionarioFachada extends DoadorFachada {

    private NegocioFuncionario negocioFuncionario;

    public FuncionarioFachada() {
        super();
        this.negocioFuncionario = new NegocioFuncionario();
    }

    public Doador buscarDoador(String cpf) throws PessoaInexistenteException{
        return negocioDoador.buscarDoador(cpf);
    }

    public Funcionario buscarFuncionario(String cpf) throws PessoaInexistenteException{
        return negocioFuncionario.buscarFuncionario(cpf);
    }

    public void adicionar(Doador doador) throws PessoaJaExistenteException, InformacoesNulasException {
        negocioDoador.adicionar(doador);
    }

    public void atribuirFichaMedica(String cpfDoador, LocalDate dataDoacao, FichaMedica fichaMedica) {
        negocioDoacoesSanguineas.buscar(cpfDoador, dataDoacao).setFicha(fichaMedica);
    }

    public void apagarCadastro(String cpfDoador) throws PessoaInexistenteException {
        negocioDoador.remover(cpfDoador);
    }


    public ArrayList<Doador> listarDoadores() {
        return negocioDoador.listarTodosOsDoadores();
    }


    public ArrayList<DoacaoSanguinea> listarDoacoes() {
        return negocioDoacoesSanguineas.listarTodasAsDoacoes();
    }


    public ArrayList<AgendamentoDoacao> listarAgendamentos() {
        return negocioAgendamentoDeDoacao.listarTodosOsAgendamentos();
    }

    public ArrayList<FichaMedica> listarFichas(){
        return  negocioDoacoesSanguineas.listarTodasAsFichas();
    }


    public void atribuirFDM(String cpfDoador, FormularioDoacaoMedula formularioDoacaoMedula) throws PessoaInexistenteException , JaEhDoadorMedulaException {
        negocioDoador.AtribuirFDM(cpfDoador, formularioDoacaoMedula);
    }

    public void agendarDoacao(String cpfDoador, LocalDate data) throws PessoaInexistenteException, AgendamentoDeDoacaoJaExistenteException, DataInvalidaException {
        negocioDoador.agendarDoacao(cpfDoador, data);
    }


    public void removerCadastroMedula(String cpfDoador) throws PessoaInexistenteException, DoadorMedulaException {
        negocioDoador.removerCadastroMedula(cpfDoador);
    }

    public boolean logarFuncionario(String numIdentificador, String senha) throws PessoaInexistenteException{
        Funcionario funcionario = negocioFuncionario.buscarFuncionario(numIdentificador);
        return funcionario.autenticar(numIdentificador, senha);
    }

    public FormularioDoacao criarFormularioDeDoacao(boolean fumante, boolean alcoolatra, boolean gestante, boolean lactante, boolean diabetico){
        return this.negocioFuncionario.verificaSePodeDoar(fumante, alcoolatra, gestante, lactante, diabetico);
    }

    public DoacaoSanguinea buscarDoacao(String cpf, LocalDate data) throws DoacaoSanguineaInexistenteException{
        return negocioFuncionario.buscarDoacao(cpf, data);
    }

    public void adicionarDoacao(String cpf, LocalDate data, FormularioDoacao formularioDoacao) throws PessoaInexistenteException, DoacaoJaExistenteException, DataInvalidaException{
        negocioFuncionario.adicionarDoacao(cpf, data, formularioDoacao);
    }

}
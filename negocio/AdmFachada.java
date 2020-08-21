package negocio;

import negocio.entidade.Funcionario;
import negocio.excecao.InformacoesNulasException;
import negocio.excecao.PessoaInexistenteException;
import negocio.excecao.PessoaJaExistenteException;

import java.util.ArrayList;

public class AdmFachada {

    private NegocioFuncionario negocioFuncionario;
    private NegocioDoacoesSanguineas negocioDoacoes;

    public AdmFachada() {
        this.negocioFuncionario = new NegocioFuncionario();
        this.negocioDoacoes = new NegocioDoacoesSanguineas();
    }

    public void contratarFuncionario(Funcionario funcionario) throws PessoaJaExistenteException, InformacoesNulasException {
        negocioFuncionario.adicionar(funcionario);
    }

    public void demitirFuncionario(String cpfFuncionario) throws PessoaInexistenteException {
        negocioFuncionario.remover(cpfFuncionario);
    }

    public void atualizarNomeFuncionario(String cpf, String novoNome) throws PessoaInexistenteException{
        negocioFuncionario.atualizarNome(cpf, novoNome);
    }

    public void atualizarCpfFuncionario(String cpf, String novoCpf) throws PessoaInexistenteException{
        negocioFuncionario.atualizarCPF(cpf, novoCpf);
    }

    public void atualizarIdadeFuncionario(String cpf) throws PessoaInexistenteException{
        negocioFuncionario.atualizarIdade(cpf);
    }

    public void atualizarGeneroFuncionario(String cpf) throws PessoaInexistenteException{
        negocioFuncionario.atualizarGenero(cpf);
    }

    public void atualizarSenhaFuncionario(String cpf, String novaSenha) throws PessoaInexistenteException{
        negocioFuncionario.atualizarSenha(cpf, novaSenha);
    }


    public ArrayList<Funcionario> listarFuncionarios() {
        return negocioFuncionario.listarTodosOsFuncionarios();
    }


    // consulta

    public double sangueA(){
        return this.negocioDoacoes.sangueA();
    }

    public double sangueAP(){
        return this.negocioDoacoes.sangueAP();
    }

    public double sangueAN(){
        return this.negocioDoacoes.sangueAN();
    }

    public double sangueB(){
        return this.negocioDoacoes.sangueB();
    }

    public double sangueBP(){
        return this.negocioDoacoes.sangueBP();
    }

    public double sangueBN(){
        return this.negocioDoacoes.sangueBN();
    }

    public double sangueAB(){
        return this.negocioDoacoes.sangueAB();
    }

    public double sangueABP(){
        return this.negocioDoacoes.sangueABP();
    }

    public double sangueABN(){
        return this.negocioDoacoes.sangueABN();
    }

    public double sangueO(){
        return this.negocioDoacoes.sangueO();
    }

    public double sangueOP(){
        return this.negocioDoacoes.sangueOP();
    }

    public double sangueON(){
        return this.negocioDoacoes.sangueON();
    }

    public double doadoresHomens(){
        return this.negocioDoacoes.doadoresHomens();
    }

    public double doadoresHomensA(){
        return this.negocioDoacoes.doadoresHomensA();
    }

    public double doadoresHomensAP(){
        return this.negocioDoacoes.doadoresHomensAP();
    }

    public double doadoresHomensAN(){
        return this.negocioDoacoes.doadoresHomensAN();
    }

    public double doadoresHomensB(){
        return this.negocioDoacoes.doadoresHomensB();
    }

    public double doadoresHomensBP(){
        return this.negocioDoacoes.doadoresHomensBP();
    }

    public double doadoresHomensBN(){
        return this.negocioDoacoes.doadoresHomensAN();
    }

    public double doadoresHomensAB(){
        return this.negocioDoacoes.doadoresHomensAB();
    }

    public double doadoresHomensABP(){
        return this.negocioDoacoes.doadoresHomensABP();
    }

    public double doadoresHomensABN(){
        return this.negocioDoacoes.doadoresHomensABN();
    }

    public double doadoresHomensO(){
        return this.negocioDoacoes.doadoresHomensO();
    }

    public double doadoresHomensOP(){
        return this.negocioDoacoes.doadoresHomensOP();
    }

    public double doadoresHomensON(){
        return this.negocioDoacoes.doadoresHomensON();
    }


    public double doadoresMulheres(){
        return this.negocioDoacoes.doadoresMulheres();
    }

    public double doadoresMulheresA(){
        return this.negocioDoacoes.doadoresMulheresA();
    }

    public double doadoresMulheresAP(){
        return this.negocioDoacoes.doadoresMulheresAP();
    }

    public double doadoresMulheresAN(){
        return this.negocioDoacoes.doadoresMulheresAN();
    }

    public double doadoresMulheresB(){
        return this.negocioDoacoes.doadoresMulheresB();
    }

    public double doadoresMulheresBP(){
        return this.negocioDoacoes.doadoresMulheresBP();
    }

    public double doadoresMulheresBN(){
        return this.negocioDoacoes.doadoresMulheresBN();
    }

    public double doadoresMulheresAB(){
        return this.negocioDoacoes.doadoresMulheresAB();
    }

    public double doadoresMulheresABP(){
        return this.negocioDoacoes.doadoresMulheresABP();
    }

    public double doadoresMulheresABN(){
        return this.negocioDoacoes.doadoresMulheresABN();
    }

    public double doadoresMulheresO(){
        return this.negocioDoacoes.doadoresMulheresO();
    }

    public double doadoresMulheresOP(){
        return this.negocioDoacoes.doadoresMulheresOP();
    }

    public double doadoresMulheresON(){
        return this.negocioDoacoes.doadoresMulheresON();
    }

}

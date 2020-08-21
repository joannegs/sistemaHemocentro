package negocio;

import negocio.entidade.DoacaoSanguinea;
import negocio.entidade.FichaMedica;
import negocio.excecao.DoacaoJaExistenteException;
import negocio.excecao.DoacaoSanguineaInexistenteException;
import negocio.excecao.PessoaInexistenteException;
import repositorio.RepositorioDoacoesSanguineas;
import repositorio.RepositorioDoador;

import java.time.LocalDate;
import java.util.ArrayList;

public class NegocioDoacoesSanguineas {

    private RepositorioDoacoesSanguineas repositorioDoacoesSanguineas;
    private RepositorioDoador repositorioDoador;

    public NegocioDoacoesSanguineas() {
        this.repositorioDoacoesSanguineas = RepositorioDoacoesSanguineas.getInstance();
        this.repositorioDoador = RepositorioDoador.getInstance();
    }

    public void adicionar(DoacaoSanguinea doacao) throws DoacaoJaExistenteException {
        if (repositorioDoacoesSanguineas.checarExistencia(doacao.getDoador().getCpf(), doacao.getData()) == true) {
            throw new DoacaoJaExistenteException();
        } else {
            repositorioDoacoesSanguineas.adicionar(doacao);
        }
    }

    public DoacaoSanguinea buscar(String cpfDoador, LocalDate data) {
        return repositorioDoacoesSanguineas.buscar(cpfDoador, data);
    }

    public ArrayList<DoacaoSanguinea> buscarTodasAsDoacoesPorPessoa(String cpf) {
        return repositorioDoacoesSanguineas.buscarDoacoesPorDoador(cpf);
    }

    public ArrayList<FichaMedica> buscarFichasPorPessoa(String cpf) throws NullPointerException{
        return this.repositorioDoacoesSanguineas.filtrarFichasPorDoador(cpf);
    }

    public ArrayList<DoacaoSanguinea> listarTodasAsDoacoes() {
        return repositorioDoacoesSanguineas.listarTodasAsDoacoes();
    }

    public ArrayList<FichaMedica> listarTodasAsFichas(){
        return repositorioDoacoesSanguineas.retornarTodasAsFichas();
    }


    public void remover(String cpf_doador, LocalDate dataDoacao) throws DoacaoSanguineaInexistenteException {
        if (repositorioDoacoesSanguineas.buscar(cpf_doador, dataDoacao) != null) {
            throw new DoacaoSanguineaInexistenteException();
        } else {
            repositorioDoacoesSanguineas.remover(cpf_doador, dataDoacao);
        }
    }


    public void atualizar_doador(String cpfDoador, LocalDate data, String cpf_novo) throws DoacaoSanguineaInexistenteException, PessoaInexistenteException {
        if (repositorioDoador.buscarDoador(cpfDoador) == null) {
            throw new PessoaInexistenteException();
        } else {
            if (repositorioDoacoesSanguineas.buscar(cpfDoador, data) == null) {
                throw new DoacaoSanguineaInexistenteException();
            } else {
                repositorioDoacoesSanguineas.atualizarDoador(cpfDoador, data, cpf_novo);
            }
        }
    }

    public void atualizar_data(String cpfDoador, LocalDate data, LocalDate nova_data) throws DoacaoSanguineaInexistenteException, PessoaInexistenteException {
        if(repositorioDoador.buscarDoador(cpfDoador) == null) {
            throw new PessoaInexistenteException();
        } else {
            if(repositorioDoacoesSanguineas.buscar(cpfDoador, data) == null) {
                throw new DoacaoSanguineaInexistenteException();
            } else {
                repositorioDoacoesSanguineas.atualizarData(cpfDoador, data, nova_data);
            }
        }
    }

    public double sangueA(){
        return this.repositorioDoacoesSanguineas.sangueA();
    }

    public double sangueAP(){
        return this.repositorioDoacoesSanguineas.sangueAP();
    }

    public double sangueAN(){
        return this.repositorioDoacoesSanguineas.sangueAN();
    }

    public double sangueB(){
        return this.repositorioDoacoesSanguineas.sangueB();
    }

    public double sangueBP(){
        return this.repositorioDoacoesSanguineas.sangueBP();
    }

    public double sangueBN(){
        return this.repositorioDoacoesSanguineas.sangueBN();
    }

    public double sangueAB(){
        return this.repositorioDoacoesSanguineas.sangueAB();
    }

    public double sangueABP(){
        return this.repositorioDoacoesSanguineas.sangueABP();
    }

    public double sangueABN(){
        return this.repositorioDoacoesSanguineas.sangueABN();
    }


    public double sangueO(){
        return this.repositorioDoacoesSanguineas.sangueO();
    }

    public double sangueOP(){
        return this.repositorioDoacoesSanguineas.sangueOP();
    }

    public double sangueON(){
        return this.repositorioDoacoesSanguineas.sangueON();
    }

    public double doadoresHomens(){
        return this.repositorioDoacoesSanguineas.doadoresHomens();
    }

    public double doadoresHomensA(){
        return this.repositorioDoacoesSanguineas.doadoresHomensSA();
    }

    public double doadoresHomensAP(){
        return this.repositorioDoacoesSanguineas.doadoresHomensSAP();
    }

    public double doadoresHomensAN(){
        return this.repositorioDoacoesSanguineas.doadoresHomensSAN();
    }

    public double doadoresHomensB(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSB();
    }

    public double doadoresHomensBP(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSBP();
    }

    public double doadoresHomensBN(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSBN();
    }

    public double doadoresHomensAB(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSAB();
    }

    public double doadoresHomensABP(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSABP();
    }

    public double doadoresHomensABN(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSABN();
    }

    public double doadoresHomensO(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSO();
    }

    public double doadoresHomensOP(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSOP();
    }

    public double doadoresHomensON(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSON();
    }


    public double doadoresMulheres(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheres();
    }

    public double doadoresMulheresA(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSA();
    }

    public double doadoresMulheresAP(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSAP();
    }

    public double doadoresMulheresAN(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSAN();
    }

    public double doadoresMulheresB(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSB();
    }

    public double doadoresMulheresBP(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSBP();
    }

    public double doadoresMulheresBN(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSBN();
    }

    public double doadoresMulheresAB(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSAB();
    }

    public double doadoresMulheresABP(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSABP();
    }

    public double doadoresMulheresABN(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSABN();
    }

    public double doadoresMulheresO(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSO();
    }

    public double doadoresMulheresOP(){
        return this.repositorioDoacoesSanguineas.doaodoresMulheresSOP();
    }

    public double doadoresMulheresON(){
        return this.repositorioDoacoesSanguineas.doaodoresHomensSON();
    }

}
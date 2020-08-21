package repositorio;

import negocio.entidade.AgendamentoDoacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class RepositorioAgendamentosDeDoacao implements IAgendamentoDeDoacao {

    private ArrayList<AgendamentoDoacao> agendamentos;
    private RepositorioDoador repositorioDoador;
    private static RepositorioAgendamentosDeDoacao instancia;

    // construtor

    public RepositorioAgendamentosDeDoacao() {
        this.repositorioDoador = RepositorioDoador.getInstance();
        this.agendamentos = new ArrayList<AgendamentoDoacao>();
    }

    // singleton

    public static RepositorioAgendamentosDeDoacao getInstance() {
        if(instancia == null) {
            instancia = new RepositorioAgendamentosDeDoacao();
        }
        return instancia;
    }

    // metodos

    public ArrayList<AgendamentoDoacao> listarTodosOsAgendamentos() {
        ArrayList<AgendamentoDoacao> agendamentosCopia = new ArrayList<AgendamentoDoacao>(agendamentos);
        Collections.copy(agendamentos, agendamentosCopia);
        return agendamentosCopia;
    }

    @Override
    public void adicionar(AgendamentoDoacao agendamento) {
        ArrayList<AgendamentoDoacao> agendamentosDoador = new ArrayList<AgendamentoDoacao>();
        agendamentos.add(agendamento);
    }

    public int quantosAgendamentosAgendadosNaData(LocalDate data){
        int agendamentos = 0;

        for(int i = 0; i < this.agendamentos.size(); i++){
            if(this.agendamentos.get(i).getData().equals(data)){
                agendamentos++;
            }
        }
        return agendamentos;
    }

    @Override
    public AgendamentoDoacao buscar(String cpfDoador, LocalDate data) {
        AgendamentoDoacao agendamentoFiltrado = null;
        if (repositorioDoador.buscarDoador(cpfDoador) != null){
            AgendamentoDoacao agendamento;
            for (int i = 0; i < agendamentos.size(); i++) {
                agendamento = agendamentos.get(i);
                if (agendamento.getDoador().equals(repositorioDoador.buscarDoador(cpfDoador)) && agendamento.getData().equals(data)) {
                    agendamentoFiltrado = agendamento;
                    break;
                }
            }
        }
        return agendamentoFiltrado;
    }

    @Override
    public void remover(String cpfDoador, LocalDate dataDoacao){
        if (buscar(cpfDoador, dataDoacao) != null) {
            for (int x = 0; x < agendamentos.size(); x++) {
                // remover doacao do arraylist geral
                if (agendamentos.get(x).getData().equals(dataDoacao) && agendamentos.get(x).getDoador().getCpf().equals(cpfDoador)) {
                    agendamentos.remove(x);
                }
            }
        }
    }

    public boolean checarExistencia(String cpfDoador, LocalDate data) {
        boolean existe = false;
        for(int x = 0; x < agendamentos.size() ;x++) {
            if(agendamentos.get(x).getData().equals(data) && agendamentos.get(x).getDoador().getCpf().equals(cpfDoador)){
                existe = true;
                break;
            }
        }
        return existe;
    }

    public void atualizarDoador(String cpfDoador, LocalDate data, String cpfNovo){
        if (repositorioDoador.buscarDoador(cpfNovo) != null) {
            if (buscar(cpfDoador, data) != null) {
                buscar(cpfDoador, data).setDoador(repositorioDoador.buscarDoador(cpfNovo));
            }
        }
    }

    public void atualizarData(String cpfDoador, LocalDate data, LocalDate novaData){
        if (buscar(cpfDoador, data) != null) {
            buscar(cpfDoador, data).setData(novaData);
        }
    }
}
package repositorio;

import negocio.entidade.AgendamentoDoacao;

import java.time.LocalDate;

public interface IAgendamentoDeDoacao {
    public void adicionar(AgendamentoDoacao agendamento);
    public AgendamentoDoacao buscar(String cpfDoador, LocalDate data);
    public void remover(String cpfDoador, LocalDate dataDoacao);
    public void atualizarDoador(String cpfDoador, LocalDate data, String cpfNovo);
    public void atualizarData(String cpfDoador, LocalDate data, LocalDate novaData);
}

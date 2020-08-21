package negocio.entidade;

import java.time.LocalDate;
import java.util.Objects;

public class AgendamentoDoacao {
    private Doador doador;
    private LocalDate data;
    public AgendamentoDoacao(Doador doador, LocalDate data){
        setDoador(doador);
        setData(data);
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object agendamento) {
        if (this == agendamento) return true;
        if (!(agendamento instanceof AgendamentoDoacao)) return false;

        AgendamentoDoacao that = (AgendamentoDoacao) agendamento;
        return Objects.equals(getDoador(), that.getDoador()) &&
                Objects.equals(getData(), that.getData());
    }

    @Override
    public String toString() {
        return  "DADOS DO DOADOR: " + getDoador() +
                "DATA AGENDADA: " + data;
    }
}

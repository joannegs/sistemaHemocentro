package negocio.entidade;

import java.time.LocalDate;
import java.util.Objects;

public class DoacaoSanguinea {
    private Doador doador;
    private LocalDate data;
    private FormularioDoacao formularioDoacao;
    private FichaMedica ficha;

    public DoacaoSanguinea(Doador doador, LocalDate data, FormularioDoacao formularioDoacao) {
        setDoador(doador);
        setData(data);
        setFormularioDoacao(formularioDoacao);
    }

    public FichaMedica getFicha() {
        return ficha;
    }

    public void setFicha(FichaMedica ficha) {
        this.ficha = ficha;
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

    public FormularioDoacao getFormularioDoacao() {
        return formularioDoacao;
    }

    public void setFormularioDoacao(FormularioDoacao formularioDoacao) {
        this.formularioDoacao = formularioDoacao;
    }

    @Override
    public String toString() {
        return "DADOS DO DOADOR: \n" + getDoador() +
                "DATA DA DOACAO: \n" + getData();
    }

    @Override
    public boolean equals(Object doacao) {
        if (this == doacao) return true;
        if (!(doacao instanceof DoacaoSanguinea)) return false;
        DoacaoSanguinea that = (DoacaoSanguinea) doacao;
        return Objects.equals(getDoador(), that.getDoador()) &&
                Objects.equals(getData(), that.getData());
    }

}

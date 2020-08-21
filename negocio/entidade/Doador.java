package negocio.entidade;

import java.util.ArrayList;

public class Doador extends Pessoa {
    private String tipoSanguineo;
    private boolean isDoadorDeMedula;
    private FormularioDoacaoMedula infosDoacaoMedula;
    private ArrayList<DoacaoSanguinea> doacoes;

    public Doador(String nome, String cpf, int idade, String senha, String tipoSanguineo, String genero) {
        super(nome, cpf, idade, genero, senha);
        this.tipoSanguineo = tipoSanguineo;
        this.doacoes = new ArrayList();
    }
    public Doador(String nome, String cpf, int idade, String senha, String tipoSanguineo, String genero, FormularioDoacaoMedula formulario) {
        super(nome, cpf, idade, genero, senha);
        this.tipoSanguineo = tipoSanguineo;
        this.doacoes = new ArrayList();
        this.infosDoacaoMedula = formulario;
        setDoadorDeMedula(true);
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public boolean isDoadorDeMedula() {
        return isDoadorDeMedula;
    }

    public void setDoadorDeMedula(boolean doadorDeMedula) {
        isDoadorDeMedula = doadorDeMedula;
    }

    public FormularioDoacaoMedula getInfosDoacaoMedula() {
        return infosDoacaoMedula;
    }

    public void setInfosDoacaoMedula(FormularioDoacaoMedula infosDoacaoMedula) {
        this.infosDoacaoMedula = infosDoacaoMedula;
    }

    public ArrayList<DoacaoSanguinea> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(ArrayList<DoacaoSanguinea> doacoes) {
        this.doacoes = doacoes;
    }

    @Override
    public String toString() {
        String doador;
        if(isDoadorDeMedula == true){
            doador = "Sim";
        } else {
            doador = "Nao";
        }

        return getCpf();

    }
}
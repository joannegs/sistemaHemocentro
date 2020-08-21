package negocio.entidade;

public class FormularioDoacaoMedula {
    private String nacionalidade;
    private String raca;
    private String nomeMae;
    private String nomePai;
    private String endereco;
    private String telefone;

    public FormularioDoacaoMedula(String nacionalidade, String raca, String nomeMae, String nomePai, String endereco, String telefone) {
        this.nacionalidade = nacionalidade;
        this.raca = raca;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return  "Nacionalidade: " + nacionalidade + "\n" +
                "Raca: " + raca + "\n" +
                "Nome da mae: " + nomeMae + "\n" +
                "Nome do pai: " + nomePai + "\n" +
                "Endereco: " + endereco + "\n" +
                "Telefone: " + telefone + "\n";
    }
}

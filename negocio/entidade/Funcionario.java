package negocio.entidade;

public class Funcionario extends Pessoa {
    private boolean isADM;

    public Funcionario (String nome, String cpf, int idade, String genero, String senha) {
        super(nome, cpf, idade, genero, senha);
    }

    public boolean isADM() {
        return isADM;
    }

    public void setADM(boolean ADM) {
        isADM = ADM;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\nCPF: " + getCpf() + "\nGenero: " + getGenero() + "\nIdade: " + getIdade() + "\nIs ADM: " + isADM + "\n------------------------";
    }
}
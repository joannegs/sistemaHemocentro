package negocio.entidade;

import java.util.Objects;

public class Pessoa implements Autenticavel {
    private String nome;
    private String cpf;
    private int idade;
    private String genero;
    private String senha;

    public Pessoa(String nome, String cpf, int idade, String genero, String senha) {
        setNome(nome);
        setCpf(cpf);
        setIdade(idade);
        setGenero(genero);
        setSenha(senha);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Pessoa(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return  Objects.equals(getCpf(), pessoa.getCpf());
    }

    @Override
    public boolean autenticar(String numIdentificador, String senha) {
        if(this.getCpf().equals(numIdentificador) && this.getSenha().equals(senha)){
            return true;
        } else  {
            return false;
        }
    }

}
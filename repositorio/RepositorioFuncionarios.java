package repositorio;

import negocio.entidade.*;
import negocio.entidade.Pessoa;

import java.util.ArrayList;
import java.util.Collections;

public class RepositorioFuncionarios implements ICrud{

    private ArrayList<Funcionario> funcionarios;
    private static RepositorioFuncionarios instancia;

    // construtor

	public RepositorioFuncionarios() {
		this.funcionarios = new ArrayList<Funcionario>();

		// inicializando o repositorio com funcionarios para testes

		Funcionario funcionario = new Funcionario("eddie", "eddie", 18,"Masculino", "eddie");
		Funcionario funcionario2 = new Funcionario("mike", "mike", 18,"Masculino", "mike");
		Funcionario adm = new Funcionario("adm", "adm", 18,"Masculino", "adm");
		adm.setADM(true);
		funcionarios.add(funcionario);
		funcionarios.add(funcionario2);
		funcionarios.add(adm);
	}

	// singleton

	public static RepositorioFuncionarios getInstance() {
		if(instancia == null) {
			instancia = new RepositorioFuncionarios();
		}
		return instancia;
	}

	// metodos

    public ArrayList<Funcionario> listarTodosOsFuncionarios(){
		ArrayList<Funcionario> funcionariosCopia = new ArrayList<Funcionario>(funcionarios);
		Collections.copy(funcionarios, funcionariosCopia);
		return funcionariosCopia;
    }

    public Funcionario buscarFuncionario(String cpf){
        Funcionario funcionario = null;
        for(int i = 0; i < funcionarios.size(); i++){
            if(funcionarios.get(i).getCpf().equals(cpf)){
                funcionario = funcionarios.get(i);
                break;
            }
        }
        return funcionario;
    }

    @Override
	public boolean checarExistencia(String cpf) {
		boolean cadastrado = false;
        for(int x = 0; x < this.listarTodosOsFuncionarios().size(); x++){
            if(listarTodosOsFuncionarios().get(x).getCpf().equals(cpf) == true){
                cadastrado = true;
                break;
            }
        }
        return cadastrado;
	}
  
	@Override
	public void remover(String cpf){
			for(int i = 0; i < funcionarios.size() ;i++) {
				if (funcionarios.get(i).getCpf().equalsIgnoreCase(cpf)) {
					funcionarios.remove(i);
					break;
				}
			}
	}
	
	@Override
	public void atualizarNome(String cpf, String novo_nome){
		for(Funcionario funcionario : funcionarios){
			if(funcionario.getCpf().equals(cpf)){funcionario.setNome(novo_nome);break;}
		}
	}

	@Override
	public void atualizarCPF(String cpf, String novo_cpf) {
		for(Funcionario funcionario : funcionarios){
			if(funcionario.getCpf().equals(cpf)){funcionario.setCpf(novo_cpf);break;}
		}
		
	}

	@Override
	public void atualizarGenero(String cpf)  {
		for(Funcionario funcionario : funcionarios){
			if(funcionario.getCpf().equals(cpf)) {
				if (funcionario.getGenero().equals("Feminino")) {
					funcionario.setGenero("Masculino");
				} else {
					funcionario.setGenero("Feminino");
				}
				break;
			}
		}
		
	}

	@Override
	public void atualizarSenha(String cpf, String nova_senha) {
		for(Funcionario funcionario : funcionarios){
			if(funcionario.getCpf().equals(cpf)){funcionario.setSenha(nova_senha);break;}
		}
		
	}

	@Override
	public void atualizarIdade(String cpf) {
		for(Funcionario funcionario : funcionarios){
			if(funcionario.getCpf().equals(cpf)){funcionario.setIdade(funcionario.getIdade() + 1);break;}
		}
	}


	@Override
	public void adicionar(Pessoa pessoa) {
		funcionarios.add((Funcionario) pessoa);
	}

}
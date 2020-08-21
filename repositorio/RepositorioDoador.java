package repositorio;

import negocio.entidade.Doador;
import negocio.entidade.FormularioDoacaoMedula;
import negocio.entidade.Pessoa;

import java.util.ArrayList;
import java.util.Collections;

public class RepositorioDoador implements ICrud {

	private ArrayList<Doador> doadores;
	private static RepositorioDoador instancia;

	// construtor

	private RepositorioDoador() {
		this.doadores = new ArrayList<Doador>();

		// inicializando doadores do sistema para teste

		Doador doador = new Doador("joanne", "joanne", 18, "joanne", "O+", "Feminino");
		Doador doador1 = new Doador("stan the man uris", "stan", 18, "stan", "A-", "Masculino");
		Doador doador2 = new Doador("bev", "bev", 18, "bev", "O+", "Feminino");
		doadores.add(doador);
		doadores.add(doador1);
		doadores.add(doador2);
	}

	// singleton

	public static RepositorioDoador getInstance() {
		if(instancia == null) {
			instancia = new RepositorioDoador();
		}
		return instancia;
	}

	// metodos

	public ArrayList<Doador> listarTodosOsDoadores() {
		ArrayList<Doador> doadoresCopia = new ArrayList<Doador>(doadores);
		Collections.copy(doadores, doadoresCopia);
		return doadoresCopia;
	}

	@Override
	public boolean checarExistencia(String cpf) {
		boolean cadastro = false;
		for(int x = 0; x < doadores.size() ;x++) {
			if(doadores.get(x).getCpf().equals(cpf)) {
				cadastro = true;
				break;
			}
		}
		return cadastro;
	}

	@Override
	public void remover(String cpf) {
		for(int x = 0; x < doadores.size() ;x++) {
			if (doadores.get(x).getCpf().equals(cpf)) {
				doadores.remove(x);
				break;
			}
		}
	}


	@Override
	public void atualizarNome(String cpf, String novo_nome){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)){doador.setNome(novo_nome);break;}
		}
	}

	@Override
	public void atualizarCPF(String cpf, String novo_cpf){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)){doador.setCpf(novo_cpf);break;}
		}
	}

	@Override
	public void atualizarGenero(String cpf){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)) {
				if (doador.getGenero().equals("Feminino")) {
					doador.setGenero("Masculino");
				} else {
					doador.setGenero("Feminino");
				}
				break;
			}
		}
	}

	public void atualizarTipoSanguineo(String cpf, String novoTipoSanguineo){
		for(Doador doador : doadores){
			if (doador.getCpf().equals(cpf)) {
				doador.setTipoSanguineo(novoTipoSanguineo);
				break;
			}
		}
	}

	@Override
	public void atualizarSenha(String cpf, String nova_senha){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)){doador.setSenha(nova_senha);break;}
		}
	}

	@Override
	public void atualizarIdade(String cpf){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)){doador.setIdade(doador.getIdade() + 1);break;}
		}
	}

	@Override
	public void adicionar(Pessoa pessoa){
		doadores.add((Doador) pessoa);
	}

	public Doador buscarDoador(String cpf){
		Doador doador = null;
		for(int i = 0; i < doadores.size(); i++){
			if(doadores.get(i).getCpf().equals(cpf)){
				doador = doadores.get(i);
				break;
			}
		}
		return doador;
	}



	public void atribuirFormularioDoacaoMedula(String cpfDoador, FormularioDoacaoMedula formulario){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpfDoador)){
				doador.setDoadorDeMedula(true);
				doador.setInfosDoacaoMedula(formulario);
			}
		}
	}

	public void apagarFormularioDoacaoDeMedula(String cpf){
		for(Doador doador : doadores){
			if(doador.getCpf().equals(cpf)){
				doador.setDoadorDeMedula(false);
				doador.setInfosDoacaoMedula(null);
			}
		}
	}









}
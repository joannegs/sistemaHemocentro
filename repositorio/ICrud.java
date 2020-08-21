package repositorio;

import negocio.entidade.Pessoa;


public interface ICrud {
	
	public void remover(String cpf);
	public void adicionar(Pessoa pessoa) ;
	public boolean checarExistencia(String cpf);
	public void atualizarNome(String cpf, String novo_nome);
	public void atualizarCPF(String cpf, String novo_cpf);
	public void atualizarGenero(String cpf);
	public void atualizarSenha(String cpf, String nova_senha);
	public void atualizarIdade(String cpf);
	
}

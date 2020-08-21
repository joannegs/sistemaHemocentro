package ui;
import negocio.entidade.Funcionario;
import negocio.entidade.Pessoa;

public class ControladorUsuarioLogado {

    private static Pessoa usuarioLogado;

    public  Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    public void alterarPessoaLogada(Pessoa userLogado){
        this.usuarioLogado = userLogado;
    }

    public void logout(){
        this.usuarioLogado = null;
    }

    public boolean isAdm(){
        Funcionario funcionario = (Funcionario) this.usuarioLogado;
        return funcionario.isADM();
    }
}

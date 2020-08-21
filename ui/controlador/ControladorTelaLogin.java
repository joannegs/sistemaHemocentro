package ui.controlador;

import ui.ControladorUsuarioLogado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import negocio.DoadorFachada;
import negocio.FuncionarioFachada;
import negocio.excecao.PessoaInexistenteException;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class ControladorTelaLogin {

    @FXML
    private TextField numIdentificador;

    @FXML
    private PasswordField senha;

    @FXML
    private Button login;

    @FXML
    private Pane painel;

    private DoadorFachada doadorFachada = new DoadorFachada();
    private FuncionarioFachada funcionarioFachada = new FuncionarioFachada();
    private ControladorUsuarioLogado controladorUsuarioLogado = new ControladorUsuarioLogado();


    public void abrirAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível fazer o login.");
        alert.setHeaderText("Usuário não cadastrado");
        alert.setTitle("Número identificador ou senha incorretos.");

        alert.show();
    }

    public void abrirAlertERROLogin() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível fazer o login.");
        alert.setHeaderText("Ocorreu um erro");
        alert.setTitle("Tente novamente");

        alert.show();
    }

    public void abrirAlertERROSenhaIncorreta() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível fazer o login.");
        alert.setHeaderText("Senha incorreta");
        alert.setTitle("A senha inserida não corresponde a cadastrada com o número identificador inserido");

        alert.show();
    }


    public void abrirTelaDeAcordoComOTipoDeUsuario() {
        if (this.controladorUsuarioLogado.getUsuarioLogado().getClass().getSimpleName().equals("Doador")) {
            try {
                Pane painelDoador = FXMLLoader.load(getClass().getResource("../visao/FXMLTelaUsuario.fxml"));
                painel.getChildren().setAll(painelDoador);
            } catch (IOException e) {
                abrirAlertERROLogin();
            }

        } else if (this.controladorUsuarioLogado.getUsuarioLogado().getClass().getSimpleName().equals("Funcionario")) {
            if (this.controladorUsuarioLogado.isAdm()) {
                try {
                    Pane painelDoador = FXMLLoader.load(getClass().getResource("../visao/FXMLTelaAdministrador.fxml"));
                    painel.getChildren().setAll(painelDoador);
                } catch (IOException e) {
                    abrirAlertERROLogin();
                }
            } else {
                try {
                    Pane painelDoador = FXMLLoader.load(getClass().getResource("../visao/FXMLTelaFuncionario.fxml"));
                    painel.getChildren().setAll(painelDoador);
                } catch (IOException e) {
                    abrirAlertERROLogin();
                }
            }
        }
    }


    @FXML public void logar() throws PessoaInexistenteException, IOException {
        try {
            if (doadorFachada.logar(this.numIdentificador.getText(), this.senha.getText())) {
                this.controladorUsuarioLogado.alterarPessoaLogada(funcionarioFachada.buscarDoador(this.numIdentificador.getText()));
                abrirTelaDeAcordoComOTipoDeUsuario();
            } else {
                abrirAlertERROSenhaIncorreta();
            }
        } catch (PessoaInexistenteException Edoador) {
            try {
                if (funcionarioFachada.logarFuncionario(this.numIdentificador.getText(), this.senha.getText())) {
                    this.controladorUsuarioLogado.alterarPessoaLogada(funcionarioFachada.buscarFuncionario(this.numIdentificador.getText()));
                    abrirTelaDeAcordoComOTipoDeUsuario();
                } else {
                    abrirAlertERROSenhaIncorreta();
                }
            } catch (PessoaInexistenteException Efuncionario) {
                abrirAlert();
                this.numIdentificador.setText("");
                this.senha.setText("");
            }
        }
    }
}

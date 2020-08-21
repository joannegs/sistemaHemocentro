package ui.controlador;

import ui.ControladorUsuarioLogado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import negocio.AdmFachada;
import negocio.entidade.Funcionario;
import negocio.entidade.ProcessamentoInformacoes;
import negocio.excecao.InformacoesNulasException;
import negocio.excecao.PessoaInexistenteException;
import negocio.excecao.PessoaJaExistenteException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaAdministrador implements Initializable {

    private AdmFachada admFachada = new AdmFachada();
    private ProcessamentoInformacoes processamentoInformacoes = new ProcessamentoInformacoes();
    private ControladorUsuarioLogado controladorUsuarioLogado = new ControladorUsuarioLogado();

    @FXML private ChoiceBox opc;
    @FXML private Button catchOpc;


    // atualização de cadastro de funcionários
    @FXML private TitledPane opcAtualizarCadastroFuncionarios;
    @FXML private ChoiceBox opcAtualizacaoInformacoesFuncionario;
    @FXML private TextField cpfChecagemFuncionario;
    @FXML private Button catchqualOpcAtualizarInformacao;

    @FXML private TitledPane opcNome;
    @FXML private TextField novoNome;
    @FXML private Button atualizarNome;

    @FXML private TitledPane opcCpf;
    @FXML private TextField novoCPF;
    @FXML private Button atualizarCpf;

    @FXML private TitledPane opcIdade;
    @FXML private Button atualizarIdade;

    @FXML private TitledPane opcSenha;
    @FXML private TextField novaSenha;
    @FXML private Button atualizarSenha;

    @FXML private TitledPane opcGenero;
    @FXML private Button atualizarGenero;

    // cadastro de funcionários
    @FXML private TitledPane opcContratarFuncionarios;
    @FXML private TextField nomeFuncionarioContratar;
    @FXML private TextField cpfFuncionarioContratar;
    @FXML private TextField idadeFuncionarioContratar;
    @FXML private TextField senhaFuncionarioContratar;
    @FXML private ChoiceBox generoFuncionarioContratar;
    @FXML private Button cadastrarFuncionario;

    // demição de funcionários
    @FXML private TitledPane opcDemitirFuncionarios;
    @FXML private TextField cpfFuncionarioDemitir;
    @FXML private Button apagarCadastroFuncionario;



    // listagem de funcionários
    @FXML private TitledPane opcListarFuncionarios;
    @FXML private TableView<Funcionario> tabelaFuncionarios;
    @FXML private TableColumn<Funcionario, String> nomeFuncionarioListagem;
    @FXML private TableColumn<Funcionario, String> cpfFuncionarioListagem;
    @FXML private TableColumn<Funcionario, String> idadeFuncionarioListagem;
    @FXML private TableColumn<Funcionario, String> generoFuncionarioListagem;

    // informações sobre doação sanguínea
    @FXML private TitledPane opcInformacoesSobreBancoDeSangue;
    @FXML private ChoiceBox opcVisualizacaoBancoDeSangue;

    @FXML private Button catchOpcVisualizacaoBancoDeSangue;

    @FXML private TitledPane opcTiposSanguineos;
    @FXML private TextField sangueA;
    @FXML private TextField sangueAP;
    @FXML private TextField sangueAN;
    @FXML private TextField sangueB;
    @FXML private TextField sangueBP;
    @FXML private TextField sangueBN;
    @FXML private TextField sangueAB;
    @FXML private TextField sangueABP;
    @FXML private TextField sangueABN;
    @FXML private TextField sangueO;
    @FXML private TextField sangueOP;
    @FXML private TextField sangueON;

    @FXML private TitledPane opcMulheres;
    @FXML private TextField msangueA;
    @FXML private TextField msangueAP;
    @FXML private TextField msangueAN;
    @FXML private TextField msangueB;
    @FXML private TextField msangueBP;
    @FXML private TextField msangueBN;
    @FXML private TextField msangueAB;
    @FXML private TextField msangueABP;
    @FXML private TextField msangueABN;
    @FXML private TextField msangueO;
    @FXML private TextField msangueOP;
    @FXML private TextField msangueON;


    @FXML private TitledPane opcHomens;
    @FXML private TextField hsangueA;
    @FXML private TextField hsangueAP;
    @FXML private TextField hsangueAN;
    @FXML private TextField hsangueB;
    @FXML private TextField hsangueBP;
    @FXML private TextField hsangueBN;
    @FXML private TextField hsangueAB;
    @FXML private TextField hsangueABP;
    @FXML private TextField hsangueABN;
    @FXML private TextField hsangueO;
    @FXML private TextField hsangueOP;
    @FXML private TextField hsangueON;



    @FXML private Pane painel;
    @FXML private Label sair;


    public void fecharPaneVisualizacaoBancoDeSangue(){
        if(this.opcTiposSanguineos.isVisible()){
            this.opcTiposSanguineos.setVisible(false);
        } else if(this.opcMulheres.isVisible()){
            this.opcMulheres.setVisible(false);
        } else if(this.opcHomens.isVisible()){
            this.opcHomens.setVisible(false);
        }
    }

    @FXML public void abrirPaneOpcVisualizacaoBancoDeSangue(){
        fecharPaneVisualizacaoBancoDeSangue();

        if(this.opcVisualizacaoBancoDeSangue.getValue().toString().equals("Tipos sanguineos")){
            this.opcTiposSanguineos.setVisible(true);
        } else if(this.opcVisualizacaoBancoDeSangue.getValue().toString().equals("Feminino")){
            this.opcMulheres.setVisible(true);
        } else if(this.opcVisualizacaoBancoDeSangue.getValue().toString().equals("Masculino")){
            this.opcHomens.setVisible(true);
        }
    }



    ArrayList<String> opcVisualizacaoDados= new ArrayList<String>() {
        {
            add("Tipos sanguineos");
            add("Feminino");
            add("Masculino");

        }
    };

    ArrayList<String> opcPrincipal = new ArrayList<String>() {
        {
            add("Atualizar cadastro de funcionários");
            add("Adicionar cadastros de funcionários");
            add("Apagar cadastro de funcionários");
            add("Listar funcionários");
            add("Visualizar informações sobre o banco de sangue");
        }
    };

    ArrayList<String> opcoesAlteracao = new ArrayList<String>() {
        {
            add("Nome");
            add("Idade");
            add("CPF");
            add("Genero");
            add("Senha");
        }
    };

    ArrayList<String> opcoesGenero = new ArrayList<String>() {
        {
            add("Masculino");
            add("Feminino");

        }
    };

    ObservableList<String> observableOpcPrincipal = FXCollections.observableArrayList(opcPrincipal);
    ObservableList<String> observableOpcAlteracao = FXCollections.observableArrayList(opcoesAlteracao);
    ObservableList<String> observableOpcGenero = FXCollections.observableArrayList(opcoesGenero);
    ObservableList<String> observableInformacoes = FXCollections.observableArrayList(opcVisualizacaoDados);


    private ObservableList<Funcionario> listaDeFuncionariosCadastrados() {
        return FXCollections.observableArrayList(
                admFachada.listarFuncionarios()
        );
    }


    public void abrirAlertERROLogout() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível fazer o logout.");
        alert.setHeaderText("Ocorreu um erro");
        alert.setTitle("Tente novamente");

        alert.show();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        opc.setItems(observableOpcPrincipal);
        opcAtualizacaoInformacoesFuncionario.setItems(observableOpcAlteracao);
        generoFuncionarioContratar.setItems(observableOpcGenero);
        opcVisualizacaoBancoDeSangue.setItems(observableInformacoes);

        nomeFuncionarioListagem.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfFuncionarioListagem.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        idadeFuncionarioListagem.setCellValueFactory(new PropertyValueFactory<>("idade"));
        generoFuncionarioListagem.setCellValueFactory(new PropertyValueFactory<>("genero"));

        tabelaFuncionarios.setItems(listaDeFuncionariosCadastrados());


        this.sangueA.setText(String.valueOf(this.admFachada.sangueA()));
        this.sangueAP.setText(String.valueOf(this.admFachada.sangueAP()));
        this.sangueAN.setText(String.valueOf(this.admFachada.sangueAN()));

        this.sangueB.setText(String.valueOf(this.admFachada.sangueB()));
        this.sangueBP.setText(String.valueOf(this.admFachada.sangueBP()));
        this.sangueBN.setText(String.valueOf(this.admFachada.sangueBN()));

        this.sangueAB.setText(String.valueOf(this.admFachada.sangueAB()));
        this.sangueABP.setText(String.valueOf(this.admFachada.sangueABP()));
        this.sangueABN.setText(String.valueOf(this.admFachada.sangueABN()));

        this.sangueO.setText(String.valueOf(this.admFachada.sangueO()));
        this.sangueOP.setText(String.valueOf(this.admFachada.sangueOP()));
        this.sangueON.setText(String.valueOf(this.admFachada.sangueON()));


        this.msangueA.setText(String.valueOf(this.admFachada.doadoresMulheresA()));
        this.msangueAP.setText(String.valueOf(this.admFachada.doadoresMulheresAP()));
        this.msangueAN.setText(String.valueOf(this.admFachada.doadoresMulheresAN()));

        this.msangueB.setText(String.valueOf(this.admFachada.doadoresMulheresB()));
        this.msangueBP.setText(String.valueOf(this.admFachada.doadoresMulheresBP()));
        this.msangueBN.setText(String.valueOf(this.admFachada.doadoresMulheresBN()));

        this.msangueAB.setText(String.valueOf(this.admFachada.doadoresMulheresAB()));
        this.msangueABP.setText(String.valueOf(this.admFachada.doadoresMulheresABP()));
        this.msangueABN.setText(String.valueOf(this.admFachada.doadoresMulheresABN()));

        this.msangueO.setText(String.valueOf(this.admFachada.doadoresMulheresO()));
        this.msangueOP.setText(String.valueOf(this.admFachada.doadoresMulheresOP()));
        this.msangueON.setText(String.valueOf(this.admFachada.doadoresMulheresON()));

        this.hsangueA.setText(String.valueOf(this.admFachada.doadoresHomensA()));
        this.hsangueAP.setText(String.valueOf(this.admFachada.doadoresHomensAP()));
        this.hsangueAN.setText(String.valueOf(this.admFachada.doadoresHomensAN()));

        this.hsangueB.setText(String.valueOf(this.admFachada.doadoresHomensB()));
        this.hsangueBP.setText(String.valueOf(this.admFachada.doadoresHomensBP()));
        this.hsangueBN.setText(String.valueOf(this.admFachada.doadoresHomensBN()));

        this.hsangueAB.setText(String.valueOf(this.admFachada.doadoresHomensAB()));
        this.hsangueABP.setText(String.valueOf(this.admFachada.doadoresHomensABP()));
        this.hsangueABN.setText(String.valueOf(this.admFachada.doadoresHomensABN()));

        this.hsangueO.setText(String.valueOf(this.admFachada.doadoresHomensO()));
        this.hsangueOP.setText(String.valueOf(this.admFachada.doadoresHomensOP()));
        this.hsangueON.setText(String.valueOf(this.admFachada.doadoresHomensON()));




    }


    public void fecharOpcPrincipal(){
        if(this.opcAtualizarCadastroFuncionarios.isVisible()){
            this.opcAtualizarCadastroFuncionarios.setVisible(false);
        } else if (this.opcContratarFuncionarios.isVisible()){
            this.opcContratarFuncionarios.setVisible(false);
        } else if(this.opcDemitirFuncionarios.isVisible()){
            this.opcDemitirFuncionarios.setVisible(false);
        } else if(this.opcListarFuncionarios.isVisible()){
            this.opcListarFuncionarios.setVisible(false);
        } else if(this.opcInformacoesSobreBancoDeSangue.isVisible()){
            this.opcInformacoesSobreBancoDeSangue.setVisible(false);
        }
    }


    public void abrirPaneEscolhido(){
        fecharOpcPrincipal();

        if(this.opc.getValue().toString().equals("Atualizar cadastro de funcionários")){
            this.opcAtualizarCadastroFuncionarios.setVisible(true);
        } else if(this.opc.getValue().toString().equals("Adicionar cadastros de funcionários")){
            this.opcContratarFuncionarios.setVisible(true);
        } else if(this.opc.getValue().toString().equals("Apagar cadastro de funcionários")){
            this.opcDemitirFuncionarios.setVisible(true);
        } else if(this.opc.getValue().toString().equals("Listar funcionários")){
            this.opcListarFuncionarios.setVisible(true);
        } else if(this.opc.getValue().toString().equals("Visualizar informações sobre o banco de sangue")){
            this.opcInformacoesSobreBancoDeSangue.setVisible(true);
        }
    }

    public void fecharOpcAlteracao(){
        if(this.opcNome.isVisible()){
            this.opcNome.setVisible(false);
        }else if(this.opcIdade.isVisible()){
            this.opcIdade.setVisible(false);
        } else if(this.opcCpf.isVisible()){
            this.opcCpf.setVisible(false);
        } else if(this.opcGenero.isVisible()){
            this.opcGenero.setVisible(false);
        } else if(this.opcSenha.isVisible() == true){
            this.opcSenha.setVisible(false);
        }
    }


    public void abrirPaneEscolhidoAlteracao(){
        fecharOpcAlteracao();

        if(this.opcAtualizacaoInformacoesFuncionario.getValue().toString().equals("Nome")){
            this.opcNome.setVisible(true);
        } else if(this.opcAtualizacaoInformacoesFuncionario.getValue().toString().equals("Idade")){
            this.opcIdade.setVisible(true);
        } else if(this.opcAtualizacaoInformacoesFuncionario.getValue().toString().equals("CPF")){
            this.opcCpf.setVisible(true);
        } else if(this.opcAtualizacaoInformacoesFuncionario.getValue().toString().equals("Genero")) {
            this.opcGenero.setVisible(true);
        } else if(this.opcAtualizacaoInformacoesFuncionario.getValue().toString().equals("Senha")){
            this.opcSenha.setVisible(true);
        }
    }

    public void abrirAlertERROAtualizacao(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("O número identificador inserido não pertence a nenhum usuário cadaastrado.");
        alert.setHeaderText("Funcionário não cadastrado");
        alert.setTitle("Não foi possível atualizar a informação");

        alert.show();
    }

    public void abrirAlertERROApagarCadastro(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("O número identificador inserido não pertence a nenhum usuário cadastrado");
        alert.setHeaderText("Funcionário não cadastrado");
        alert.setTitle("O número identificador inserido não pertence a nenhum usuário cadaastrado.");

        alert.show();
    }

    public void abrirAlertERROFormatoDeIdadeInadequado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível cadastrar o funcionário");
        alert.setHeaderText("Dados inválidos");
        alert.setTitle("Insira uma idade válida");

        alert.show();
    }

    public void abrirAlertERROFuncionarioJaCadastrado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível cadastrar o funcionário");
        alert.setHeaderText("Funcionário já existe");
        // alert.setTitle("");

        alert.show();
    }

    public void abrirAlertCONFIRMATION(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Ação realizada");
        alert.show();
    }

    public void abrirAlertERROInformacoesVazias(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possivel fazer o cadastro");
        alert.setHeaderText("Campos vazios");
        alert.setContentText("Há informações que não podem estar em branco no formulário");
        alert.show();
    }


    public void atualizarNomeFuncionario(){
        try {
            admFachada.atualizarNomeFuncionario(this.cpfChecagemFuncionario.getText(), this.novoNome.getText());
            this.cpfChecagemFuncionario.setText("");
            this.novoNome.setText("");
            abrirAlertCONFIRMATION();

        } catch (PessoaInexistenteException e){
            abrirAlertERROAtualizacao();
            this.cpfChecagemFuncionario.setText("");
            this.novoNome.setText("");
        }
    }


    public void atualizarCpfFuncionario(){
        try {
            admFachada.atualizarCpfFuncionario(this.cpfChecagemFuncionario.getText(), this.novoCPF.getText());
            this.cpfChecagemFuncionario.setText("");
            this.novoCPF.setText("");
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e){
            abrirAlertERROAtualizacao();
            this.cpfChecagemFuncionario.setText("");
            this.novoCPF.setText("");
        }
    }

    public void atualizarIdadeFuncionario(){
        try {
            admFachada.atualizarIdadeFuncionario(this.cpfChecagemFuncionario.getText());
            this.cpfChecagemFuncionario.setText("");
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e){
            abrirAlertERROAtualizacao();
            this.cpfChecagemFuncionario.setText("");
        }
    }

    public void atualizarGeneroFuncionario(){
        try {
            admFachada.atualizarGeneroFuncionario(this.cpfChecagemFuncionario.getText());
            this.cpfChecagemFuncionario.setText("");
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e){
            abrirAlertERROAtualizacao();
            this.cpfChecagemFuncionario.setText("");
        }
    }

    public void atualizarSenhaFuncionario(){
        try {
            admFachada.atualizarSenhaFuncionario(this.cpfChecagemFuncionario.getText(), this.novaSenha.getText());
            this.cpfChecagemFuncionario.setText("");
            this.novaSenha.setText("");
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e){
            abrirAlertERROAtualizacao();
            this.cpfChecagemFuncionario.setText("");
            this.novaSenha.setText("");
        }
    }


    public void cadastrarFuncionario(){
        try {
                int idadeFuncionario = Integer.parseInt(this.idadeFuncionarioContratar.getText());
                Funcionario funcionario = new Funcionario(this.nomeFuncionarioContratar.getText(), this.cpfFuncionarioContratar.getText(), idadeFuncionario, this.generoFuncionarioContratar.getValue().toString(), this.senhaFuncionarioContratar.getText());
                admFachada.contratarFuncionario(funcionario);
                abrirAlertCONFIRMATION();
                this.nomeFuncionarioContratar.setText("");
                this.cpfFuncionarioContratar.setText("");
                this.idadeFuncionarioContratar.setText("");
                this.senhaFuncionarioContratar.setText("");
                this.generoFuncionarioContratar.setValue("");
                fecharOpcPrincipal();

            } catch (NumberFormatException e){
                abrirAlertERROFormatoDeIdadeInadequado();
        } catch (PessoaJaExistenteException e){
            abrirAlertERROFuncionarioJaCadastrado();
        }  catch (InformacoesNulasException e2){
            abrirAlertERROInformacoesVazias();
        }
    }


    public void apagarCadastroFuncionario(){
        try{
            admFachada.demitirFuncionario(this.cpfFuncionarioDemitir.getText());
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e){
            abrirAlertERROApagarCadastro();
        }
    }

    @FXML public void sair() throws IOException {
        try{
            this.controladorUsuarioLogado.logout();
            Pane painelDoador = FXMLLoader.load(getClass().getResource("../visao/FXMLTelaLogin.fxml"));
            painel.getChildren().setAll(painelDoador);
        } catch (IOException e) {
            abrirAlertERROLogout();
        }
    }
}

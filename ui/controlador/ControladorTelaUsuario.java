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
import negocio.DoadorFachada;
import negocio.entidade.Doador;
import negocio.entidade.FichaMedica;
import negocio.excecao.AgendamentoDeDoacaoJaExistenteException;
import negocio.excecao.DataInvalidaException;
import negocio.excecao.PessoaInexistenteException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaUsuario implements Initializable {

    private DoadorFachada doadorFachada = new DoadorFachada();

    private ControladorUsuarioLogado controladorUsuarioLogado = new ControladorUsuarioLogado();

    private Doador doador;


    // inicial
    @FXML private ChoiceBox opc;
    @FXML private Button catchOpc;

    // atualização de informações de cadastro
    @FXML private TitledPane opcAtualizarInformacoesDeCadastro;
    @FXML private ChoiceBox opcQualAtualizarInformacoesCadastro;
    @FXML private Button catchOpcAtualizarInformacoesCadastro;

    @FXML private TextField cpfConfirmacao;

    @FXML private TitledPane opcNome;
    @FXML private TextField novoNome;
    @FXML private Button atualizarNome;

    @FXML private TitledPane opcCpf;
    @FXML private TextField novoCPF;
    @FXML
    private Button atualizarCpf;

    @FXML
    private TitledPane opcIdade;
    @FXML
    private Button atualizarIdade;

    @FXML
    private TitledPane opcSenha;
    @FXML
    private TextField novaSenha;
    @FXML
    private Button atualizarSenha;

    @FXML private TitledPane opcGenero;
    @FXML private Button atualizarGenero;

    @FXML private TitledPane opcTipoSanguineo;
    @FXML private ChoiceBox tipoSanguineo;
    @FXML private Button atualizarTipoSanguineo;


    // agendamento de doação
    @FXML private TitledPane opcAgendamentoDeDoacao;
    @FXML private DatePicker dataAgendamento;
    @FXML private TextField cpfAgendamento;
    @FXML private Button agendarDoacao;


    // visualização de fichas medicas
    @FXML private TitledPane opcVisualizarFichasMedicas;

    @FXML private TableView<FichaMedica> tabelaVisualizacaoDeFichasMedicas;
    @FXML private TableColumn<FichaMedica, String> hemoglobina;
    @FXML private TableColumn<FichaMedica, Float> pressaoArterial;
    @FXML private TableColumn<FichaMedica, Float> temperaturaCorporal;
    @FXML private TableColumn<FichaMedica, Float> peso;
    @FXML private TableColumn<FichaMedica, Float> altura;
    @FXML private TableColumn<FichaMedica, String> pulso;
    @FXML private TableColumn<FichaMedica, Float> volumeDoSangue;

    @FXML private Pane painel;

    @FXML private Label sair;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opc.setItems(observableOpcPrincipal);
        tipoSanguineo.setItems(observableTiposSanguineos);
        opcQualAtualizarInformacoesCadastro.setItems(observableOpcoesAlteracao);

        this.doador = (Doador) controladorUsuarioLogado.getUsuarioLogado();


        hemoglobina.setCellValueFactory(new PropertyValueFactory<>("hemoglobina"));
        pressaoArterial.setCellValueFactory(new PropertyValueFactory<>("pressaoArterial"));
        temperaturaCorporal.setCellValueFactory(new PropertyValueFactory<>("temperaturaCorporal"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        altura.setCellValueFactory(new PropertyValueFactory<>("altura"));
        pulso.setCellValueFactory(new PropertyValueFactory<>("pulso"));
        volumeDoSangue.setCellValueFactory(new PropertyValueFactory<>("volumeDeSangue"));


        tabelaVisualizacaoDeFichasMedicas.setItems(listaDeFichas());

    }

    private ObservableList<FichaMedica> listaDeFichas() {
        try{
            return FXCollections.observableArrayList(
                    doadorFachada.visualizarFichasMedicas(this.doador.getCpf()));
        } catch (PessoaInexistenteException e){

        }

        return null;
    }

    public void abrirAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("");
        alert.setContentText("Não foi possível alterar a informação");

        alert.show();
    }

    public void abrirAlertERROLogout() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível fazer o logout.");
        alert.setHeaderText("Ocorreu um erro");
        alert.setTitle("Tente novamente");

        alert.show();
    }

    public void abrirAlertERRODataInvalida() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("A data escolhida já tem o máximo de agendamentos permitidos.");
        alert.setHeaderText("Não foi possível agendar a doação");
        alert.setTitle("Limite de agendamentos excedido");

        alert.show();
    }

    public void abrirAlertERROAgendamentoJaExiste() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possível agendar a doação");
        alert.setHeaderText("Agendamento já cadastrado");

        alert.show();
    }

    public void abrirAlertPessoaInexistente() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pessoa não Existe");
        alert.setHeaderText("Pessoa com esse CPF não está cadastrada");
        alert.setContentText("Tente Novamente com outro Cpf");

        alert.show();
    }


    public void abrirAlertCONFIRMATION(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Ação realizada");
        alert.show();
    }


    ArrayList<String> opcPrincipal = new ArrayList<String>() {
        {
            add("Atualizar informações de cadastro");
            add("Agendar doação");
            add("Visualizar fichas medicas");
        }
    };


    ArrayList<String> opcoesAlteracao = new ArrayList<String>() {
        {
            add("Nome");
            add("Idade");
            add("CPF");
            add("Genero");
            add("Tipo sanguineo");
            add("Senha");
        }
    };


    ArrayList<String> tiposSanguineos = new ArrayList<String>() {
        {
            add("A+");
            add("A-");
            add("B+");
            add("B-");
            add("AB+");
            add("AB-");
            add("O+");
            add("O-");
        }
    };

    ObservableList<String> observableOpcPrincipal = FXCollections.observableList(opcPrincipal);
    ObservableList<String> observableTiposSanguineos = FXCollections.observableList(tiposSanguineos);
    ObservableList<String> observableOpcoesAlteracao = FXCollections.observableList(opcoesAlteracao);


    public void fecharOpcPrincipal() {
        if (this.opcAtualizarInformacoesDeCadastro.isVisible()) {
            this.opcAtualizarInformacoesDeCadastro.setVisible(false);
        } else if (this.opcAgendamentoDeDoacao.isVisible()) {
            this.opcAgendamentoDeDoacao.setVisible(false);
        } else if (this.opcVisualizarFichasMedicas.isVisible()) {
            this.opcVisualizarFichasMedicas.setVisible(false);
        }
    }


    @FXML
    public void abrirPaneEscolhido() {
        fecharOpcPrincipal();

        if (this.opc.getValue().toString().equals("Atualizar informações de cadastro")) {
            this.opcAtualizarInformacoesDeCadastro.setVisible(true);
        } else if (this.opc.getValue().toString().equals("Agendar doação")) {
            this.opcAgendamentoDeDoacao.setVisible(true);
        } else if (this.opc.getValue().toString().equals("Visualizar fichas medicas")) {
            this.opcVisualizarFichasMedicas.setVisible(true);
        }
    }


    public void fecharOpcAlteracao() {
        if (this.opcNome.isVisible()) {
            this.opcNome.setVisible(false);
        } else if (this.opcIdade.isVisible()) {
            this.opcIdade.setVisible(false);
        } else if (this.opcCpf.isVisible()) {
            this.opcCpf.setVisible(false);
        } else if (this.opcGenero.isVisible()) {
            this.opcGenero.setVisible(false);
        } else if (this.opcTipoSanguineo.isVisible() == true) {
            this.opcTipoSanguineo.setVisible(false);
        } else if (this.opcSenha.isVisible() == true) {
            this.opcSenha.setVisible(false);
        }
    }


    @FXML
    public void abrirPaneEscolhidoAlteracao() {
        fecharOpcAlteracao();

        if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("Nome")) {
            this.opcNome.setVisible(true);
        } else if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("Idade")) {
            this.opcIdade.setVisible(true);
        } else if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("CPF")) {
            this.opcCpf.setVisible(true);
        } else if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("Genero")) {
            this.opcGenero.setVisible(true);
        } else if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("Tipo sanguineo")) {
            this.opcTipoSanguineo.setVisible(true);
        } else if (this.opcQualAtualizarInformacoesCadastro.getValue().toString().equals("Senha")) {
            this.opcSenha.setVisible(true);
        }
    }


    @FXML public void atualizarNomeUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atualizarNome(this.doador.getCpf(), this.novoCPF.getText());
            abrirAlertCONFIRMATION();
            this.opcNome.setVisible(false);
            this.novoNome.setText("");
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }

    @FXML
    public void atualizarCpfUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atualizarCpf(this.doador.getCpf(), this.novoCPF.getText());
            abrirAlertCONFIRMATION();
            this.opcCpf.setVisible(false);
            this.novoCPF.setText("");
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }

    @FXML
    public void atualizarIdadeUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atuzalizarIdade(this.doador.getCpf());
            abrirAlertCONFIRMATION();
            this.opcIdade.setVisible(false);
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }


    @FXML
    public void atualizarSenhaUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atualizarSenha(this.doador.getCpf(), this.novaSenha.getText());
            abrirAlertCONFIRMATION();
            this.opcSenha.setVisible(false);
            this.novaSenha.setText("");
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }

    @FXML
    public void atualizarGeneroUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atualizarGenero(this.doador.getCpf());
            abrirAlertCONFIRMATION();
            this.opcGenero.setVisible(false);
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }

    @FXML
    public void atualizarTipoSanguineoUsuario() throws PessoaInexistenteException {
        try {
            doadorFachada.atualizarTipoSanguineo(this.doador.getCpf(), this.tipoSanguineo.getValue().toString());
            abrirAlertCONFIRMATION();
            this.opcTipoSanguineo.setVisible(false);
            this.tipoSanguineo.setValue(null);
        } catch (PessoaInexistenteException e) {
            abrirAlert();
        }
    }

    @FXML public void agendarDoacao() {
        try {
            this.doadorFachada.agendarDoacao(this.doador.getCpf(), this.dataAgendamento.getValue());
            abrirAlertCONFIRMATION();
            this.dataAgendamento.setValue(null);
            this.opcAgendamentoDeDoacao.setVisible(false);
        } catch (PessoaInexistenteException e1) {
            abrirAlertPessoaInexistente();
        } catch (DataInvalidaException e2) {
            abrirAlertERRODataInvalida();
        } catch (AgendamentoDeDoacaoJaExistenteException e3) {
            abrirAlertERROAgendamentoJaExiste();
        }
    }

    @FXML
    public void sair() throws IOException {
        try {
            this.controladorUsuarioLogado.logout();
            Pane painelDoador = FXMLLoader.load(getClass().getResource("../visao/FXMLTelaLogin.fxml"));
            painel.getChildren().setAll(painelDoador);
        } catch (IOException e) {
            abrirAlertERROLogout();
        }
    }

}
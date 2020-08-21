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
import negocio.excecao.*;
import negocio.FuncionarioFachada;
import negocio.entidade.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorTelaFuncionario implements Initializable {
    /**
     * CD -> cadastro doador
     * DM -> doação de medula
     * CMD -> cadastro de doação de medula
     * FA/FO -> Funcionario array/Funcionario ObservableList
     * GA/GO -> genero arraylis/Genero ObservableList
     * TSA/TSO -> tipo sanguineo arrayList/ObservableList
     * RA/RO -> raca arrayList/ObservableList
     * DDC -> dado cadastrado
     * AFDM -> atribuição de formulário de doador de medula
     */


    private FuncionarioFachada faxadaFuncionario = new FuncionarioFachada();

    //opções para o funcionario escolher
    @FXML private ChoiceBox<String> opcFuncionario;
    @FXML private Button catchOpcFunc;

    //telas para opções escolhidas

    //tela atualizar cadastro de doaddor
    @FXML private TitledPane paneAtualizarCD;
    @FXML private ChoiceBox<String> opcAtualizarDadosDoador;
    @FXML private Button catchAtualizarDadoDoador;
    @FXML private TextField txtCpfVerificar;

    @FXML private TitledPane paneAttNome;
    @FXML private TextField txtNovoNome;
    @FXML private Button catchAttNome;

    @FXML private TitledPane paneAttCpf;
    @FXML private TextField txtNovoCpf;
    @FXML private Button catchAttCpf;

    @FXML private TitledPane paneAttSenha;
    @FXML private TextField txtNovaSenha;
    @FXML private Button catchAttSenha;

    @FXML private TitledPane paneAttTipoSanguineo;
    @FXML private Button catchAttTipoSanguineo;
    @FXML private ChoiceBox<String> opcAttTipoSanguineo;

    @FXML private Button catchAttIdade;
    @FXML private Button catchAttGenero;



    // tratamento para tela de cadastro de doador
    @FXML private TitledPane paneCadastroDoador;
    @FXML private ChoiceBox<String> opcTipoSanguineo;
    @FXML private ChoiceBox<String> opcGenero;
    @FXML private ToggleGroup groupDoadorMedula;
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField intIdade;
    @FXML private PasswordField txtSenha;
    @FXML private Button butoncadastrarDoador;

    //pane de formulario de doação de medula
    @FXML private TitledPane paneFormularioDM;
    @FXML private ChoiceBox<String> opcRaca;
    @FXML private TextField txtNacionalidade;
    @FXML private TextField txtNomeMae;
    @FXML private TextField txtNomePai;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtTelefone;
    @FXML private Button catchFormularioDM;


    //tratamento para remover doador
    @FXML private TitledPane paneApagarCadastroDoador;
    @FXML private Button removerDoador;
    @FXML private TextField txtCpfApagar;

    //tratamento para armazenar doação
    @FXML private TitledPane paneArmazenarDoacao;
    @FXML private DatePicker dateDoacao;
    @FXML private TextField txtCpfDoacao;
    @FXML private TitledPane paneFormularioDoacao;
    @FXML private ToggleGroup groupFumante;
    @FXML private ToggleGroup groupLactante;
    @FXML private ToggleGroup groupAlcoolatra;
    @FXML private ToggleGroup groupGestante;
    @FXML private ToggleGroup groupDiabetico;

    @FXML private Label aviso;

    //tratamento para atribuiçao de fichaa médica
    @FXML private TitledPane paneAtribuirFichaMedica;
    @FXML private DatePicker dateVerificarDoacao;
    @FXML private TextField txtCPFVerificarDoacao;
    @FXML private Button catchVerificarDoacao;

    @FXML private TitledPane paneFichaMédica;
    @FXML private Button catchFichaMedica;
    @FXML private TextField txtHemoglibina;
    @FXML private TextField txtTemperaturaCorporal;
    @FXML private TextField txtNumeroTubo;
    @FXML private TextField txtVolumeSangue;
    @FXML private TextField txtPeso;
    @FXML private TextField txtPulso;
    @FXML private TextField txtAltura;
    @FXML private TextField txtPressaoArterial;

    //tratamento para removerCDM
    @FXML private TitledPane paneApagarCDM;
    @FXML private TextField txtCpfRemoverCDM;

    //tratamento paraatribuição de doação de medula
    @FXML private TitledPane paneAtribuirFormularioMedula;
    @FXML private TextField txtCpfAFDM;
    @FXML private TitledPane paneFormularioDM1;
    @FXML private TextField txtNacionalidade1;
    @FXML private TextField txtNomeMae1;
    @FXML private TextField txtNomePai1;
    @FXML private TextField txtEndereco1;
    @FXML private TextField txtTelefone1;
    @FXML private ChoiceBox<String> opcRaca1;

    @FXML private TitledPane paneAgendarDoacao;
    @FXML private TitledPane paneExibirFichasMedicas;
    @FXML private TitledPane paneExibirDoadores;
    @FXML private TitledPane paneExibirDoacoes;
    @FXML private TitledPane paneExibirAgendamentos;


    // agendamento de doação
    @FXML private DatePicker dataAgendamento;
    @FXML private TextField cpfDoadorAgendamento;
    @FXML private Button agendarDoacao;


    // listagem de doadores
    @FXML private TableView<Doador> tabelaDoadores;
    @FXML private TableColumn<Doador, String> nomeDoadorListagem;
    @FXML private TableColumn<Doador, String> cpfDoadorListagem;
    @FXML private TableColumn<Doador, Integer> idadeDoadorListagem;
    @FXML private TableColumn<Doador, String> generoDoadorListagem;
    @FXML private TableColumn<Doador, String> tipoSanguineoDoadorListagem;

    // listagem de doações sanguíneas
    @FXML private TableView<DoacaoSanguinea> tabelaDoacoes;
    @FXML private TableColumn<DoacaoSanguinea, String> cpfDoadorListagemDoacoesSanguineas;
    @FXML private TableColumn<DoacaoSanguinea, LocalDate> dataDoacaoListagemDoacoesSanguineas;

    // listagem de agendamentos
    @FXML private TableView<AgendamentoDoacao> tabelaAgendamentos;
    @FXML private TableColumn<AgendamentoDoacao, String> cpfDoadorListagemAgendamentos;
    @FXML private TableColumn<AgendamentoDoacao, LocalDate> dataAgendamentoListagemAgendamentos;

    // listagem fichas medicas
    @FXML private TableView<FichaMedica> tabelaFichasMedicas;
    // @FXML private TableColumn<FichaMedica, String> cpfDoadorListagemFichasMedicas;
    @FXML private TableColumn<FichaMedica, String> hemoglobinaListagemFichas;
    @FXML private TableColumn<FichaMedica, Float> pressaoArterialListagemFichas;
    @FXML private TableColumn<FichaMedica, Float> temperaturaCorporalListagemFichas;
    @FXML private TableColumn<FichaMedica, Float> pesoListagemFichas;
    @FXML private TableColumn<FichaMedica, Float> alturaListagemFichas;
    @FXML private TableColumn<FichaMedica, String> pulsoListagemFichas;
    @FXML private TableColumn<FichaMedica, Float> volumeDoSangueListagemFichas;

    @FXML private Pane painel;
    @FXML private Label sair;

    private ControladorUsuarioLogado controladorUsuarioLogado = new ControladorUsuarioLogado();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        opcFuncionario.setItems(opcFO);
        opcGenero.setItems(opcGO);
        opcTipoSanguineo.setItems(opcTSO);
        opcRaca.setItems(opcRO);
        opcRaca1.setItems(opcRO);
        opcAtualizarDadosDoador.setItems(opcAtualizarDDO);
        opcAttTipoSanguineo.setItems(opcTSO);

        // listagem de doadores
        nomeDoadorListagem.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfDoadorListagem.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        idadeDoadorListagem.setCellValueFactory(new PropertyValueFactory<>("idade"));
        generoDoadorListagem.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tipoSanguineoDoadorListagem.setCellValueFactory(new PropertyValueFactory<>("tipoSanguineo"));

        tabelaDoadores.setItems(listaDeDoadoresCadastrados());

        // listagem de doações
        cpfDoadorListagemDoacoesSanguineas.setCellValueFactory(new PropertyValueFactory<>("doador"));
        dataDoacaoListagemDoacoesSanguineas.setCellValueFactory(new PropertyValueFactory<>("data"));

        tabelaDoacoes.setItems(listagemDoacaoes());

        // listagem agendamentos
        cpfDoadorListagemAgendamentos.setCellValueFactory(new PropertyValueFactory<>("doador"));
        dataAgendamentoListagemAgendamentos.setCellValueFactory(new PropertyValueFactory<>("data"));

        tabelaAgendamentos.setItems(listagemAgendamentos());

        // listagem fichas medicas
        // cpfDoadorListagemFichasMedicas.setCellValueFactory(new PropertyValueFactory<>("doador"));
        hemoglobinaListagemFichas.setCellValueFactory(new PropertyValueFactory<>("hemoglobina"));
        pressaoArterialListagemFichas.setCellValueFactory(new PropertyValueFactory<>("pressaoArterial"));
        temperaturaCorporalListagemFichas.setCellValueFactory(new PropertyValueFactory<>("temperaturaCorporal"));
        pesoListagemFichas.setCellValueFactory(new PropertyValueFactory<>("peso"));
        alturaListagemFichas.setCellValueFactory(new PropertyValueFactory<>("altura"));
        pulsoListagemFichas.setCellValueFactory(new PropertyValueFactory<>("pulso"));
        volumeDoSangueListagemFichas.setCellValueFactory(new PropertyValueFactory<>("volumeDeSangue"));

        tabelaFichasMedicas.setItems(listagemFichas());
    }

    private ObservableList<Doador> listaDeDoadoresCadastrados() {
        return FXCollections.observableArrayList(
                faxadaFuncionario.listarDoadores()
        );
    }

    private ObservableList<DoacaoSanguinea> listagemDoacaoes() {
        return FXCollections.observableArrayList(
                faxadaFuncionario.listarDoacoes()
        );
    }

    private ObservableList<AgendamentoDoacao> listagemAgendamentos(){
        return FXCollections.observableArrayList(
                faxadaFuncionario.listarAgendamentos()
        );
    }


    private ObservableList<FichaMedica> listagemFichas(){
        return FXCollections.observableArrayList(
                faxadaFuncionario.listarFichas()
        );
    }



    ArrayList<String> opcAtualizarDDA = new ArrayList<>();{
        {
            opcAtualizarDDA.add("Nome");
            opcAtualizarDDA.add("Idade");
            opcAtualizarDDA.add("CPF");
            opcAtualizarDDA.add("Genero");
            opcAtualizarDDA.add("Senha");
            opcAtualizarDDA.add("Tipo Sanguineo");
        }
    }
    ArrayList<String> opcFA = new ArrayList<>();{ {
        opcFA.add("Cadastrar Doador");
        opcFA.add("Apagar cadastro de doador");
        opcFA.add("Editar Dados de Doador");
        opcFA.add("Armazenar Doação");
        opcFA.add("Atribuir Ficha Médica");
        opcFA.add("Atribuir Formulario de Doação de Medula");
        opcFA.add("Remover Cadastro de Doação de Medula");
        opcFA.add("Agendar doacao");
        opcFA.add("Listar doadores");
        opcFA.add("Listar doacoes");
        opcFA.add("Listar Fichas medicas");
        opcFA.add("Listar agendamentos de doacao");

    } }
    ArrayList<String> opcGA = new ArrayList<>();{
        {
            opcGA.add("Masculino");
            opcGA.add("Feminino");
            opcGA.add("Nenhum");

        }
    }
    ArrayList<String> opcTSA = new ArrayList<>();{
        {
            opcTSA.add("A+");
            opcTSA.add("A-");
            opcTSA.add("B+");
            opcTSA.add("B-");
            opcTSA.add("AB+");
            opcTSA.add("AB-");
            opcTSA.add("O-");
            opcTSA.add("O+");

        }
    }
    ArrayList<String> opcRA = new ArrayList<>();{
        {
            opcRA.add("Negro");
            opcRA.add("Branco");
            opcRA.add("Pardo");
            opcRA.add("Nenhum");

        }
    }


    ObservableList<String> opcAtualizarDDO = FXCollections.observableArrayList(opcAtualizarDDA);
    ObservableList<String> opcFO = FXCollections.observableArrayList(opcFA);
    ObservableList<String> opcGO = FXCollections.observableArrayList(opcGA);
    ObservableList<String> opcTSO = FXCollections.observableArrayList(opcTSA);
    ObservableList<String> opcRO = FXCollections.observableArrayList(opcRA);


    // Telas com opções do funcionario
    @FXML void abrirOpcEscolhida() {
        fecharOpcPrincipal();
        if(opcFuncionario.getValue().equals("Cadastrar Doador")) {
            paneCadastroDoador.setVisible(true);
        }else if(opcFuncionario.getValue().equals("Editar Dados de Doador")){
            paneAtualizarCD.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Apagar cadastro de doador")) {
            paneApagarCadastroDoador.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Armazenar Doação")){
            paneArmazenarDoacao.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Atribuir Ficha Médica")){
            paneAtribuirFichaMedica.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Atribuir Formulario de Doação de Medula")){
            paneAtribuirFormularioMedula.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Remover Cadastro de Doação de Medula")){
            paneApagarCDM.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Agendar doacao")){
            paneAgendarDoacao.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Listar doadores")){
            paneExibirDoadores.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Listar doacoes")){
            paneExibirDoacoes.setVisible(true);
        }else if(opcFuncionario.getValue().toString().equals("Listar agendamentos de doacao")){
            paneExibirAgendamentos.setVisible(true);
        } else if(opcFuncionario.getValue().equals("Listar Fichas medicas")){
            paneExibirFichasMedicas.setVisible(true);
        }

    }

    public void fecharOpcPrincipal(){
        if(paneCadastroDoador.isVisible()){
            paneCadastroDoador.setVisible(false);
        }else if(paneApagarCadastroDoador.isVisible()){
            paneApagarCadastroDoador.setVisible(false);
        }else if(paneAtualizarCD.isVisible()){
            paneAtualizarCD.setVisible(false);
        }else if(paneArmazenarDoacao.isVisible()){
            paneArmazenarDoacao.setVisible(false);
        }else if(paneAtribuirFichaMedica.isVisible()){
            paneAtribuirFichaMedica.setVisible(false);
        }else if(paneAtribuirFormularioMedula.isVisible()){
            paneAtribuirFormularioMedula.setVisible(false);
        }else if(paneApagarCDM.isVisible()){
            paneApagarCDM.setVisible(false);
        }else if(paneAgendarDoacao.isVisible()){
            paneAgendarDoacao.setVisible(false);
        }else if(paneExibirDoadores.isVisible()){
            paneExibirDoadores.setVisible(false);
        }else if(paneExibirDoacoes.isVisible()){
            paneExibirDoacoes.setVisible(false);
        }else if(paneExibirAgendamentos.isVisible()){
            paneExibirAgendamentos.setVisible(false);
        }
    }

    @FXML
    void abrirTelaAtualizacao() {
        fecharTelaAtualizacao();
        if(opcAtualizarDadosDoador.getValue().toString().equals("Nome")){
            paneAttNome.setVisible(true);
        }else if(opcAtualizarDadosDoador.getValue().toString().equals("Idade")){
            catchAttIdade.setVisible(true);
        }else if(opcAtualizarDadosDoador.getValue().toString().equals("CPF")){
            paneAttCpf.setVisible(true);
        }else if(opcAtualizarDadosDoador.getValue().toString().equals("Genero")){
            catchAttGenero.setVisible(true);
        }else if(opcAtualizarDadosDoador.getValue().toString().equals("Senha")){
            paneAttSenha.setVisible(true);
        }else if(opcAtualizarDadosDoador.getValue().toString().equals("Tipo Sanguineo")){
            paneAttTipoSanguineo.setVisible(true);
        }
    }

    public void fecharTelaAtualizacao(){
        if(paneAttNome.isVisible()){
            paneAttNome.setVisible(false);
        }else if(catchAttIdade.isVisible()){
            catchAttIdade.setVisible(false);
        }else if(paneAttCpf.isVisible()){
            paneAttCpf.setVisible(false);
        }else if(catchAttGenero.isVisible()){
            catchAttGenero.setVisible(false);
        }else if(paneAttSenha.isVisible()){
            paneAttSenha.setVisible(false);
        }else if(paneAttTipoSanguineo.isVisible()){
            paneAttTipoSanguineo.setVisible(false);
        }
    }

    //telaas de Alert
    public void abrirAlertPJE(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pessoa ja existente");
        alert.setHeaderText("Pessoa com esse CPF já está cadastrada");
        alert.setContentText("Tente Novamente com outro Cpf");

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

    public void abrirAlertCadastrado(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parabéns");
        alert.setHeaderText("Doador cadastrado!");
        alert.show();
    }

    public void abrirAlertDDC(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parabéns");
        alert.setHeaderText("Dado Atualizado!");
        alert.show();
    }

    public void FecharFDM(){
        if(paneFormularioDM.isVisible()){
            paneFormularioDM.setVisible(false);
        }
    }

    public void abrirAlertPessoaInexistente(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pessoa não Existe");
        alert.setHeaderText("Pessoa com esse CPF não está cadastrada");
        alert.setContentText("Tente Novamente com outro Cpf");

        alert.show();
    }

    public void abrirAlertPessoRemovida(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Doador Removido");
        alert.setHeaderText("Doador removido com Sucesso");
        alert.show();
    }

    public void fechartelaformulario(){
        if(paneFormularioDoacao.isVisible()){
            paneFormularioDoacao.setVisible(false);
        }
    }

    public void abritelaformulario(){
        paneFormularioDoacao.setVisible(true);
    }

    public void fecharFichaMedica(){
        if(paneFichaMédica.isVisible()){
            paneFichaMédica.setVisible(false);
        }
    }

    public void abrirAlertDoadorMedula(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText("Doador já não é Doador de Medula!");
        alert.show();
    }

    public void fecharFDM(){
        if(paneFormularioDM1.isVisible()){
            paneFormularioDM1.setVisible(false);
        }
    }

    public void abrirAlertJaEhDoadorMedula(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText("Doador já é Doador de Medula!");
        alert.show();
    }


    public void abrirAlertERROFormatoDeInformacao(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Não foi possível atribuir a ficha médica");
        alert.setHeaderText("Dados pertencentes a ficha médica inválidos");
        alert.setTitle("Insira dados válidos");

        alert.show();
    }

    public void abrirAlertERROAgendamentoJaExiste(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possível agendar a doação");
        alert.setHeaderText("Agendamento já cadastrado");

        alert.show();
    }

    public void abrirAlertERRODoacaoJaExiste(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possível adicionar a doação");
        alert.setHeaderText("Doação sanguínea já cadastrada");

        alert.show();
    }

    public void abrirAlertERRODoacaoNaoExiste(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possível encontrar a doação");
        alert.setHeaderText("Doação sanguínea não cadastrada");

        alert.show();
    }

    public void abrirInfAFMD(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parabens!");
        alert.setHeaderText("Formulario de Doação de Medula Cadastrado!");
        alert.show();
    }

    public void abrirAlertCONFIRMATION(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Ação realizada");
        alert.show();
    }

    public void abrirAlertERRONaoPodeDoar(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possivel cadastrar a doação");
        alert.setContentText("Segundo o formulário de doação, o doador não está apto a doar sangue");
        alert.show();
    }

    public void abrirAlertERROInformacoesVazias(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Não foi possivel fazer o cadastro");
        alert.setHeaderText("Campos vazios");
        alert.setContentText("Há informações que não podem estar em branco no formulário");
        alert.show();
    }


    //metodos para cadastros
    @FXML void cadastrarDoador() throws PessoaJaExistenteException, InformacoesNulasException{
        FecharFDM();
        RadioButton opcFormularioDoador = (RadioButton) groupDoadorMedula.getSelectedToggle();
        if(opcFormularioDoador.getText().equals("Sim")){
            paneFormularioDM.setVisible(true);
        }else{
            int idade = Integer.parseInt(intIdade.getText());
            Doador doador = new Doador(txtNome.getText(), txtCpf.getText(), idade, txtSenha.getText(), opcTipoSanguineo.getValue().toString(), opcGenero.getValue().toString());
            try {
                faxadaFuncionario.adicionar(doador);
                abrirAlertCadastrado();
                txtNome.setText(null);
                txtCpf.setText(null);
                intIdade.setText(null);
                txtSenha.setText(null);
                opcTipoSanguineo.setValue(null);
                opcGenero.setValue(null);
                FecharFDM();
                fecharOpcPrincipal();
            }catch (PessoaJaExistenteException e){
                txtCpf.setText(null);
                abrirAlertPJE();
            } catch (InformacoesNulasException e2){
                abrirAlertERROInformacoesVazias();
            }
        }
    }

    @FXML void cadastrarDM() throws PessoaJaExistenteException, InformacoesNulasException{
        int idade = Integer.parseInt(intIdade.getText());
        FormularioDoacaoMedula formulario = new FormularioDoacaoMedula(txtNacionalidade.getText(), opcRaca.getValue().toString(), txtNomeMae.getText(), txtNomePai.getText(), txtEndereco.getText(), txtTelefone.getText().toString());
        Doador doador = new Doador(txtNome.getText(), txtCpf.getText(), idade, txtSenha.getText(), opcTipoSanguineo.getValue().toString(), opcGenero.getValue().toString(), formulario);
        try {
            faxadaFuncionario.adicionar(doador);
            abrirAlertCadastrado();
            txtNome.setText(null);
            txtCpf.setText(null);
            intIdade.setText(null);
            txtSenha.setText(null);
            opcTipoSanguineo.setValue(null);
            opcGenero.setValue(null);
            txtNacionalidade.setText(null);
            opcRaca.setValue(null);
            txtNomeMae.setText(null);
            txtNomePai.setText(null);
            txtEndereco.setText(null);
            txtTelefone.setText(null);
            FecharFDM();
            fecharOpcPrincipal();
        }catch (PessoaJaExistenteException e){
            txtCpf.setText(null);
            abrirAlertPJE();
        } catch (InformacoesNulasException e2){
            abrirAlertERROInformacoesVazias();
        }
    }

    @FXML void removerDoador() throws PessoaInexistenteException {
        try {
            faxadaFuncionario.apagarCadastro(txtCpfApagar.getText());
            abrirAlertPessoRemovida();
            txtCpfApagar.setText(null);
            fecharOpcPrincipal();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfApagar.setText(null);
            fecharOpcPrincipal();
        }
    }

    //metodos para atualização de cadastro de doadores
    @FXML void atualizarCpf() throws PessoaInexistenteException{
        try{
            faxadaFuncionario.atualizarCpf(txtCpfVerificar.getText(), txtNovoCpf.getText());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            txtNovoCpf.setText(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            txtNovoCpf.setText(null);
            fecharTelaAtualizacao();
        }
    }

    @FXML void atualizarGenero() throws PessoaInexistenteException{
        try{
            faxadaFuncionario.atuzalizarIdade(txtCpfVerificar.getText());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            fecharTelaAtualizacao();
        }
    }

    @FXML void atualizarNome() throws PessoaInexistenteException{
        try{
            faxadaFuncionario.atualizarNome(txtCpfVerificar.getText(), txtNovoNome.getText());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            txtNovoNome.setText(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            txtNovoNome.setText(null);
            fecharTelaAtualizacao();
        }
    }

    @FXML void atualizarSenha() throws PessoaInexistenteException{
        try{
            faxadaFuncionario.atualizarSenha(txtCpfVerificar.getText(), txtNovaSenha.getText());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            txtNovaSenha.setText(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            txtNovaSenha.setText(null);
            fecharTelaAtualizacao();
        }
    }

    @FXML void atuaizarTipoSanguineo() {
        try{
            faxadaFuncionario.atualizarTipoSanguineo(txtCpfVerificar.getText(), opcAttTipoSanguineo.getValue().toString());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            opcAttTipoSanguineo.setValue(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            opcAttTipoSanguineo.setValue(null);
            fecharTelaAtualizacao();
        }
    }

    @FXML void atualizarIdade() {
        try{
            faxadaFuncionario.atuzalizarIdade(txtCpfVerificar.getText());
            abrirAlertDDC();
            txtCpfVerificar.setText(null);
            fecharTelaAtualizacao();
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfVerificar.setText(null);
            fecharTelaAtualizacao();
        }
    }


    // metodos para salvar doação
    @FXML void checarDataCPFDoacao() {
        fechartelaformulario();
        abritelaformulario();

    }

    @FXML void salvarDoacao() {
        if(!aviso.isVisible()){
            aviso.setVisible(true);
        }
    }


    //metodos para atribuição de ficha médica

    @FXML void buscarDoacao() {
        try {
            faxadaFuncionario.buscarDoacao(this.txtCPFVerificarDoacao.getText(), this.dateVerificarDoacao.getValue());
            this.paneFichaMédica.setVisible(true);
        } catch (DoacaoSanguineaInexistenteException e1) {
            abrirAlertERRODoacaoNaoExiste();
        }
    }

    @FXML void atribuirFichaMedicaADoacao(){
        try{

            float hemoglobina = Float.parseFloat(this.txtHemoglibina.getText());
            float temperaturaCorporal = Float.parseFloat(this.txtHemoglibina.getText());
            float peso = Float.parseFloat(this.txtHemoglibina.getText());
            float altura = Float.parseFloat(this.txtHemoglibina.getText());
            float volSangue = Float.parseFloat(this.txtHemoglibina.getText());

            FichaMedica ficha = new FichaMedica(hemoglobina, this.txtPressaoArterial.getText(), temperaturaCorporal, peso, altura, this.txtPulso.getText(), this.txtNumeroTubo.getText(), volSangue);

            faxadaFuncionario.atribuirFichaMedica(this.txtCPFVerificarDoacao.getText(), this.dateVerificarDoacao.getValue(), ficha);

            abrirAlertCONFIRMATION();
            this.paneAtribuirFichaMedica.setVisible(false);

        } catch (NumberFormatException e){
            abrirAlertERROFormatoDeInformacao();
        }
    }


    //metodo para remover cadastro de doador de medula
    @FXML
    void removerCDM() throws PessoaInexistenteException, DoadorMedulaException {
        try {
            faxadaFuncionario.removerCadastroMedula(txtCpfRemoverCDM.getText());
            abrirAlertDDC();
            txtCpfRemoverCDM.setText(null);
        }catch (PessoaInexistenteException e){
            abrirAlertPessoaInexistente();
            txtCpfRemoverCDM.setText(null);
        }catch (DoadorMedulaException e){
            abrirAlertDoadorMedula();
            txtCpfRemoverCDM.setText(null);
        }
    }

    //metodos para atribuição de formulario de doação de medula

    @FXML void abrirTelaFDM(){
        fecharFDM();
        if(faxadaFuncionario.checarExistencia(txtCpfAFDM.getText())){
            paneFormularioDM1.setVisible(true);
        }else{
            abrirAlertPessoaInexistente();
            txtCpfAFDM.setText(null);
        }
    }

    @FXML void atribuirFDM() throws PessoaInexistenteException , JaEhDoadorMedulaException{
        try{
            FormularioDoacaoMedula formulario = new FormularioDoacaoMedula(txtNacionalidade1.getText(), opcRaca1.getValue().toString(), txtNomeMae1.getText(), txtNomePai1.getText(), txtEndereco1.getText(), txtTelefone1.getText());
            faxadaFuncionario.atribuirFDM(txtCpfAFDM.getText(), formulario);
            txtNacionalidade1.setText(null);
            opcRaca1.setValue(null);
            txtNomeMae1.setText(null);
            txtNomePai1.setText(null);
            txtEndereco1.setText(null);
            txtTelefone1.setText(null);
            abrirInfAFMD();
            txtCpfAFDM.setText(null);
            fecharFDM();
        }catch (JaEhDoadorMedulaException e){
            abrirAlertJaEhDoadorMedula();
            txtNacionalidade1.setText(null);
            opcRaca1.setValue(null);
            txtNomeMae1.setText(null);
            txtNomePai1.setText(null);
            txtEndereco1.setText(null);
            txtTelefone1.setText(null);
            txtCpfAFDM.setText(null);
            fecharFDM();
        }
    }

    @FXML public void armazenarDoacao() {
        try {
            boolean fumante = this.groupFumante.getToggles().get(0).isSelected();
            boolean diabetico = this.groupFumante.getToggles().get(0).isSelected();
            boolean alcoolatra = this.groupFumante.getToggles().get(0).isSelected();
            boolean gestante = this.groupFumante.getToggles().get(0).isSelected();
            boolean lactante = this.groupFumante.getToggles().get(0).isSelected();

            FormularioDoacao formularioDoacao = this.faxadaFuncionario.criarFormularioDeDoacao(fumante, diabetico, alcoolatra, gestante, lactante);

            if (formularioDoacao != null) {
                this.faxadaFuncionario.adicionarDoacao(this.txtCpfDoacao.getText(), this.dateDoacao.getValue(), formularioDoacao);
                abrirAlertCONFIRMATION();
                this.txtCpfDoacao.setText("");
                this.dateDoacao.setValue(null);
                this.groupFumante.getSelectedToggle().setSelected(false);
                this.groupAlcoolatra.getSelectedToggle().setSelected(false);
                this.groupDiabetico.getSelectedToggle().setSelected(false);
                this.groupGestante.getSelectedToggle().setSelected(false);
                this.groupLactante.getSelectedToggle().setSelected(false);

                fecharOpcPrincipal();
            } else {

            }

        } catch (PessoaInexistenteException e1) {
            abrirAlertPessoaInexistente();
        } catch (DataInvalidaException e2){
            abrirAlertERRODataInvalida();
        } catch (DoacaoJaExistenteException e3){
            abrirAlertERRODoacaoJaExiste();
        }
    }

    @FXML public void agendarDoacao(){
        try{
            this.faxadaFuncionario.agendarDoacao(this.cpfDoadorAgendamento.getText(), this.dataAgendamento.getValue());
            abrirAlertCONFIRMATION();
        } catch (PessoaInexistenteException e1){
            abrirAlertPessoaInexistente();
        } catch (DataInvalidaException e2) {
            abrirAlertERRODataInvalida();
        } catch (AgendamentoDeDoacaoJaExistenteException e3){
            abrirAlertERROAgendamentoJaExiste();
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

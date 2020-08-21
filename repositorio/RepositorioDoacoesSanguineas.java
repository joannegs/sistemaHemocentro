package repositorio;

import negocio.entidade.DoacaoSanguinea;
import negocio.entidade.FichaMedica;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class RepositorioDoacoesSanguineas implements IDoacoesSanguineas,IProcessamentoInformacao {

    private ArrayList<DoacaoSanguinea> doacoes;
    private RepositorioDoador repositorioDoador;
    private static RepositorioDoacoesSanguineas instancia;

    // construtor

    public RepositorioDoacoesSanguineas() {
        this.doacoes = new ArrayList<DoacaoSanguinea>();
        repositorioDoador = RepositorioDoador.getInstance();
    }

    // singleton

    public static RepositorioDoacoesSanguineas getInstance() {
        if(instancia == null) {
            instancia = new RepositorioDoacoesSanguineas();
        }
        return instancia;
    }

    public ArrayList<DoacaoSanguinea> listarTodasAsDoacoes() {
        ArrayList<DoacaoSanguinea> doacoesCopia = new ArrayList<DoacaoSanguinea>(doacoes);
        Collections.copy(doacoes, doacoesCopia);
        return doacoesCopia;
    }

    @Override
    public void adicionar(DoacaoSanguinea doacao){
        doacoes.add(doacao);
    }

    @Override
    public ArrayList<FichaMedica> filtrarFichasPorDoador(String cpfDoador){
        if(repositorioDoador.buscarDoador(cpfDoador) != null){
            ArrayList<FichaMedica> fichasFiltradas = new ArrayList<FichaMedica>();
            DoacaoSanguinea doacaoSanguinea;
            for(int i = 0; i < doacoes.size(); i++){
                doacaoSanguinea = doacoes.get(i);
                if(doacaoSanguinea.getDoador().getCpf().equals(cpfDoador)){
                    fichasFiltradas.add(doacaoSanguinea.getFicha());
                }
            }
            return  fichasFiltradas;
        }
        return null;
    }


    public int quantasDoacoesNaData(LocalDate data){
        int doacoes = 0;

        for(int i = 0; i < this.doacoes.size(); i++){
            if(this.doacoes.get(i).getData().equals(data)){
                doacoes++;
            }
        }
        return doacoes;
    }

    public ArrayList<FichaMedica> retornarTodasAsFichas(){
        ArrayList<FichaMedica> fichas = new ArrayList<FichaMedica>();

        for(int i = 0; i < this.doacoes.size(); i++){
            fichas.add(this.doacoes.get(i).getFicha());
        }
        return fichas;
    }

    public ArrayList<DoacaoSanguinea> buscarDoacoesPorDoador(String cpfDoador){
        if(repositorioDoador.buscarDoador(cpfDoador) != null){
            ArrayList<DoacaoSanguinea> listaDoacoes = new ArrayList<DoacaoSanguinea>();
            DoacaoSanguinea doacaoSanguinea;
            for(int i = 0; i < doacoes.size(); i++){
                doacaoSanguinea = doacoes.get(i);
                if(doacaoSanguinea.getDoador().getCpf().equals(cpfDoador)){
                    listaDoacoes.add(doacaoSanguinea);
                }
            }
            return listaDoacoes;
        }
        return null;
    }

    public boolean checarExistencia(String cpfDoador, LocalDate data) {
        boolean existe = false;
        for(int x = 0; x < doacoes.size() ;x++) {
            if(doacoes.get(x).getData().equals(data) && doacoes.get(x).getDoador().getCpf().equals(cpfDoador)){
                existe = true;
                break;
            }
        }
        return existe;
    }

    @Override
    public DoacaoSanguinea buscar(String cpfDoador, LocalDate data) {
        DoacaoSanguinea doacao_filtrada = null;
        if (repositorioDoador.buscarDoador(cpfDoador) != null) {
            DoacaoSanguinea doacao;
            for (int i = 0; i < doacoes.size(); i++) {
                doacao = doacoes.get(i);
                if (doacao.getDoador().equals(repositorioDoador.buscarDoador(cpfDoador)) && doacao.getData().equals(data)) {
                    doacao_filtrada = doacao;
                    break;
                }
            }
        }
        return doacao_filtrada;
    }

    @Override
    public void remover(String cpfDoador, LocalDate dataDoacao){
        if(buscar(cpfDoador, dataDoacao) != null){
            for(int x = 0; x < doacoes.size(); x++){
                // remover doacao do arraylist geral
                if(doacoes.get(x).getData().equals(dataDoacao) && doacoes.get(x).getDoador().getCpf().equals(cpfDoador)){
                    doacoes.remove(x);

                    // remover a doação do arraylist individual do doador
                    for(int y = 0; y < doacoes.get(x).getDoador().getDoacoes().size(); y++){
                        if(doacoes.get(x).getDoador().getDoacoes().get(y).getData().equals(dataDoacao)){
                            doacoes.get(x).getDoador().getDoacoes().remove(y);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void atualizarDoador(String cpfDoador, LocalDate dataDoacao, String novoCpf){
        if(buscar(cpfDoador, dataDoacao) != null){
            if(repositorioDoador.buscarDoador(novoCpf) != null){
                buscar(cpfDoador, dataDoacao).setDoador(repositorioDoador.buscarDoador(novoCpf));
            }
        }
    }

    // CHECAR DATAS INVÁLIDAS
    @Override
    public void atualizarData(String cpfDoador, LocalDate dataDoacao, LocalDate novaData){
        if(buscar(cpfDoador, dataDoacao) != null){
            buscar(cpfDoador, dataDoacao).setData(novaData);
        }
    }

    public void atualizarHemogoblina_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarPressaoArterial_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarHTemperaturaCorporal_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarPeso_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarAltura_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarPulso_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarNumTubo_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    public void atualizarVolumeSangue_fichaMedica(DoacaoSanguinea doacao, float hemogoblina){
        doacao.getFicha().setHemoglobina(hemogoblina);
    }

    //NUMEROS RELACIONADOS A SANGUE

    @Override
    public double sangueA() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("A+") || doacao.getDoador().getTipoSanguineo().equals("A-")){
               quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueAP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("A+")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueAN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("A-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("B+") || doacao.getDoador().getTipoSanguineo().equals("B-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueBP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("B+")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueBN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("B-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueAB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("AB+") || doacao.getDoador().getTipoSanguineo().equals("AB-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueABP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("AB+")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueABN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("AB-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueO() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("O+") || doacao.getDoador().getTipoSanguineo().equals("O-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueOP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("O+")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double sangueON() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getTipoSanguineo().equals("O-")){
                quantidade++;
            }
        }

        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    //NUMEROS RELACIONADOS AOS HOMENS

    @Override
    public double doadoresHomens() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doadoresHomensSA() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && (doacao.getDoador().getTipoSanguineo().equals("A+") || doacao.getDoador().getTipoSanguineo().equals("A-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doadoresHomensSAP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("A+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doadoresHomensSAN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("A-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && (doacao.getDoador().getTipoSanguineo().equals("B+") || doacao.getDoador().getTipoSanguineo().equals("B-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSBP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("B+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSBN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("B-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSAB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && (doacao.getDoador().getTipoSanguineo().equals("AB+") || doacao.getDoador().getTipoSanguineo().equals("AB-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSABP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("AB+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSABN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("AB-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSO() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && (doacao.getDoador().getTipoSanguineo().equals("O+") || doacao.getDoador().getTipoSanguineo().equals("O-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSOP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("O+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresHomensSON() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Masculino") && doacao.getDoador().getTipoSanguineo().equals("O-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    // NUMEROS RELACIONADOS AS MULHERES

    @Override
    public double doaodoresMulheres() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSA() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && (doacao.getDoador().getTipoSanguineo().equals("A+") || doacao.getDoador().getTipoSanguineo().equals("A-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSAP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("A+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSAN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("A-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && (doacao.getDoador().getTipoSanguineo().equals("B+") || doacao.getDoador().getTipoSanguineo().equals("B-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSBP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("B+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSBN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("B-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSAB() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && (doacao.getDoador().getTipoSanguineo().equals("AB+") || doacao.getDoador().getTipoSanguineo().equals("AB-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSABP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("AB+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSABN() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("AB-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSO() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && (doacao.getDoador().getTipoSanguineo().equals("O+") || doacao.getDoador().getTipoSanguineo().equals("O-"))){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSOP() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("O+")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }

    @Override
    public double doaodoresMulheresSON() {
        int quantidade = 0;
        for(DoacaoSanguinea doacao : doacoes){
            if(doacao.getDoador().getGenero().equals("Feminino") && doacao.getDoador().getTipoSanguineo().equals("O-")){
                quantidade++;
            }
        }
        if(doacoes.size() != 0){
            double estatistica = (quantidade * 100) / doacoes.size();
            return estatistica;
        }
        return 0;
    }
}
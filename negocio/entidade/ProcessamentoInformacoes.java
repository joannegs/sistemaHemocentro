package negocio.entidade;

import java.util.ArrayList;

public class ProcessamentoInformacoes {

    public static double gerarDadaosPerfilCadastroMasculino(ArrayList<Doador> doadores) {
        int cont_masculino = 0;
        int totalDoadores = doadores.size() + 1;

        for (int i = 0; i < doadores.size(); i++) {
            Doador doador = doadores.get(i);
            if (doador.getGenero().equals("Masculino")) {
                cont_masculino++;
            }
        }

        float porcentagemMasculina = (cont_masculino / totalDoadores) * 100;
        return porcentagemMasculina;
    }

    public static double gerarDadaosPerfilCadastroFeminino(ArrayList<Doador> doadores) {
        int cont_feminino = 0;
        int totalDoadores = doadores.size() + 1;

        for (int i = 0; i < doadores.size(); i++) {
            Doador doador = doadores.get(i);
            if (doador.getGenero().equals("Feminino")) {
                cont_feminino++;
            }
        }

        float porcentagemFeminina = (cont_feminino / totalDoadores) * 100;
        return porcentagemFeminina;
    }

    public static double taxaHomens(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getGenero().equals("Masculino")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador*100)/max;
        }else{
            return result = 0;
        }
    }

    public static double taxaMulheres(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getGenero().equals("Feminino")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador*100)/max;
        }else{
            return result = 0;
        }
    }

    public static double taxaSangueA(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getTipoSanguineo().equals("A+") || doacoes.get(i).getDoador().getTipoSanguineo().equals("A-")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador * 100) / max;
        }else{
            return result = 0;
        }
    }

    public static double taxaSangueB(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getTipoSanguineo().equals("B+") || doacoes.get(i).getDoador().getTipoSanguineo().equals("B-")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador*100)/max;
        }else{
            return result = 0;
        }
    }

    public static double taxaSangueAB(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getTipoSanguineo().equals("AB+") || doacoes.get(i).getDoador().getTipoSanguineo().equals("AB-")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador*100)/max;
        }else{
            return result = 0;
        }
    }

    public static double taxaSangueO(ArrayList<DoacaoSanguinea> doacoes){
        int max = doacoes.size();
        int contador = 0;
        double result;
        for(int i = 0; i < max ;i++){
            if(doacoes.get(i).getDoador().getTipoSanguineo().equals("O+") || doacoes.get(i).getDoador().getTipoSanguineo().equals("O-")){
                contador += 1;
            }
        }
        if(max != 0){
            return result = (contador*100)/max;
        } else {
            return result = 0;
        }
    }
}
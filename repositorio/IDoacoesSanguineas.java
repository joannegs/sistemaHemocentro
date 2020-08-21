package repositorio;

import negocio.entidade.DoacaoSanguinea;
import negocio.entidade.FichaMedica;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDoacoesSanguineas {

    public void adicionar(DoacaoSanguinea doacao);
    public ArrayList<FichaMedica> filtrarFichasPorDoador(String cpfDoador);
    public DoacaoSanguinea buscar(String cpfDoador, LocalDate data);
    public void remover(String cpfDoador, LocalDate dataDoacao);
    public void atualizarDoador(String cpfDoador, LocalDate dataDoacao, String novoCpf);
    public void atualizarData(String cpfDoador, LocalDate dataDoacao, LocalDate novaData);


}

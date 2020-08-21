package negocio.excecao;

public class DoacaoSanguineaInexistenteException extends Exception {

    boolean doacaoSanguineaExiste;

    public DoacaoSanguineaInexistenteException(){
        super("Doação sanguíena não cadastrada");
        this.doacaoSanguineaExiste = false;
    }

    public boolean isDoacaoSanguineaExiste() {
        return doacaoSanguineaExiste;
    }
}

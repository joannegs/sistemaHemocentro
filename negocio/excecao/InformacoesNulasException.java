package negocio.excecao;

public class InformacoesNulasException extends Exception{
    boolean formularioIncompleto;

    public InformacoesNulasException(){
        super("Há informações que não podem estar em branco no formulário");
        formularioIncompleto = true;
    }

    public boolean isFormularioIncompleto() {
        return formularioIncompleto;
    }
}

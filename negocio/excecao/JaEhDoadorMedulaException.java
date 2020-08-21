package negocio.excecao;

public class JaEhDoadorMedulaException extends Exception{

    private boolean ehDoadordeMedula;

    public JaEhDoadorMedulaException() {
        super("Doador já é doador de medula");
        this.ehDoadordeMedula = true;
    }

    public boolean isDoadorMedula() {
        return ehDoadordeMedula;
    }

    public void setDoadorMedula(boolean doadorMedula) {
        this.ehDoadordeMedula = doadorMedula;
    }

}

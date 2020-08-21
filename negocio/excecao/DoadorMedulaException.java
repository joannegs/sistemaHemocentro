package negocio.excecao;

public class DoadorMedulaException extends Exception {
	
	private boolean naoDoadordeMedula;

	public DoadorMedulaException() {
		super("Doaodor nao eh doador de medula");
		this.naoDoadordeMedula = true;
	}

	public boolean isDoadorMedula() {
		return naoDoadordeMedula;
	}

	public void setDoadorMedula(boolean doadorMedula) {
		this.naoDoadordeMedula = doadorMedula;
	}
	
	
}

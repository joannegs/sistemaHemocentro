package negocio.entidade;

public class FormularioDoacao {
    private boolean fumante;
    private boolean alcoolatra;
    private boolean gestante;
    private boolean lactante;
    private boolean diabetico;

    public FormularioDoacao(boolean fumante, boolean alcoolatra, boolean gestante, boolean lactante, boolean diabetico) {
        setFumante(fumante);
        setAlcoolatra(alcoolatra);
        setGestante(gestante);
        setLactante(lactante);
        setDiabetico(diabetico);
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public boolean isAlcoolatra() {
        return alcoolatra;
    }

    public void setAlcoolatra(boolean alcoolatra) {
        this.alcoolatra = alcoolatra;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public boolean isLactante() {
        return lactante;
    }

    public void setLactante(boolean lactante) {
        this.lactante = lactante;
    }

    public boolean isDiabetico() {
        return diabetico;
    }

    public void setDiabetico(boolean diabetico) {
        this.diabetico = diabetico;
    }

    public boolean checarformulario(){
        if(this.isFumante() == false) {
            if (this.isAlcoolatra() == false) {
                if (this.isDiabetico() == false) {
                    if (this.isGestante() == false && this.isLactante() == false) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

}
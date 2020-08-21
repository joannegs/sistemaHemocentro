package negocio.entidade;

public class FichaMedica {
    private float hemoglobina;
    private String pressaoArterial;
    private float temperaturaCorporal;
    private float peso;
    private float altura;
    private String pulso;
    private String numeroDoTubo;
    private float volumeDeSangue;

    public FichaMedica(float hemoglobina, String pressaoArterial, float temperaturaCorporal, float peso, float altura, String pulso, String numeroTubo, float volume) {
        setHemoglobina(hemoglobina);
        setPressaoArterial(pressaoArterial);
        setTemperaturaCorporal(temperaturaCorporal);
        setPeso(peso);
        setAltura(altura);
        setPulso(pulso);
        setNumeroDoTubo(numeroTubo);
        setVolumeDeSangue(volume);
    }

    public String getNumeroDoTubo() {
        return numeroDoTubo;
    }

    public float getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(float hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public float getTemperaturaCorporal() {
        return temperaturaCorporal;
    }

    public void setTemperaturaCorporal(float temperaturaCorporal) {
        this.temperaturaCorporal = temperaturaCorporal;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public void setNumeroDoTubo(String numeroDoTubo) {
        this.numeroDoTubo = numeroDoTubo;
    }

    public float getVolumeDeSangue() {
        return volumeDeSangue;
    }

    public void setVolumeDeSangue(float volumeDeSangue) {
        this.volumeDeSangue = volumeDeSangue;
    }

    @Override
    public String toString() {
        return "Hemoglobina: " + hemoglobina + "\n" +
                "Pressao Arterial: " + pressaoArterial + "\n" +
                "Temperatura Corporal: " + temperaturaCorporal + "\n" +
                "Peso: " + peso + "\n" +
                "Altura: " + altura + "\n" +
                "Pulso: " + pulso + '\n' +
                "Numero do Tubo: " + numeroDoTubo + '\n' +
                "Volume de sangue: " + volumeDeSangue + "\n----------------------" ;
    }
}
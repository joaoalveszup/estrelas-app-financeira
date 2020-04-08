package br.com.zup.estrelas.financas.enums;

public enum Parentesco {

    FILHO("filho"), CONJUGE("cônjuge"), PAI("pai"), MAE("mãe"), OUTRO("outro");

    private String value;

    private Parentesco(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}



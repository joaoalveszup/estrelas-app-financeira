package br.com.zup.estrelas.financas.enums;

public enum TipoSugestao {
    CRITICA("critica"), MELHORIA("melhoria"), BUG("bug"), OUTRA("outra");

    private String sugestaoValor;


    TipoSugestao(String valor) {
        this.sugestaoValor = valor;
    }

    public String getSugestaoValor() {
        return sugestaoValor;
    }

    @Override
    public String toString() {
        return this.sugestaoValor;
    }
}

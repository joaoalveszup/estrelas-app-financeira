package br.com.zup.estrelas.financas.enums;

public enum TipoDespesa {

    AGUA("agua"), LUZ("luz"), ALUGUEL("aluguel"), FINANCIAMENTO("financiamento"), CARRO(
            "carro"), ESCOLA_FILHO("escolaFilho"), ESCOLA("escola"), OUTRO("outro");

    private String valueDespesa;

    TipoDespesa(String value) {
        this.valueDespesa = value;
    }

    public String getValueDespesa() {
        return valueDespesa;
    }

    @Override
    public String toString() {
        return this.valueDespesa;
    }


}

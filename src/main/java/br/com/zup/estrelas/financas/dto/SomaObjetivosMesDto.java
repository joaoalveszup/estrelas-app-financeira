package br.com.zup.estrelas.financas.dto;

public class SomaObjetivosMesDto {

    private Double valorTotal;

    public SomaObjetivosMesDto(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

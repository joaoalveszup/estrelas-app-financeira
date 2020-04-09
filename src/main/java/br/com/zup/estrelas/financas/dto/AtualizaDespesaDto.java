package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Despesa;

public class AtualizaDespesaDto {

    private float valor;

    public static AtualizaDespesaDto fromAtualizaDespesa(Despesa despesa) {
        AtualizaDespesaDto atualizaDespesaDto = new AtualizaDespesaDto();

        atualizaDespesaDto.setValor(despesa.getValor());

        return atualizaDespesaDto;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}

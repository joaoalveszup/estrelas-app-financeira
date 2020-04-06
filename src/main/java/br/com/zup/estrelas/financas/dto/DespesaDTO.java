package br.com.zup.estrelas.financas.dto;

import java.time.LocalDate;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.enums.TipoDespesa;

public class DespesaDTO {


    private Long idDespesa;
    private float valor;
    private LocalDate vencimento;
    private TipoDespesa tipoDespesa;

    public static DespesaDTO fromDespesa(Despesa despesa) {
        DespesaDTO despesaDto = new DespesaDTO();
        despesaDto.setIdDespesa(despesa.getIdDespesa());
        despesaDto.setValor(despesa.getValor());
        despesaDto.setVencimento(despesa.getVencimento());
        despesaDto.setTipoDespesa(despesa.getTipoDeDespesa());

        return despesaDto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }   

}

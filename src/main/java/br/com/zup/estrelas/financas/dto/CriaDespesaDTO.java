package br.com.zup.estrelas.financas.dto;

import java.time.LocalDate;
import br.com.zup.estrelas.financas.enums.TipoDespesa;

public class CriaDespesaDTO {

    private float valor;
    private LocalDate vencimento;
    private TipoDespesa tipoDespesa;

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
}


package br.com.zup.estrelas.financas.dto;

import java.time.LocalDate;
import br.com.zup.estrelas.financas.entity.Despesa;

public class AtualizaDespesaDto {
    
    private float valor;
    private LocalDate vencimento;
    
    public static AtualizaDespesaDto fromAtualizaDespesa(Despesa despesa) {
        AtualizaDespesaDto atualizaDespesaDto = new AtualizaDespesaDto();
        
        atualizaDespesaDto.setValor(despesa.getValor());
        atualizaDespesaDto.setVencimento(despesa.getVencimento());
        
        return atualizaDespesaDto;
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
    
    

}

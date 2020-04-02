package br.com.zup.estrelas.financas.dto;

import java.util.List;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.entity.Usuario;

public class ObjetivoDto {

    private String nome;
    private List<Investimento> investimentos;
    private float valorTotal;
    private int numeroInvestimentos;

    public static ObjetivoDto fromEntity(Objetivo objetivo) {
        ObjetivoDto objetivoDto = new ObjetivoDto();
        objetivoDto.setNome(objetivo.getNome());
        objetivoDto.setInvestimentos(objetivo.getInvestimentos());
        objetivoDto.setValorTotal(objetivo.getValorTotal());
        objetivoDto.setNumeroInvestimentos(objetivo.getNumeroInvestimentos());

        return objetivoDto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumeroInvestimentos() {
        return numeroInvestimentos;
    }

    public void setNumeroInvestimentos(int numeroInvestimentos) {
        this.numeroInvestimentos = numeroInvestimentos;
    }



}

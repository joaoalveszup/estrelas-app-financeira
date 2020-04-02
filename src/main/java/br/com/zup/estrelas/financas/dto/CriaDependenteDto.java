package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.enums.Parentesco;

public class CriaDependenteDto {

    private String nome;
    private Float renda;
    private Parentesco parentesco;

    public static CriaDependenteDto fromDto(Dependente dependente) {
        CriaDependenteDto criaDependenteDto = new CriaDependenteDto();
        criaDependenteDto.setNome(dependente.getNome());
        criaDependenteDto.setRenda(dependente.getRenda());
        criaDependenteDto.setParentesco(dependente.getParentesco());
        return criaDependenteDto;
    }

    public void setRenda(Float renda) {
        this.renda = renda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Float getRenda() {
        return renda;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

}



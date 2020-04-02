package br.com.zup.estrelas.financas.dto;

import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.enums.Parentesco;

public class DependenteDto {
    
    private String nome;
    private Parentesco parentesco;
    private Float renda;
    
    public static DependenteDto fromDto(Dependente dependente) {
        DependenteDto dependenteDto = new DependenteDto();
        dependenteDto.setNome(dependente.getNome());
        dependenteDto.setRenda(dependente.getRenda());
        dependenteDto.setParentesco(dependente.getParentesco());
        return dependenteDto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Float getRenda() {
        return renda;
    }

    public void setRenda(Float renda) {
        this.renda = renda;
    }

}

package br.com.zup.estrelas.financas.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.estrelas.financas.enums.TipoDocumento;

public class UsuarioDto {


    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotNull
    private Float salarioBruto;

    @NotNull
    private Float salarioLiquido;

    @NotBlank
    private String profissao;

    @NotBlank
    private String empresa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Float getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Float salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Float getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(Float salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
}

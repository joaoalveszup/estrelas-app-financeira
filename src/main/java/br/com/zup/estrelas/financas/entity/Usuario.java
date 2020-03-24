package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String documento;
    
    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;
    
    @Column(name = "salario_bruto", nullable = false)
    private Float salarioBruto;
    
    @Column(name = "salario_liquido", nullable = false)
    private Float salarioLiquido;
    
    @Column(nullable = false)
    private String profissao;
    
    @Column
    private String empresa;

    //TODO: CRIAR ASSOCIAÇÕES
    
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
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
package br.com.zup.estrelas.financas.entity;

import br.com.zup.estrelas.financas.dto.UsuarioDto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.beans.BeanUtils;

@Entity
public class Usuario {

    public Usuario(UsuarioDto usuarioDto) {
        BeanUtils.copyProperties(usuarioDto, this);
        this.tipoDocumento = usuarioDto.getTipoDocumento().getValue();
    }

    public Usuario() {
    }

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Dependente> dependentes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Despesa> despesas;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Objetivo> objetivos;

    public Long getIdUsuario() {
        return idUsuario;
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

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }
}

package br.com.zup.estrelas.financas.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.zup.estrelas.financas.dto.ObjetivoDto;

@Entity
public class Objetivo {

    @Id
    @Column(name = "id_objetivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObjetivo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false,
            updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // PESQUISE SOBRE O FETCH
    @JoinColumn(name = "id_objetivo") // USEI JOIN COLUMN PRA REFERENCIAR
    private List<Investimento> investimentos;

    @Column
    private float valorTotal;

    @Column(name = "numero_investimentos")
    private int numeroInvestimentos;
    
    public static Objetivo fromDto(Usuario usuario, ObjetivoDto objetivoDto) {
        Objetivo objetivo = new Objetivo();
        objetivo.setNome(objetivoDto.getNome());
        objetivo.setUsuario(usuario);
        objetivo.setInvestimentos(objetivoDto.getInvestimentos());
        objetivo.setValorTotal(objetivoDto.getValorTotal());
        objetivo.setNumeroInvestimentos(objetivoDto.getNumeroInvestimentos());
        objetivo.setIdUsuario(usuario.getIdUsuario());
        
        return objetivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdObjetivo() {
        return idObjetivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public void setIdObjetivo(Long idObjetivo) {
        this.idObjetivo = idObjetivo;
    }


}

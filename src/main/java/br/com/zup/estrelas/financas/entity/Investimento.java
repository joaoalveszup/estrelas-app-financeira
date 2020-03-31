package br.com.zup.estrelas.financas.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Investimento {

    @Id
    @Column(name = "id_investimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvestimento;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false, name = "data_vencimento")
    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "id_objetivo", referencedColumnName = "id_objetivo", insertable = false,
            updatable = false)
    @JsonIgnore
    private Objetivo objetivo;

    @JsonIgnore
    @Column(name = "id_objetivo") 
    private Long idObjetivo;

    @Column
    private boolean pago;

    public Float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Long getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Long idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    
}

package br.com.zup.estrelas.financas.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Long idDespesa;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false, name = "tipo_de_despesa")
    private String tipoDeDespesa;

    @Column(nullable = false)
    private LocalDate vencimento;

    @ManyToOne
    private Usuario usuario;

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipoDeDespesa() {
        return tipoDeDespesa;
    }

    public void setTipoDeDespesa(String tipoDeDespesa) {
        this.tipoDeDespesa = tipoDeDespesa;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
}

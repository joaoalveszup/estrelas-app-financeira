package br.com.zup.estrelas.financas.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.zup.estrelas.financas.dto.AtualizaDespesaDto;
import br.com.zup.estrelas.financas.dto.CriaDespesaDTO;
import br.com.zup.estrelas.financas.enums.TipoDespesa;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Long idDespesa;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false, name = "tipo_de_despesa")
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDeDespesa;

    @Column(nullable = false)
    private LocalDate vencimento;

    @Column(nullable = false)
    private boolean paga;

    @Column(nullable = false)
    private boolean recorrente;

    @Column(nullable = false, name = "numero_recorrencias")
    private Integer numeroRecorrencias;


    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false,
            updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "id_usuario")
    private Long idUsuario;

    public static Despesa fromCriacaoDto(CriaDespesaDTO criaDespesaDto, Long idUsuario) {
        Despesa despesaConvertida = new Despesa();
        despesaConvertida.setIdUsuario(idUsuario);
        despesaConvertida.setTipoDeDespesa(criaDespesaDto.getTipoDespesa());
        despesaConvertida.setValor(criaDespesaDto.getValor());
        despesaConvertida.setVencimento(criaDespesaDto.getVencimento());
        despesaConvertida.setNumeroRecorrencias(criaDespesaDto.getNumeroRecorrencias());
        despesaConvertida.setRecorrente(criaDespesaDto.isRecorrente());
        return despesaConvertida;
    }

    public static Despesa fromDespesaAtualizada(AtualizaDespesaDto atualizaDespesaDto,
            Long idUsuario) {
        Despesa despesaConvertida = new Despesa();
        despesaConvertida.setValor(atualizaDespesaDto.getValor());
        despesaConvertida.setIdUsuario(idUsuario);
        return despesaConvertida;
    }

    public Long getIdDespesa() {
        return idDespesa;
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

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public TipoDespesa getTipoDeDespesa() {
        return tipoDeDespesa;
    }

    public void setTipoDeDespesa(TipoDespesa tipoDeDespesa) {
        this.tipoDeDespesa = tipoDeDespesa;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public boolean isRecorrente() {
        return recorrente;
    }

    public void setRecorrente(boolean recorrente) {
        this.recorrente = recorrente;
    }

    public int getNumeroRecorrencias() {
        return numeroRecorrencias;
    }

    public void setNumeroRecorrencias(int numeroRecorrencias) {
        this.numeroRecorrencias = numeroRecorrencias;
    }
}

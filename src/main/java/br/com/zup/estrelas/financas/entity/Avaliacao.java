package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.zup.estrelas.financas.dto.CriaAvaliacaoDto;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao", nullable = false)
    private Long idAvaliacao;

    @Column(name = "nota_avaliacao", nullable = false)
    private int notaAvaliacao;

    @Column
    private String comentario;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false,
            updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "id_usuario")
    private Long idUsuario;

    public static Avaliacao fromCriacaoDto(CriaAvaliacaoDto criaAvaliacaoDto, Long idUsuario) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setComentario(criaAvaliacaoDto.getComentario());
        avaliacao.setNotaAvaliacao(criaAvaliacaoDto.getNotaAvaliacao());
        avaliacao.setIdUsuario(idUsuario);
        return avaliacao;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

}
